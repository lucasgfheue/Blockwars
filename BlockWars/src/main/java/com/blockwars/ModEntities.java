package com.blockwars;

import com.blockwars.entity.BastionEntity;
import com.blockwars.entity.BulletEntity;
import com.blockwars.entity.GoliathEntity;
import com.blockwars.entity.MechaEntity;
import com.blockwars.entity.MissileEntity;
import com.blockwars.entity.VanguardEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, BlockWars.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<MechaEntity>> MECHA =
            ENTITY_TYPES.register("mecha", () -> EntityType.Builder.of(MechaEntity::new, MobCategory.MISC)
                    .sized(2.0F, 3.4F).fireImmune().clientTrackingRange(10).build("mecha"));

    public static final DeferredHolder<EntityType<?>, EntityType<GoliathEntity>> GOLIATH =
            ENTITY_TYPES.register("goliath", () -> EntityType.Builder.of(GoliathEntity::new, MobCategory.MISC)
                    .sized(2.4F, 3.1F).fireImmune().clientTrackingRange(10).build("goliath"));

    public static final DeferredHolder<EntityType<?>, EntityType<VanguardEntity>> VANGUARD =
            ENTITY_TYPES.register("vanguard", () -> EntityType.Builder.of(VanguardEntity::new, MobCategory.MISC)
                    .sized(2.0F, 3.9F).fireImmune().clientTrackingRange(10).build("vanguard"));

    public static final DeferredHolder<EntityType<?>, EntityType<BastionEntity>> BASTION =
            ENTITY_TYPES.register("bastion", () -> EntityType.Builder.of(BastionEntity::new, MobCategory.MISC)
                    .sized(2.6F, 3.5F).fireImmune().clientTrackingRange(10).build("bastion"));

    public static final DeferredHolder<EntityType<?>, EntityType<BulletEntity>> BULLET =
            ENTITY_TYPES.register("bullet", () -> EntityType.Builder.<BulletEntity>of(BulletEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(8).updateInterval(1).build("bullet"));

    public static final DeferredHolder<EntityType<?>, EntityType<MissileEntity>> MISSILE =
            ENTITY_TYPES.register("missile", () -> EntityType.Builder.<MissileEntity>of(MissileEntity::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F).clientTrackingRange(8).updateInterval(1).build("missile"));
}
