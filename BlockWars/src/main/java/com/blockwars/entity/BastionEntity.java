package com.blockwars.entity;

import com.blockwars.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Unit 04 — Bastion. Heavy assault walker.
 * R: 6-round autocannon burst. G: 6-rocket salvo from the shoulder pod.
 */
public class BastionEntity extends BaseMechaEntity {
    /** Seconds into the burst/salvo at which each shot leaves the barrel (shared with the model). */
    public static final float[] SHOT_TIMES = {0.45F, 0.6F, 0.75F, 0.9F, 1.05F, 1.2F};
    public static final float[] ROCKET_TIMES = {0.7F, 0.83F, 0.96F, 1.09F, 1.22F, 1.35F};
    private static final double[][] TUBES = {{-0.14, 3.25}, {-0.33, 3.25}, {-0.52, 3.25}, {-0.14, 3.45}, {-0.33, 3.45}, {-0.52, 3.45}};

    public BastionEntity(EntityType<? extends BastionEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 260.0)
                .add(Attributes.ARMOR, 22.0)
                .add(Attributes.ARMOR_TOUGHNESS, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.19)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.STEP_HEIGHT, 1.6)
                .add(Attributes.SAFE_FALL_DISTANCE, 10.0);
    }

    @Override
    public Item getCoreItem() {
        return ModItems.BASTION_CORE.get();
    }

    @Override
    protected double seatHeight() {
        return 1.75;
    }

    @Override
    protected float jumpStrength() {
        return 0.55F;
    }

    private static boolean isEventTick(float[] times, int t) {
        for (float s : times) {
            if (Math.round(s * 20.0F) == t) return true;
        }
        return false;
    }

    private static int eventIndex(float[] times, int t) {
        for (int i = 0; i < times.length; i++) {
            if (Math.round(times[i] * 20.0F) == t) return i;
        }
        return -1;
    }

    private Vec3 muzzle() {
        return this.localPoint(0.88, 2.06, 1.95);
    }

    // ---- R: autocannon
    @Override
    protected int primaryCooldownTicks() { return 44; }
    @Override
    protected int primaryDuration() { return 40; }

    @Override
    protected void primaryTick(Player pilot, int t) {
        if (!isEventTick(SHOT_TIMES, t)) return;
        Vec3 look = pilot.getLookAngle();
        Vec3 from = this.muzzle();
        BulletEntity shell = new BulletEntity(this.level(), pilot, 8.0F, false);
        shell.setPos(from.x, from.y, from.z);
        shell.shoot(look.x, look.y, look.z, 3.6F, 1.2F);
        this.level().addFreshEntity(shell);
        this.level().playSound(null, from.x, from.y, from.z, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.NEUTRAL, 0.6F, 1.8F);
    }

    @Override
    protected void primaryClientTick(int t) {
        if (!isEventTick(SHOT_TIMES, t)) return;
        Vec3 m = this.muzzle();
        Vec3 f = this.forward();
        for (int i = 0; i < 4; i++) {
            this.level().addParticle(ParticleTypes.LARGE_SMOKE, m.x, m.y, m.z, f.x * 0.15, 0.03, f.z * 0.15);
        }
        this.level().addParticle(ParticleTypes.FLASH, m.x, m.y, m.z, 0, 0, 0);
        // eject a brass casing from the right side of the gun
        Vec3 ej = this.localPoint(1.15, 2.1, 0.8);
        Vec3 right = this.localPoint(1.0, 0, 0).subtract(this.position());
        this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.GOLD_NUGGET)),
                ej.x, ej.y, ej.z, right.x * 0.15, 0.25, right.z * 0.15);
    }

    // ---- G: rocket salvo
    @Override
    protected int secondaryCooldownTicks() { return 100; }
    @Override
    protected int secondaryDuration() { return 52; }

    @Override
    protected void secondaryTick(Player pilot, int t) {
        int i = eventIndex(ROCKET_TIMES, t);
        if (i < 0) return;
        Vec3 from = this.localPoint(TUBES[i][0] - 0.6, TUBES[i][1], 0.55);
        Vec3 dir = pilot.getLookAngle().add(0, 0.35, 0).normalize();
        MissileEntity rocket = new MissileEntity(this.level(), pilot, 2.2F);
        rocket.setPos(from.x, from.y, from.z);
        rocket.shoot(dir.x, dir.y, dir.z, 0.9F, 2.0F);
        this.level().addFreshEntity(rocket);
        this.level().playSound(null, from.x, from.y, from.z, SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.NEUTRAL, 2.0F, 0.5F);
    }

    @Override
    protected void secondaryClientTick(int t) {
        int i = eventIndex(ROCKET_TIMES, t);
        if (i < 0) return;
        Vec3 back = this.localPoint(-0.95, 3.2, -0.5);
        for (int k = 0; k < 6; k++) {
            this.level().addParticle(ParticleTypes.CLOUD, back.x, back.y, back.z,
                    (this.random.nextDouble() - 0.5) * 0.15, 0.05, (this.random.nextDouble() - 0.5) * 0.15);
        }
    }

    // ---- diesel exhaust from the stacks
    @Override
    protected void ambientClientTick() {
        if (this.tickCount % 3 != 0) return;
        for (double side : new double[]{0.31, -0.31}) {
            Vec3 p = this.localPoint(side, 3.6, -0.8);
            this.level().addParticle(ParticleTypes.LARGE_SMOKE, p.x, p.y, p.z, 0, 0.04, 0);
        }
    }
}
