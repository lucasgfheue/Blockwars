package com.blockwars.entity;

import com.blockwars.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Unit 02 — Goliath. Reverse-joint heavy walker with a closed glass cockpit.
 * R: gatling burst. G: missile salvo from the shoulder pods. Frontal shield blocks 40% of damage.
 */
public class GoliathEntity extends BaseMechaEntity {
    public GoliathEntity(EntityType<? extends GoliathEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200.0)
                .add(Attributes.ARMOR, 18.0)
                .add(Attributes.ARMOR_TOUGHNESS, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.22)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.STEP_HEIGHT, 1.6)
                .add(Attributes.SAFE_FALL_DISTANCE, 12.0);
    }

    @Override
    public Item getCoreItem() {
        return ModItems.GOLIATH_CORE.get();
    }

    @Override
    protected double seatHeight() {
        return 1.9;
    }

    @Override
    protected float jumpStrength() {
        return 0.7F;
    }

    // ---- R: gatling — 8 rounds over one second
    @Override
    protected int primaryCooldownTicks() { return 30; }
    @Override
    protected int primaryDuration() { return 20; }

    @Override
    protected void primaryTick(Player pilot, int t) {
        if (t < 2 || t > 16 || t % 2 != 0) return;
        Vec3 look = pilot.getLookAngle();
        Vec3 from = this.localPoint(0.88, 1.6, 1.5);
        BulletEntity bullet = new BulletEntity(this.level(), pilot, 4.0F, false);
        bullet.setPos(from.x, from.y, from.z);
        bullet.shoot(look.x, look.y, look.z, 3.2F, 2.5F);
        this.level().addFreshEntity(bullet);
        this.level().playSound(null, from.x, from.y, from.z, SoundEvents.CROSSBOW_SHOOT, SoundSource.NEUTRAL, 1.0F, 1.6F);
    }

    @Override
    protected void primaryClientTick(int t) {
        if (t < 2 || t > 16 || t % 2 != 0) return;
        Vec3 m = this.localPoint(0.88, 1.6, 1.5);
        this.level().addParticle(ParticleTypes.SMOKE, m.x, m.y, m.z, 0, 0.03, 0);
        this.level().addParticle(ParticleTypes.FLAME, m.x, m.y, m.z, 0, 0, 0);
    }

    // ---- G: four missiles alternating between the pods
    @Override
    protected int secondaryCooldownTicks() { return 80; }
    @Override
    protected int secondaryDuration() { return 30; }

    @Override
    protected void secondaryTick(Player pilot, int t) {
        if (t < 6 || t > 18 || (t - 6) % 4 != 0) return;
        int i = (t - 6) / 4;
        double side = i % 2 == 0 ? 1.0 : -1.0;
        Vec3 from = this.localPoint(side, 2.8, 0.6);
        Vec3 dir = pilot.getLookAngle().add(0, 0.12, 0).normalize();
        MissileEntity missile = new MissileEntity(this.level(), pilot, 1.6F);
        missile.setPos(from.x, from.y, from.z);
        missile.shoot(dir.x, dir.y, dir.z, 1.1F, 1.5F);
        this.level().addFreshEntity(missile);
        this.level().playSound(null, from.x, from.y, from.z, SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.NEUTRAL, 1.5F, 0.6F);
    }

    // ---- frontal shield
    @Override
    protected float modifyIncomingDamage(DamageSource source, float amount) {
        Vec3 src = source.getSourcePosition();
        if (src != null) {
            Vec3 to = src.subtract(this.position()).multiply(1, 0, 1);
            if (to.lengthSqr() > 0.01 && to.normalize().dot(this.forward()) > 0.3) {
                this.playSound(SoundEvents.SHIELD_BLOCK, 1.0F, 0.7F);
                return amount * 0.6F;
            }
        }
        return amount;
    }
}
