package com.blockwars.entity;

import com.blockwars.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Unit 03 — Vanguard. Agile assault mecha with thrusters.
 * R: energy sword slash (wide arc). G: wrist blaster. Hold SPACE: fly with the thrusters.
 */
public class VanguardEntity extends BaseMechaEntity {
    /** Tick of the slash at which the blade connects (matches the model animation). */
    private static final int STRIKE_TICK = 12;

    public VanguardEntity(EntityType<? extends VanguardEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150.0)
                .add(Attributes.ARMOR, 14.0)
                .add(Attributes.ARMOR_TOUGHNESS, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.32)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.9)
                .add(Attributes.STEP_HEIGHT, 1.6)
                .add(Attributes.SAFE_FALL_DISTANCE, 30.0);
    }

    @Override
    public Item getCoreItem() {
        return ModItems.VANGUARD_CORE.get();
    }

    @Override
    protected double seatHeight() {
        return 2.42;
    }

    @Override
    public boolean canFly() {
        return true;
    }

    // ---- R: energy sword
    @Override
    protected int primaryCooldownTicks() { return 34; }
    @Override
    protected int primaryDuration() { return 32; }

    @Override
    protected void primaryTick(Player pilot, int t) {
        if (t == 2) {
            this.level().playSound(null, this.getX(), this.getY() + 2, this.getZ(), SoundEvents.BEACON_ACTIVATE, SoundSource.NEUTRAL, 0.6F, 2.0F);
        }
        if (t != STRIKE_TICK) return;
        Vec3 fwd = this.forward();
        this.level().playSound(null, this.getX(), this.getY() + 2, this.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.NEUTRAL, 1.5F, 0.6F);
        for (LivingEntity target : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.5, 1.5, 4.5),
                e -> e != this && e != pilot && e.isAlive() && e.getVehicle() != this)) {
            Vec3 to = target.position().subtract(this.position()).multiply(1, 0, 1);
            double dist = to.length();
            if (dist > 5.5 || (dist > 0.5 && to.normalize().dot(fwd) < 0.2)) continue;
            target.hurt(this.damageSources().playerAttack(pilot), 16.0F);
            target.knockback(1.2, -fwd.x, -fwd.z);
        }
        if (this.level() instanceof ServerLevel server) {
            for (int i = -2; i <= 2; i++) {
                Vec3 p = this.localPoint(i * 0.8, 1.8, 2.2);
                server.sendParticles(ParticleTypes.SWEEP_ATTACK, p.x, p.y, p.z, 1, 0, 0, 0, 0);
            }
        }
    }

    @Override
    protected void primaryClientTick(int t) {
        if (t >= 7 && t <= 13) {
            Vec3 p = this.localPoint(0.6 - (t - 7) * 0.25, 3.6 - (t - 7) * 0.4, 1.6);
            for (int i = 0; i < 4; i++) {
                this.level().addParticle(ParticleTypes.ELECTRIC_SPARK, p.x, p.y, p.z,
                        (this.random.nextDouble() - 0.5) * 0.2, (this.random.nextDouble() - 0.5) * 0.2, (this.random.nextDouble() - 0.5) * 0.2);
            }
        }
    }

    // ---- G: wrist blaster
    @Override
    protected int secondaryCooldownTicks() { return 8; }
    @Override
    protected int secondaryDuration() { return 10; }

    @Override
    protected void secondaryTick(Player pilot, int t) {
        if (t != 3) return;
        Vec3 look = pilot.getLookAngle();
        Vec3 from = this.localPoint(-0.75, 2.6, 1.1);
        BulletEntity bolt = new BulletEntity(this.level(), pilot, 6.0F, true);
        bolt.setPos(from.x, from.y, from.z);
        bolt.shoot(look.x, look.y, look.z, 3.0F, 0.5F);
        this.level().addFreshEntity(bolt);
        this.level().playSound(null, from.x, from.y, from.z, SoundEvents.BEACON_DEACTIVATE, SoundSource.NEUTRAL, 0.8F, 2.0F);
    }

    // ---- thruster flames
    @Override
    protected void ambientClientTick() {
        float lift = this.getBoostAmount(1.0F);
        if (lift > 0.2F) {
            for (double side : new double[]{0.25, -0.25}) {
                Vec3 p = this.localPoint(side, 1.75, -0.6);
                this.level().addParticle(ParticleTypes.FLAME, p.x, p.y, p.z, 0, -0.25 * lift, 0);
                if (this.random.nextFloat() < 0.4F) {
                    this.level().addParticle(ParticleTypes.SMOKE, p.x, p.y - 0.3, p.z, 0, -0.05, 0);
                }
            }
        }
    }
}
