package com.blockwars.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

/**
 * Shared logic for every BlockWars mecha: piloting, jumping/thrusters, two weapon slots
 * (primary = R, secondary = G), repair, packing back into a core, and pilot protection.
 */
public abstract class BaseMechaEntity extends Mob implements PlayerRideableJumping {
    public static final byte EVENT_PRIMARY = 101;
    public static final byte EVENT_SECONDARY = 102;

    private static final EntityDataAccessor<Boolean> DATA_BOOSTING =
            SynchedEntityData.defineId(BaseMechaEntity.class, EntityDataSerializers.BOOLEAN);

    protected int primaryCooldown;
    protected int secondaryCooldown;
    /** Ticks since the current primary/secondary action started, or -1 when idle (tracked on both sides). */
    protected int primaryTicks = -1;
    protected int secondaryTicks = -1;
    private float pendingJump;
    private float boostAmount;
    private float boostAmountO;

    protected BaseMechaEntity(EntityType<? extends BaseMechaEntity> type, Level level) {
        super(type, level);
        this.setPersistenceRequired();
    }

    // ================================================================= per-mecha configuration

    /** Item dropped when the mecha is packed up with sneak + right-click. */
    public abstract Item getCoreItem();

    /** Height (blocks) where the pilot sits. */
    protected abstract double seatHeight();

    /** Closed cockpits hide the pilot's body. */
    public boolean hidesPilot() {
        return true;
    }

    /** 0 = cannot charge-jump. 1 = normal mecha jump. */
    protected float jumpStrength() {
        return 1.0F;
    }

    /** Mechas with thrusters fly while SPACE is held instead of charge-jumping. */
    public boolean canFly() {
        return false;
    }

    protected int primaryCooldownTicks() { return 10; }
    protected int secondaryCooldownTicks() { return 20; }
    /** How long the primary/secondary action (and its animation) lasts, in ticks. */
    protected int primaryDuration() { return 8; }
    protected int secondaryDuration() { return 8; }

    /** Server: called every tick while the action runs (t = 0 .. duration-1). */
    protected void primaryTick(Player pilot, int t) {}
    protected void secondaryTick(Player pilot, int t) {}
    /** Client: visual effects every tick while the action runs. */
    protected void primaryClientTick(int t) {}
    protected void secondaryClientTick(int t) {}
    /** Client: ambient effects (smoke, sparks) every tick. */
    protected void ambientClientTick() {}

    /** Lets a mecha reduce incoming damage (shields, armor plating...). */
    protected float modifyIncomingDamage(DamageSource source, float amount) {
        return amount;
    }

