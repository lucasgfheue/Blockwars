package com.blockwars;

import com.blockwars.entity.BaseMechaEntity;
import com.blockwars.entity.BastionEntity;
import com.blockwars.entity.GoliathEntity;
import com.blockwars.entity.MechaEntity;
import com.blockwars.entity.VanguardEntity;
import com.blockwars.network.ModNetwork;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(BlockWars.MODID)
public class BlockWars {
    public static final String MODID = "blockwars";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKWARS_TAB =
            CREATIVE_MODE_TABS.register("blockwars", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.blockwars"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.MECHA_CORE.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.MECHA_CORE.get());
                        output.accept(ModItems.GOLIATH_CORE.get());
                        output.accept(ModItems.VANGUARD_CORE.get());
                        output.accept(ModItems.BASTION_CORE.get());
                    })
                    .build());

    public BlockWars(IEventBus modEventBus, ModContainer modContainer) {
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        modEventBus.addListener(BlockWars::registerAttributes);
        modEventBus.addListener(ModNetwork::register);

        NeoForge.EVENT_BUS.addListener(BlockWars::protectPilot);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    private static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MECHA.get(), MechaEntity.createAttributes().build());
        event.put(ModEntities.GOLIATH.get(), GoliathEntity.createAttributes().build());
        event.put(ModEntities.VANGUARD.get(), VanguardEntity.createAttributes().build());
        event.put(ModEntities.BASTION.get(), BastionEntity.createAttributes().build());
    }

    /**
     * The mecha's armored cockpit protects the pilot: damage aimed at the pilot
     * is absorbed by the mecha instead.
     */
    private static void protectPilot(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(player.getVehicle() instanceof BaseMechaEntity mecha)) return;

        DamageSource source = event.getSource();
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return; // /kill, void...

        event.setCanceled(true);
        if (source.is(DamageTypes.IN_WALL) || source.is(DamageTypeTags.IS_FALL)) return;
        if (!player.level().isClientSide) {
            mecha.hurt(source, event.getAmount());
        }
    }
}
