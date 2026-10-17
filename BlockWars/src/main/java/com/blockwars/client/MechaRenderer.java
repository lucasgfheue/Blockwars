package com.blockwars.client;

import com.blockwars.BlockWars;
import com.blockwars.entity.MechaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MechaRenderer extends MobRenderer<MechaEntity, MechaModel> {
    private static final ResourceLocation TEXTURE = BlockWars.id("textures/entity/mecha.png");

    public MechaRenderer(EntityRendererProvider.Context context) {
        super(context, new MechaModel(context.bakeLayer(MechaModel.LAYER)), 1.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(MechaEntity entity) {
        return TEXTURE;
    }
}