    // ================================================================= synched data

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_BOOSTING, false);
    }

    public boolean isBoosting() {
        return this.entityData.get(DATA_BOOSTING);
    }

    public void setBoosting(boolean boosting) {
        this.entityData.set(DATA_BOOSTING, boosting);
    }

    public float getBoostAmount(float partialTick) {
        return Mth.lerp(partialTick, this.boostAmountO, this.boostAmount);
    }

    /** Seconds since the primary action started (with partial tick), or -1 when idle. */
    public float primaryTime(float partialTick) {
        return this.primaryTicks < 0 ? -1.0F : (this.primaryTicks + partialTick) / 20.0F;
    }

    public float secondaryTime(float partialTick) {
        return this.secondaryTicks < 0 ? -1.0F : (this.secondaryTicks + partialTick) / 20.0F;
    }

    // ================================================================= ticking

    @Override
    public void tick() {
        super.tick();
        if (this.primaryCooldown > 0) this.primaryCooldown--;
        if (this.secondaryCooldown > 0) this.secondaryCooldown--;

        Player pilot = this.getControllingPassenger() instanceof Player p ? p : null;
        boolean client = this.level().isClientSide;

        if (this.primaryTicks >= 0) {
            if (client) this.primaryClientTick(this.primaryTicks);
            else if (pilot != null) this.primaryTick(pilot, this.primaryTicks);
            if (++this.primaryTicks >= this.primaryDuration()) this.primaryTicks = -1;
        }
        if (this.secondaryTicks >= 0) {
            if (client) this.secondaryClientTick(this.secondaryTicks);
            else if (pilot != null) this.secondaryTick(pilot, this.secondaryTicks);
            if (++this.secondaryTicks >= this.secondaryDuration()) this.secondaryTicks = -1;
        }

        if (!client && pilot == null && this.isBoosting()) {
            this.setBoosting(false);
        }
        if (client) {
            this.boostAmountO = this.boostAmount;
            float target = this.isBoosting() ? 1.0F : 0.0F;
            this.boostAmount += (target - this.boostAmount) * 0.2F;
            this.ambientClientTick();
        }
    }

    // ================================================================= weapons (server)

    public void tryPrimary(Player pilot) {
        if (this.level().isClientSide || this.primaryCooldown > 0 || this.primaryTicks >= 0) return;
        this.primaryCooldown = this.primaryCooldownTicks();
        this.primaryTicks = 0;
        this.level().broadcastEntityEvent(this, EVENT_PRIMARY);
    }

    public void trySecondary(Player pilot) {
        if (this.level().isClientSide || this.secondaryCooldown > 0 || this.secondaryTicks >= 0) return;
        this.secondaryCooldown = this.secondaryCooldownTicks();
        this.secondaryTicks = 0;
        this.level().broadcastEntityEvent(this, EVENT_SECONDARY);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == EVENT_PRIMARY) {
            this.primaryTicks = 0;
        } else if (id == EVENT_SECONDARY) {
            this.secondaryTicks = 0;
        } else {
            super.handleEntityEvent(id);
        }
    }

    /** World position of a point given relative to the mecha: right, up and forward in blocks. */
    public Vec3 localPoint(double right, double up, double forward) {
        float yaw = this.getYRot() * Mth.DEG_TO_RAD;
        double fx = -Mth.sin(yaw), fz = Mth.cos(yaw);
        double rx = -Mth.cos(yaw), rz = -Mth.sin(yaw);
        return new Vec3(this.getX() + rx * right + fx * forward, this.getY() + up, this.getZ() + rz * right + fz * forward);
    }

    public Vec3 forward() {
        float yaw = this.getYRot() * Mth.DEG_TO_RAD;
        return new Vec3(-Mth.sin(yaw), 0.0, Mth.cos(yaw));
    }

    // ================================================================= riding

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        if (this.getFirstPassenger() instanceof Player player) {
            return player;
        }
        return super.getControllingPassenger();
    }

    @Override
    public void tickRidden(Player player, Vec3 travelVector) {
        super.tickRidden(player, travelVector);
        this.setRot(player.getYRot(), player.getXRot() * 0.5F);
        this.yRotO = this.getYRot();
        this.yBodyRot = this.getYRot();
        this.yHeadRot = this.getYRot();

        Vec3 motion = this.getDeltaMovement();
        if (this.canFly()) {
            if (this.isBoosting()) {
                this.setDeltaMovement(motion.x, Math.min(motion.y + 0.13, 0.55), motion.z);
                this.fallDistance = 0.0F;
            } else if (!this.onGround() && motion.y < -0.22) {
                this.setDeltaMovement(motion.x, -0.22, motion.z); // glide down
                this.fallDistance = 0.0F;
            }
        } else if (this.pendingJump > 0.0F && this.onGround()) {
            float s = this.jumpStrength();
            double up = (0.55 + 0.45 * this.pendingJump) * s;
            Vec3 fwd = this.forward().scale(0.6 * this.pendingJump * s);
            this.setDeltaMovement(motion.x + fwd.x, up, motion.z + fwd.z);
            this.hasImpulse = true;
            this.playSound(SoundEvents.PISTON_EXTEND, 1.0F, 0.6F);
            this.pendingJump = 0.0F;
        }
    }

    @Override
    public Vec3 getRiddenInput(Player player, Vec3 travelVector) {
        float strafe = player.xxa * 0.5F;
        float forward = player.zza;
        if (forward <= 0.0F) {
            forward *= 0.4F;
        }
        return new Vec3(strafe, 0.0, forward);
    }

    @Override
    public float getRiddenSpeed(Player player) {
        float speed = (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
        return this.canFly() && !this.onGround() ? speed * 1.4F : speed;
    }

    @Override
    public Vec3 getPassengerAttachmentPoint(Entity passenger, EntityDimensions dimensions, float partialTick) {
        return new Vec3(0.0, this.seatHeight(), 0.0);
    }

    @Override
    public void onPlayerJump(int jumpPower) {
        if (jumpPower < 0) jumpPower = 0;
        this.pendingJump = jumpPower >= 90 ? 1.0F : 0.4F + 0.6F * jumpPower / 90.0F;
    }

    @Override
    public boolean canJump() {
        return !this.canFly() && this.jumpStrength() > 0.0F;
    }

    @Override
    public void handleStartJump(int jumpPower) {
    }

    @Override
    public void handleStopJump() {
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        if (fallDistance > 3.0F) {
            this.playSound(SoundEvents.ANVIL_LAND, 0.6F, 0.5F);
        }
        return false;
    }

    // ================================================================= interaction

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        boolean client = this.level().isClientSide;

        if (stack.is(Items.IRON_INGOT) && this.getHealth() < this.getMaxHealth()) {
            if (!client) {
                this.heal(10.0F);
                stack.consume(1, player);
                this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, 1.0F);
            }
            return InteractionResult.sidedSuccess(client);
        }

        if (player.isSecondaryUseActive() && stack.isEmpty() && !this.isVehicle()) {
            if (!client) {
                this.spawnAtLocation(new ItemStack(this.getCoreItem()));
                this.playSound(SoundEvents.PISTON_CONTRACT, 1.0F, 0.6F);
                this.discard();
            }
            return InteractionResult.sidedSuccess(client);
        }

        if (!this.isVehicle() && !player.isSecondaryUseActive()) {
            if (!client && player.startRiding(this)) {
                player.displayClientMessage(Component.translatable(this.canFly()
                        ? "message.blockwars.controls_fly" : "message.blockwars.controls"), true);
            }
            return InteractionResult.sidedSuccess(client);
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() != null && source.getEntity() == this.getControllingPassenger()) {
            return false;
        }
        return super.hurt(source, this.modifyIncomingDamage(source, amount));
    }

    @Override
    public void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean recentlyHit) {
        super.dropCustomDeathLoot(level, source, recentlyHit);
        this.spawnAtLocation(new ItemStack(Items.IRON_INGOT, 8));
        this.spawnAtLocation(new ItemStack(Items.REDSTONE, 6));
    }

    // ================================================================= misc

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.2F, 0.6F);
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("PrimaryCooldown", this.primaryCooldown);
        tag.putInt("SecondaryCooldown", this.secondaryCooldown);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.primaryCooldown = tag.getInt("PrimaryCooldown");
        this.secondaryCooldown = tag.getInt("SecondaryCooldown");
    }
}
