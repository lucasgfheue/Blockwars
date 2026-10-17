package com.blockwars.entity;

import com.blockwars.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/** Unit 01 — the original open-cockpit mecha. R: arm cannon (fireballs). SPACE: charged jump. */
public class MechaEntity extends BaseMechaEntity {
    public MechaEntity(EntityType<? extends MechaEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120.0)
                .add(Attributes.ARMOR, 12.0)
                .add(Attributes.ARMOR_TOUGHNESS, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.28)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.STEP_HEIGHT, 1.6)
                .add(Attributes.SAFE_FALL_DISTANCE, 12.0);
    }

    @Override
    public Item getCoreItem() {
        return ModItems.MECHA_CORE.get();
    }

    @Override
    protected double seatHeight() {
        return 2.75;
    }

    @Override
    public boolean hidesPilot() {
        return false; // open cockpit
    }

    @Override
    protected int primaryCooldownTicks() {
        return 10;
    }

    @Override
    protected void primaryTick(Player pilot, int t) {
        if (t != 0) return;
        Vec3 look = pilot.getLookAngle();
        Vec3 from = this.localPoint(0.95, 2.3, 0.0).add(look.scale(1.8));
        SmallFireball fireball = new SmallFireball(this.level(), pilot, look);
        fireball.setPos(from.x, from.y, from.z);
        fireball.setDeltaMovement(look.scale(1.4));
        this.level().addFreshEntity(fireball);
        this.level().playSound(null, from.x, from.y, from.z, SoundEvents.FIRECHARGE_USE, SoundSource.NEUTRAL, 1.0F, 0.7F);
    }

    /** Recoil animation counter used by the model (8 -> 0). */
    public int getFireAnim() {
        return this.primaryTicks < 0 ? 0 : Math.max(0, 8 - this.primaryTicks);
    }
}
