package com.blockwars.entity;

import com.blockwars.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/** Fast, gravity-free round fired by mecha guns. Energy rounds look like prismarine crystals. */
public class BulletEntity extends ThrowableItemProjectile {
    private float damage = 4.0F;
    private boolean energy;

    public BulletEntity(EntityType<? extends BulletEntity> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    public BulletEntity(Level level, LivingEntity owner, float damage, boolean energy) {
        super(ModEntities.BULLET.get(), owner, level);
        this.damage = damage;
        this.energy = energy;
        this.setNoGravity(true);
        this.setItem(new ItemStack(energy ? Items.PRISMARINE_CRYSTALS : Items.IRON_NUGGET));
    }

    @Override
    protected Item getDefaultItem() {
        return Items.IRON_NUGGET;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            this.level().addParticle(this.energy ? ParticleTypes.ELECTRIC_SPARK : ParticleTypes.SMOKE,
                    this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        } else if (this.tickCount > 60) {
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), this.damage);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            for (int i = 0; i < 6; i++) {
                this.level().addParticle(this.energy ? ParticleTypes.ELECTRIC_SPARK : ParticleTypes.CRIT,
                        this.getX(), this.getY(), this.getZ(),
                        (this.random.nextDouble() - 0.5) * 0.3, this.random.nextDouble() * 0.2, (this.random.nextDouble() - 0.5) * 0.3);
            }
        } else {
            super.handleEntityEvent(id);
        }
    }
}
