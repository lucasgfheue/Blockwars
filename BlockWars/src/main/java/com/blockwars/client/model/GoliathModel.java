package com.blockwars.client.model;

import com.blockwars.BlockWars;
import com.blockwars.client.MechaAnim;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import com.blockwars.entity.GoliathEntity;

/** Generated from the BlockWars design files. Geometry and UVs match the textures exactly. */
@SuppressWarnings("unused")
public class GoliathModel extends HierarchicalModel<GoliathEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(BlockWars.id("goliath"), "main");
    private static final float S = 1F;
    private final ModelPart root;
    public final ModelPart body;
    public final ModelPart right_arm;
    public final ModelPart left_arm;
    public final ModelPart right_thigh;
    public final ModelPart right_shin;
    public final ModelPart right_foot;
    public final ModelPart left_thigh;
    public final ModelPart left_shin;
    public final ModelPart left_foot;

    public GoliathModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.root = root;
        this.body = root.getChild("body");
        this.right_arm = this.body.getChild("right_arm");
        this.left_arm = this.body.getChild("left_arm");
        this.right_thigh = this.body.getChild("right_thigh");
        this.right_shin = this.right_thigh.getChild("right_shin");
        this.right_foot = this.right_shin.getChild("right_foot");
        this.left_thigh = this.body.getChild("left_thigh");
        this.left_shin = this.left_thigh.getChild("left_shin");
        this.left_foot = this.left_shin.getChild("left_foot");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition p_body = root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(40, 0).addBox(-10F, -17F, -9F, 20F, 14F, 18F)
                .texOffs(0, 83).addBox(-8F, -3F, -7F, 16F, 4F, 14F)
                .texOffs(60, 83).addBox(-7F, -25F, -11F, 14F, 8F, 9F)
                .texOffs(84, 101).addBox(-4F, -28F, -4F, 8F, 3F, 7F)
                .texOffs(0, 63).addBox(-8F, -19F, 9F, 16F, 14F, 6F)
                .texOffs(44, 101).addBox(-7F, -27F, 11F, 4F, 10F, 4F)
                .texOffs(44, 101).addBox(3F, -27F, 11F, 4F, 10F, 4F)
                .texOffs(46, 40).addBox(-21F, -25F, -7F, 10F, 8F, 13F)
                .texOffs(46, 40).mirror().addBox(11F, -25F, -7F, 10F, 8F, 13F).mirror(false)
                .texOffs(60, 101).addBox(-14F, -6F, -4F, 4F, 6F, 8F)
                .texOffs(60, 101).mirror().addBox(10F, -6F, -4F, 4F, 6F, 8F).mirror(false),
                PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0F));
        PartDefinition p_right_arm = p_body.addOrReplaceChild("right_arm", CubeListBuilder.create()
                .texOffs(106, 83).addBox(-2.5F, -2F, -2.5F, 5F, 12F, 5F)
                .texOffs(0, 40).addBox(-3.5F, 9F, -13F, 7F, 7F, 16F)
                .texOffs(24, 117).addBox(-2.5F, 10F, -19F, 2F, 2F, 6F)
                .texOffs(24, 117).addBox(0.5F, 10F, -19F, 2F, 2F, 6F)
                .texOffs(24, 117).addBox(-1F, 13F, -19F, 2F, 2F, 6F),
                PartPose.offsetAndRotation(-14F, -14F, 0F, 0F, 0F, 0F));
        PartDefinition p_left_arm = p_body.addOrReplaceChild("left_arm", CubeListBuilder.create()
                .texOffs(106, 83).mirror().addBox(-2.5F, -2F, -2.5F, 5F, 12F, 5F).mirror(false)
                .texOffs(0, 0).addBox(3F, -4F, -10F, 2F, 22F, 18F),
                PartPose.offsetAndRotation(14F, -14F, 0F, 0F, 0F, 0F));
        PartDefinition p_right_thigh = p_body.addOrReplaceChild("right_thigh", CubeListBuilder.create()
                .texOffs(64, 63).addBox(-3F, 0F, -3F, 6F, 13F, 6F),
                PartPose.offsetAndRotation(-12F, -3F, 0F, -0.55F, 0F, 0F));
        PartDefinition p_right_shin = p_right_thigh.addOrReplaceChild("right_shin", CubeListBuilder.create()
                .texOffs(44, 63).addBox(-2.5F, 0F, -2.5F, 5F, 15F, 5F)
                .texOffs(0, 117).addBox(-3F, -2F, -3F, 6F, 4F, 6F),
                PartPose.offsetAndRotation(0F, 12F, 0F, 1.15F, 0F, 0F));
        PartDefinition p_right_foot = p_right_shin.addOrReplaceChild("right_foot", CubeListBuilder.create()
                .texOffs(0, 101).addBox(-4.5F, 0F, -7F, 9F, 3F, 13F)
                .texOffs(40, 117).addBox(-1.5F, 0F, 4F, 3F, 2F, 4F),
                PartPose.offsetAndRotation(0F, 14F, 0F, -0.6F, 0F, 0F));
        PartDefinition p_left_thigh = p_body.addOrReplaceChild("left_thigh", CubeListBuilder.create()
                .texOffs(64, 63).mirror().addBox(-3F, 0F, -3F, 6F, 13F, 6F).mirror(false),
                PartPose.offsetAndRotation(12F, -3F, 0F, -0.55F, 0F, 0F));
        PartDefinition p_left_shin = p_left_thigh.addOrReplaceChild("left_shin", CubeListBuilder.create()
                .texOffs(44, 63).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 15F, 5F).mirror(false)
                .texOffs(0, 117).mirror().addBox(-3F, -2F, -3F, 6F, 4F, 6F).mirror(false),
                PartPose.offsetAndRotation(0F, 12F, 0F, 1.15F, 0F, 0F));
        PartDefinition p_left_foot = p_left_shin.addOrReplaceChild("left_foot", CubeListBuilder.create()
                .texOffs(0, 101).mirror().addBox(-4.5F, 0F, -7F, 9F, 3F, 13F).mirror(false)
                .texOffs(40, 117).mirror().addBox(-1.5F, 0F, 4F, 3F, 2F, 4F).mirror(false),
                PartPose.offsetAndRotation(0F, 14F, 0F, -0.6F, 0F, 0F));
        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(GoliathEntity e, float limbSwing, float limbSwingAmount, float age, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        float pt = age - e.tickCount;
        float a = Math.min(1.0F, limbSwingAmount * 1.3F);
        float ph = limbSwing * 0.3F;

        // idle: hydraulic breathing
        this.body.y += Mth.sin(age * 0.08F) * 0.3F;
        this.left_arm.yRot += -0.12F;

        // reverse-joint walk
        float th = Mth.cos(ph) * 0.45F * a;
        float kr = Math.max(0.0F, Mth.sin(ph)) * 0.55F * a;
        float kl = Math.max(0.0F, -Mth.sin(ph)) * 0.55F * a;
        this.right_thigh.xRot += th;
        this.left_thigh.xRot -= th;
        this.right_shin.xRot += kr;
        this.left_shin.xRot += kl;
        this.right_foot.xRot -= th + kr;
        this.left_foot.xRot -= -th + kl;
        this.body.zRot += Mth.sin(ph) * 0.04F * a;
        this.body.yRot += Mth.cos(ph) * 0.05F * a;
        this.right_arm.xRot += -Mth.cos(ph) * 0.15F * a;
        this.left_arm.xRot += Mth.cos(ph) * 0.2F * a;

        // gatling burst: arm braced forward, rattling recoil
        float g = e.primaryTime(pt);
        if (g >= 0.0F) {
            float aim = MechaAnim.ease(g / 0.15F) * (1.0F - MechaAnim.ease((g - 0.85F) / 0.2F));
            this.right_arm.xRot += -0.25F * aim + Mth.sin(g * 70.0F) * 0.035F * aim;
            this.right_arm.z += Math.abs(Mth.sin(g * 70.0F)) * 0.8F * aim;
            this.body.yRot += 0.08F * aim;
        }
        // missile salvo: hull kicks back with each launch
        float m = e.secondaryTime(pt);
        if (m >= 0.0F) {
            float kick = 0.0F;
            for (int i = 0; i < 4; i++) {
                float tl = 0.3F + i * 0.2F;
                if (m >= tl) kick = Math.max(kick, (float) Math.exp(-(m - tl) * 12.0F));
            }
            this.body.xRot += -0.06F * kick;
            this.left_arm.xRot += -0.4F * MechaAnim.ease(m / 0.2F) * (1.0F - MechaAnim.ease((m - 1.2F) / 0.3F));
        }
    }

}
