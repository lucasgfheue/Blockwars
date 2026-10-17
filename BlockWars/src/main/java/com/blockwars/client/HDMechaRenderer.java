package com.blockwars.client;

import com.blockwars.entity.BaseMechaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

/**
 * Renderer for the detailed mechas. Models built at 2x resolution are drawn with modelScale = 0.5
 * so their textures get twice the usual pixel density.
 */
public class HDMechaRenderer<T extends BaseMechaEntity, M extends EntityModel<T>> extends MobRenderer<T, M> {
    private final ResourceLocation texture;
    private final float modelScale;

    public HDMechaRenderer(EntityRendererProvider.Context context, M model, float shadow, ResourceLocation texture,
                           @Nullable ResourceLocation glow, float modelScale) {
        super(context, model, shadow);
        this.texture = texture;
        this.modelScale = modelScale;
        if (glow != null) {
            this.addLayer(new MechaGlowLayer<>(this, glow));
        }
    }

    @Override
    protected void scale(T entity, PoseStack poseStack, float partialTick) {
        if (this.modelScale != 1.0F) {
            poseStack.scale(this.modelScale, this.modelScale, this.modelScale);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return this.texture;
    }
}
