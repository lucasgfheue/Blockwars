package com.blockwars.entity;

import com.blockwars.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

/** Rocket/missile that accelerates, leaves a smoke trail and explodes on impact. */
public class MissileEntity extends ThrowableItemProjectile {
    private float power = 2.0F;

    public MissileEntity(EntityType<? extends MissileEntity> type, Level level) {
        super(type, level);
    }

    public MissileEntity(Level level, LivingEntity owner, float power) {
        super(ModEntities.MISSILE.get(), owner, level);
        this.power = power;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.FIREWORK_ROCKET;
    }

    @Override
    protected double getDefaultGravity() {
        return 0.008;
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 v = this.getDeltaMovement();
        if (v.length() < 2.6) {
            this.setDeltaMovement(v.scale(1.06));
        }
        if (this.level().isClientSide) {
            this.level().addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getX(), this.getY(), this.getZ(), 0, 0.02, 0);
        } else if (this.tickCount > 120) {
            this.explode();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.explode();
        }
    }

    private void explode() {
        // Respects the mobGriefing gamerule for block damage.
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.power, Level.ExplosionInteraction.MOB);
        this.discard();
    }
}
