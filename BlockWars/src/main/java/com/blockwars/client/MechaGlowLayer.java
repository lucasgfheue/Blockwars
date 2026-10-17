package com.blockwars.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

/** Full-bright emissive layer (visors, energy blades, thruster flames, sensor lenses). */
public class MechaGlowLayer<T extends Mob, M extends EntityModel<T>> extends EyesLayer<T, M> {
    private final RenderType renderType;

    public MechaGlowLayer(RenderLayerParent<T, M> parent, ResourceLocation texture) {
        super(parent);
        this.renderType = RenderType.eyes(texture);
    }

    @Override
    public RenderType renderType() {
        return this.renderType;
    }
}
