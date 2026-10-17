package com.blockwars.client;

import com.blockwars.BlockWars;
import com.blockwars.ModEntities;
import com.blockwars.client.model.BastionModel;
import com.blockwars.client.model.GoliathModel;
import com.blockwars.client.model.VanguardModel;
import com.blockwars.entity.BaseMechaEntity;
import com.blockwars.entity.BastionEntity;
import com.blockwars.entity.GoliathEntity;
import com.blockwars.entity.VanguardEntity;
import com.blockwars.network.MechaActionPayload;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

/** Client-only code: rendering and controls. Never loaded on dedicated servers. */
@Mod(value = BlockWars.MODID, dist = Dist.CLIENT)
public class BlockWarsClient {
    public static final KeyMapping PRIMARY_KEY = new KeyMapping(
            "key.blockwars.primary", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, "key.categories.blockwars");
    public static final KeyMapping SECONDARY_KEY = new KeyMapping(
            "key.blockwars.secondary", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, "key.categories.blockwars");

    public BlockWarsClient(IEventBus modEventBus) {
        modEventBus.addListener(BlockWarsClient::registerRenderers);
        modEventBus.addListener(BlockWarsClient::registerLayers);
        modEventBus.addListener(BlockWarsClient::registerKeys);
        NeoForge.EVENT_BUS.addListener(BlockWarsClient::onClientTick);
        NeoForge.EVENT_BUS.addListener(BlockWarsClient::hidePilot);
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.MECHA.get(), MechaRenderer::new);
        event.registerEntityRenderer(ModEntities.GOLIATH.get(), ctx -> new HDMechaRenderer<GoliathEntity, GoliathModel>(ctx,
                new GoliathModel(ctx.bakeLayer(GoliathModel.LAYER)), 1.4F,
                BlockWars.id("textures/entity/goliath.png"), null, 1.0F));
        event.registerEntityRenderer(ModEntities.VANGUARD.get(), ctx -> new HDMechaRenderer<VanguardEntity, VanguardModel>(ctx,
                new VanguardModel(ctx.bakeLayer(VanguardModel.LAYER)), 1.2F,
                BlockWars.id("textures/entity/vanguard.png"), BlockWars.id("textures/entity/vanguard_glow.png"), 0.5F));
        event.registerEntityRenderer(ModEntities.BASTION.get(), ctx -> new HDMechaRenderer<BastionEntity, BastionModel>(ctx,
                new BastionModel(ctx.bakeLayer(BastionModel.LAYER)), 1.5F,
                BlockWars.id("textures/entity/bastion.png"), BlockWars.id("textures/entity/bastion_glow.png"), 0.5F));
        event.registerEntityRenderer(ModEntities.BULLET.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.MISSILE.get(), ThrownItemRenderer::new);
    }

    private static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MechaModel.LAYER, MechaModel::createBodyLayer);
        event.registerLayerDefinition(GoliathModel.LAYER, GoliathModel::createBodyLayer);
        event.registerLayerDefinition(VanguardModel.LAYER, VanguardModel::createBodyLayer);
        event.registerLayerDefinition(BastionModel.LAYER, BastionModel::createBodyLayer);
    }

    private static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(PRIMARY_KEY);
        event.register(SECONDARY_KEY);
    }

    private static void send(byte action) {
        PacketDistributor.sendToServer(new MechaActionPayload(action));
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        BaseMechaEntity mecha = mc.player != null && mc.player.getVehicle() instanceof BaseMechaEntity m ? m : null;

        while (PRIMARY_KEY.consumeClick()) {
            if (mecha != null) send(MechaActionPayload.PRIMARY);
        }
        while (SECONDARY_KEY.consumeClick()) {
            if (mecha != null) send(MechaActionPayload.SECONDARY);
        }

        // Thrusters: hold SPACE while piloting a mecha that can fly
        if (mecha != null && mecha.canFly() && mc.screen == null) {
            boolean held = mc.options.keyJump.isDown();
            if (held != mecha.isBoosting()) {
                mecha.setBoosting(held); // the pilot's client moves the mecha, so apply it locally too
                send(held ? MechaActionPayload.BOOST_ON : MechaActionPayload.BOOST_OFF);
            }
        }
    }

    /** Pilots inside closed cockpits are not rendered. */
    private static void hidePilot(RenderLivingEvent.Pre<?, ?> event) {
        if (event.getEntity().getVehicle() instanceof BaseMechaEntity mecha && mecha.hidesPilot()) {
            event.setCanceled(true);
        }
    }
}
