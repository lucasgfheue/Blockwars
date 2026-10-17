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
import com.blockwars.entity.VanguardEntity;

/** Generated from the BlockWars design files. Geometry and UVs match the textures exactly. */
@SuppressWarnings("unused")
public class VanguardModel extends HierarchicalModel<VanguardEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(BlockWars.id("vanguard"), "main");
    private static final float S = 2F;
    private final ModelPart root;
    public final ModelPart waist;
    public final ModelPart skirt_f;
    public final ModelPart skirt_b;
    public final ModelPart skirt_r;
    public final ModelPart skirt_l;
    public final ModelPart chest;
    public final ModelPart head;
    public final ModelPart crest;
    public final ModelPart horn_r;
    public final ModelPart horn_l;
    public final ModelPart pack;
    public final ModelPart flame_r;
    public final ModelPart flame_l;
    public final ModelPart wing_r;
    public final ModelPart wing_l;
    public final ModelPart wing2_r;
    public final ModelPart wing2_l;
    public final ModelPart shoulder_r;
    public final ModelPart forearm_r;
    public final ModelPart shoulder_l;
    public final ModelPart forearm_l;
    public final ModelPart sword;
    public final ModelPart blaster;
    public final ModelPart thigh_r;
    public final ModelPart shin_r;
    public final ModelPart foot_r;
    public final ModelPart thigh_l;
    public final ModelPart shin_l;
    public final ModelPart foot_l;

    public VanguardModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.root = root;
        this.waist = root.getChild("waist");
        this.skirt_f = this.waist.getChild("skirt_f");
        this.skirt_b = this.waist.getChild("skirt_b");
        this.skirt_r = this.waist.getChild("skirt_r");
        this.skirt_l = this.waist.getChild("skirt_l");
        this.chest = this.waist.getChild("chest");
        this.head = this.chest.getChild("head");
        this.crest = this.head.getChild("crest");
        this.horn_r = this.head.getChild("horn_r");
        this.horn_l = this.head.getChild("horn_l");
        this.pack = this.chest.getChild("pack");
        this.flame_r = this.pack.getChild("flame_r");
        this.flame_l = this.pack.getChild("flame_l");
        this.wing_r = this.pack.getChild("wing_r");
        this.wing_l = this.pack.getChild("wing_l");
        this.wing2_r = this.pack.getChild("wing2_r");
        this.wing2_l = this.pack.getChild("wing2_l");
        this.shoulder_r = this.chest.getChild("shoulder_r");
        this.forearm_r = this.shoulder_r.getChild("forearm_r");
        this.shoulder_l = this.chest.getChild("shoulder_l");
        this.forearm_l = this.shoulder_l.getChild("forearm_l");
        this.sword = this.forearm_r.getChild("sword");
        this.blaster = this.forearm_l.getChild("blaster");
        this.thigh_r = root.getChild("thigh_r");
        this.shin_r = this.thigh_r.getChild("shin_r");
        this.foot_r = this.shin_r.getChild("foot_r");
        this.thigh_l = root.getChild("thigh_l");
        this.shin_l = this.thigh_l.getChild("shin_l");
        this.foot_l = this.shin_l.getChild("foot_l");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition p_waist = root.addOrReplaceChild("waist", CubeListBuilder.create()
                .texOffs(0, 140).addBox(-10F, -4F, -7F, 20F, 10F, 14F)
                .texOffs(152, 114).addBox(-12F, -2F, -9F, 24F, 6F, 18F)
                .texOffs(212, 182).addBox(-3F, -3F, -10F, 6F, 6F, 2F),
                PartPose.offsetAndRotation(0F, -36F, 0F, 0F, 0F, 0F));
        PartDefinition p_skirt_f = p_waist.addOrReplaceChild("skirt_f", CubeListBuilder.create()
                .texOffs(188, 164).addBox(-8F, 0F, -2F, 16F, 12F, 2F),
                PartPose.offsetAndRotation(0F, 2F, -9F, -0.12F, 0F, 0F));
        PartDefinition p_skirt_b = p_waist.addOrReplaceChild("skirt_b", CubeListBuilder.create()
                .texOffs(188, 164).addBox(-8F, 0F, 0F, 16F, 12F, 2F),
                PartPose.offsetAndRotation(0F, 2F, 9F, 0.12F, 0F, 0F));
        PartDefinition p_skirt_r = p_waist.addOrReplaceChild("skirt_r", CubeListBuilder.create()
                .texOffs(0, 114).addBox(-2F, 0F, -7F, 2F, 12F, 14F),
                PartPose.offsetAndRotation(-12F, 2F, 0F, 0F, 0F, 0.18F));
        PartDefinition p_skirt_l = p_waist.addOrReplaceChild("skirt_l", CubeListBuilder.create()
                .texOffs(0, 114).mirror().addBox(0F, 0F, -7F, 2F, 12F, 14F).mirror(false),
                PartPose.offsetAndRotation(12F, 2F, 0F, 0F, 0F, -0.18F));
        PartDefinition p_chest = p_waist.addOrReplaceChild("chest", CubeListBuilder.create()
                .texOffs(68, 140).addBox(-9F, -10F, -6F, 18F, 10F, 12F)
                .texOffs(152, 182).addBox(-7F, -10F, -7F, 14F, 8F, 2F)
                .texOffs(52, 0).addBox(-18F, -32F, -12F, 36F, 22F, 24F)
                .texOffs(0, 54).addBox(-19F, -24F, -13F, 38F, 6F, 26F)
                .texOffs(104, 182).addBox(-5F, -28F, -14F, 10F, 10F, 2F)
                .texOffs(28, 195).addBox(-12F, -32F, -13F, 8F, 4F, 2F)
                .texOffs(28, 195).addBox(4F, -32F, -13F, 8F, 4F, 2F)
                .texOffs(64, 114).addBox(-12F, -36F, -10F, 24F, 4F, 20F)
                .texOffs(192, 182).addBox(-16F, -18F, -13F, 8F, 6F, 2F)
                .texOffs(192, 182).addBox(8F, -18F, -13F, 8F, 6F, 2F),
                PartPose.offsetAndRotation(0F, -4F, 0F, 0F, 0F, 0F));
        PartDefinition p_head = p_chest.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(44, 182).addBox(-4F, -4F, -4F, 8F, 4F, 8F)
                .texOffs(132, 86).addBox(-7F, -16F, -8F, 14F, 12F, 16F)
                .texOffs(228, 182).addBox(-6F, -12F, -9F, 12F, 4F, 2F)
                .texOffs(72, 195).addBox(-6F, -6F, -9F, 12F, 3F, 2F)
                .texOffs(48, 195).addBox(-2F, -18F, -9.2F, 4F, 4F, 2F)
                .texOffs(88, 164).addBox(-8F, -13F, -4F, 2F, 6F, 10F)
                .texOffs(88, 164).mirror().addBox(6F, -13F, -4F, 2F, 6F, 10F).mirror(false),
                PartPose.offsetAndRotation(0F, -36F, 0F, 0F, 0F, 0F));
        PartDefinition p_crest = p_head.addOrReplaceChild("crest", CubeListBuilder.create()
                .texOffs(148, 140).addBox(-1F, -16F, -2F, 2F, 16F, 6F),
                PartPose.offsetAndRotation(0F, -16F, -2F, -0.85F, 0F, 0F));
        PartDefinition p_horn_r = p_head.addOrReplaceChild("horn_r", CubeListBuilder.create()
                .texOffs(128, 182).addBox(-1F, -1F, 0F, 2F, 2F, 10F),
                PartPose.offsetAndRotation(-7F, -12F, 2F, 0.5F, 0.6F, 0F));
        PartDefinition p_horn_l = p_head.addOrReplaceChild("horn_l", CubeListBuilder.create()
                .texOffs(128, 182).mirror().addBox(-1F, -1F, 0F, 2F, 2F, 10F).mirror(false),
                PartPose.offsetAndRotation(7F, -12F, 2F, 0.5F, -0.6F, 0F));
        PartDefinition p_pack = p_chest.addOrReplaceChild("pack", CubeListBuilder.create()
                .texOffs(68, 86).addBox(-12F, -8F, 0F, 24F, 20F, 8F)
                .texOffs(164, 140).addBox(-13F, 10F, 1F, 10F, 10F, 10F)
                .texOffs(164, 140).mirror().addBox(3F, 10F, 1F, 10F, 10F, 10F).mirror(false),
                PartPose.offsetAndRotation(0F, -24F, 12F, 0F, 0F, 0F));
        PartDefinition p_flame_r = p_pack.addOrReplaceChild("flame_r", CubeListBuilder.create()
                .texOffs(32, 164).addBox(-3F, 0F, -3F, 6F, 12F, 6F),
                PartPose.offsetAndRotation(-8F, 20F, 6F, 0F, 0F, 0F));
        PartDefinition p_flame_l = p_pack.addOrReplaceChild("flame_l", CubeListBuilder.create()
                .texOffs(32, 164).mirror().addBox(-3F, 0F, -3F, 6F, 12F, 6F).mirror(false),
                PartPose.offsetAndRotation(8F, 20F, 6F, 0F, 0F, 0F));
        PartDefinition p_wing_r = p_pack.addOrReplaceChild("wing_r", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-2F, -40F, 0F, 4F, 40F, 14F),
                PartPose.offsetAndRotation(-8F, -6F, 6F, 0.45F, 0.3F, -0.6F));
        PartDefinition p_wing_l = p_pack.addOrReplaceChild("wing_l", CubeListBuilder.create()
                .texOffs(0, 0).mirror().addBox(-2F, -40F, 0F, 4F, 40F, 14F).mirror(false),
                PartPose.offsetAndRotation(8F, -6F, 6F, 0.45F, -0.3F, 0.6F));
        PartDefinition p_wing2_r = p_pack.addOrReplaceChild("wing2_r", CubeListBuilder.create()
                .texOffs(172, 0).addBox(-2F, -28F, 0F, 4F, 28F, 10F),
                PartPose.offsetAndRotation(-8F, 0F, 6F, 0.6F, 0.35F, -1.15F));
        PartDefinition p_wing2_l = p_pack.addOrReplaceChild("wing2_l", CubeListBuilder.create()
                .texOffs(172, 0).mirror().addBox(-2F, -28F, 0F, 4F, 28F, 10F).mirror(false),
                PartPose.offsetAndRotation(8F, 0F, 6F, 0.6F, -0.35F, 1.15F));
        PartDefinition p_shoulder_r = p_chest.addOrReplaceChild("shoulder_r", CubeListBuilder.create()
                .texOffs(32, 114).addBox(-4F, -2F, -4F, 8F, 18F, 8F)
                .texOffs(128, 54).addBox(-9F, -8F, -9F, 16F, 12F, 18F)
                .texOffs(204, 140).addBox(-10F, -9F, -8F, 4F, 4F, 16F),
                PartPose.offsetAndRotation(-24F, -26F, 0F, 0F, 0F, 0F));
        PartDefinition p_forearm_r = p_shoulder_r.addOrReplaceChild("forearm_r", CubeListBuilder.create()
                .texOffs(192, 86).addBox(-5F, 0F, -6F, 10F, 14F, 12F)
                .texOffs(148, 164).addBox(-5F, 12F, -5F, 10F, 4F, 10F)
                .texOffs(224, 164).addBox(-4F, 16F, -4F, 8F, 6F, 8F),
                PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 0F, 0F));
        PartDefinition p_shoulder_l = p_chest.addOrReplaceChild("shoulder_l", CubeListBuilder.create()
                .texOffs(32, 114).mirror().addBox(-4F, -2F, -4F, 8F, 18F, 8F).mirror(false)
                .texOffs(128, 54).addBox(-7F, -8F, -9F, 16F, 12F, 18F)
                .texOffs(204, 140).mirror().addBox(6F, -9F, -8F, 4F, 4F, 16F).mirror(false),
                PartPose.offsetAndRotation(24F, -26F, 0F, 0F, 0F, 0F));
        PartDefinition p_forearm_l = p_shoulder_l.addOrReplaceChild("forearm_l", CubeListBuilder.create()
                .texOffs(192, 86).mirror().addBox(-5F, 0F, -6F, 10F, 14F, 12F).mirror(false)
                .texOffs(148, 164).mirror().addBox(-5F, 12F, -5F, 10F, 4F, 10F).mirror(false)
                .texOffs(224, 164).mirror().addBox(-4F, 16F, -4F, 8F, 6F, 8F).mirror(false),
                PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 0F, 0F));
        PartDefinition p_sword = p_forearm_r.addOrReplaceChild("sword", CubeListBuilder.create()
                .texOffs(184, 182).addBox(-1F, -6F, -1F, 2F, 8F, 2F)
                .texOffs(0, 195).addBox(-5F, 2F, -2F, 10F, 2F, 4F)
                .texOffs(36, 0).addBox(-1F, 4F, -3F, 2F, 48F, 6F),
                PartPose.offsetAndRotation(0F, 19F, -1F, -1.25F, 0F, 0F));
        PartDefinition p_blaster = p_forearm_l.addOrReplaceChild("blaster", CubeListBuilder.create()
                .texOffs(56, 164).addBox(-3F, -2F, -8F, 6F, 6F, 10F)
                .texOffs(60, 195).addBox(-2F, -1F, -9F, 4F, 4F, 2F),
                PartPose.offsetAndRotation(0F, 6F, -6F, 0F, 0F, 0F));
        PartDefinition p_thigh_r = root.addOrReplaceChild("thigh_r", CubeListBuilder.create()
                .texOffs(196, 54).addBox(-5F, 0F, -5F, 10F, 20F, 10F)
                .texOffs(0, 164).addBox(-6F, 1F, -7F, 12F, 14F, 4F)
                .texOffs(128, 140).addBox(-6F, 2F, -4F, 2F, 14F, 8F),
                PartPose.offsetAndRotation(-8F, -32F, 0F, 0F, 0F, 0F));
        PartDefinition p_shin_r = p_thigh_r.addOrReplaceChild("shin_r", CubeListBuilder.create()
                .texOffs(200, 0).addBox(-6F, 0F, -6F, 12F, 24F, 12F)
                .texOffs(76, 182).addBox(-5F, -4F, -8F, 10F, 8F, 4F)
                .texOffs(136, 164).addBox(-2F, 6F, -7F, 4F, 14F, 2F)
                .texOffs(112, 164).addBox(-4F, 8F, 5F, 8F, 12F, 4F),
                PartPose.offsetAndRotation(0F, 20F, 0F, 0F, 0F, 0F));
        PartDefinition p_foot_r = p_shin_r.addOrReplaceChild("foot_r", CubeListBuilder.create()
                .texOffs(44, 182).addBox(-4F, -2F, -4F, 8F, 4F, 8F)
                .texOffs(0, 86).addBox(-7F, 2F, -13F, 14F, 8F, 20F)
                .texOffs(0, 182).addBox(-6F, 1F, -12F, 12F, 3F, 10F)
                .texOffs(100, 195).addBox(-7F, 5F, -14F, 14F, 2F, 2F),
                PartPose.offsetAndRotation(0F, 26F, 0F, 0F, 0F, 0F));
        PartDefinition p_thigh_l = root.addOrReplaceChild("thigh_l", CubeListBuilder.create()
                .texOffs(196, 54).mirror().addBox(-5F, 0F, -5F, 10F, 20F, 10F).mirror(false)
                .texOffs(0, 164).mirror().addBox(-6F, 1F, -7F, 12F, 14F, 4F).mirror(false)
                .texOffs(128, 140).mirror().addBox(4F, 2F, -4F, 2F, 14F, 8F).mirror(false),
                PartPose.offsetAndRotation(8F, -32F, 0F, 0F, 0F, 0F));
        PartDefinition p_shin_l = p_thigh_l.addOrReplaceChild("shin_l", CubeListBuilder.create()
                .texOffs(200, 0).mirror().addBox(-6F, 0F, -6F, 12F, 24F, 12F).mirror(false)
                .texOffs(76, 182).mirror().addBox(-5F, -4F, -8F, 10F, 8F, 4F).mirror(false)
                .texOffs(136, 164).mirror().addBox(-2F, 6F, -7F, 4F, 14F, 2F).mirror(false)
                .texOffs(112, 164).mirror().addBox(-4F, 8F, 5F, 8F, 12F, 4F).mirror(false),
                PartPose.offsetAndRotation(0F, 20F, 0F, 0F, 0F, 0F));
        PartDefinition p_foot_l = p_shin_l.addOrReplaceChild("foot_l", CubeListBuilder.create()
                .texOffs(44, 182).mirror().addBox(-4F, -2F, -4F, 8F, 4F, 8F).mirror(false)
                .texOffs(0, 86).mirror().addBox(-7F, 2F, -13F, 14F, 8F, 20F).mirror(false)
                .texOffs(0, 182).mirror().addBox(-6F, 1F, -12F, 12F, 3F, 10F).mirror(false)
                .texOffs(100, 195).mirror().addBox(-7F, 5F, -14F, 14F, 2F, 2F).mirror(false),
                PartPose.offsetAndRotation(0F, 26F, 0F, 0F, 0F, 0F));
        return LayerDefinition.create(mesh, 256, 256);
    }

    private static float ph3(float rest, float wind, float strike, float w, float k, float r) {
        return Mth.lerp(r, Mth.lerp(k, Mth.lerp(w, rest, wind), strike), rest);
    }

    @Override
    public void setupAnim(VanguardEntity e, float limbSwing, float limbSwingAmount, float age, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        float pt = age - e.tickCount;
        float t = age;
        float a = Math.min(1.0F, limbSwingAmount * 1.3F);

        // ---- idle
        float br = Mth.sin(t * 0.09F);
        this.chest.xRot += br * 0.025F;
        this.chest.y += -br * 0.25F * S;
        this.head.yRot += Mth.sin(t * 0.035F) * 0.3F * (1.0F - a);
        this.shoulder_r.zRot += -0.06F - br * 0.02F;
        this.shoulder_l.zRot += 0.06F + br * 0.02F;
        this.forearm_r.xRot += -0.25F;
        this.forearm_l.xRot += -0.35F;
        this.wing_r.zRot += Mth.sin(t * 0.07F) * 0.05F;
        this.wing_l.zRot -= Mth.sin(t * 0.07F) * 0.05F;
        float flame = 0.55F + 0.12F * Mth.sin(t * 1.9F) + 0.08F * Mth.sin(t * 3.3F);

        // ---- walk
        if (a > 0.01F) {
            float ph = limbSwing * 0.33F;
            float th = 0.55F * a * Mth.cos(ph);
            float drop = 23.0F * (1.0F - Mth.cos(Math.abs(th)));
            this.thigh_r.xRot += th; this.thigh_r.y += drop * S;
            this.thigh_l.xRot -= th; this.thigh_l.y += drop * S;
            float kr = (Math.max(0.0F, Mth.sin(ph)) * 1.1F + 0.05F) * a;
            float kl = (Math.max(0.0F, -Mth.sin(ph)) * 1.1F + 0.05F) * a;
            this.shin_r.xRot += kr; this.shin_l.xRot += kl;
            this.foot_r.xRot += -(th + kr) * 0.9F; this.foot_l.xRot += -(-th + kl) * 0.9F;
            this.waist.y += drop * S;
            this.waist.xRot += 0.06F * a; this.waist.yRot += Mth.cos(ph) * 0.12F * a; this.waist.zRot += Mth.sin(ph) * 0.035F * a;
            this.chest.xRot += 0.04F * a; this.chest.yRot += -Mth.cos(ph) * 0.22F * a; this.chest.zRot += -Mth.sin(ph) * 0.02F * a;
            this.head.yRot += Mth.cos(ph) * 0.1F * a;
            this.shoulder_r.xRot += -Mth.cos(ph) * 0.25F * a;
            this.shoulder_l.xRot += Mth.cos(ph) * 0.5F * a;
            this.forearm_l.xRot += -0.2F * a * Math.max(0.0F, Mth.cos(ph));
            flame += 0.25F * a;
        }

        // ---- energy sword slash
        float st = e.primaryTime(pt);
        if (st >= 0.0F) {
            float s = Math.min(1.0F, st / 1.6F);
            float w = MechaAnim.easeOut(s / 0.35F), k = MechaAnim.ease((s - 0.35F) / 0.13F), r = MechaAnim.ease((s - 0.68F) / 0.32F);
            this.shoulder_r.xRot += ph3(0, -2.3F, -0.9F, w, k, r);
            this.shoulder_r.yRot += ph3(0, 0.3F, -0.7F, w, k, r);
            this.shoulder_r.zRot += ph3(0, -1.1F, 1.0F, w, k, r);
            this.forearm_r.xRot += ph3(0, -0.5F, 0.05F, w, k, r);
            this.sword.xRot += ph3(0, 0.3F, 0.45F, w, k, r);
            this.sword.zRot += ph3(0, -0.3F, 0.5F, w, k, r);
            this.chest.xRot += ph3(0, -0.08F, 0.15F, w, k, r);
            this.chest.yRot += ph3(0, 0.35F, -0.5F, w, k, r);
            this.waist.yRot += ph3(0, 0.18F, -0.25F, w, k, r);
            this.head.xRot += ph3(0, 0.05F, 0.1F, w, k, r);
            this.head.yRot += ph3(0, -0.4F, 0.45F, w, k, r);
            this.shoulder_l.xRot += ph3(0, -0.6F, 0.5F, w, k, r);
            this.shoulder_l.zRot += ph3(0, 0.3F, 0.1F, w, k, r);
            if (a <= 0.01F) {
                float lt = ph3(0, -0.25F, -0.75F, w, k, r), rt = ph3(0, 0.2F, 0.45F, w, k, r);
                float lk = ph3(0, 0.35F, 1.05F, w, k, r), rk = ph3(0, 0.15F, 0.35F, w, k, r);
                this.thigh_l.xRot += lt; this.thigh_r.xRot += rt;
                this.shin_l.xRot += lk; this.shin_r.xRot += rk;
                this.foot_l.xRot += -(lt + lk); this.foot_r.xRot += -(rt + rk);
                float hl = 12 * Mth.cos(lt) + 13 * Mth.cos(lt + lk), hr = 12 * Mth.cos(rt) + 13 * Mth.cos(rt + rk);
                float drop = 25.0F - Math.max(hl, hr);
                float lunge = ph3(0, 1.0F, -2.0F, w, k, r);
                this.waist.y += drop * S; this.thigh_l.y += drop * S; this.thigh_r.y += drop * S;
                this.waist.z += lunge * S; this.thigh_l.z += lunge * S; this.thigh_r.z += lunge * S;
            }
            flame += ph3(0, 0.3F, 1.0F, w, k, r);
        }

        // ---- wrist blaster
        float bt = e.secondaryTime(pt);
        if (bt >= 0.0F) {
            float aim = Mth.sin(Math.min(1.0F, bt / 0.5F) * Mth.PI);
            this.shoulder_l.xRot += -1.4F * aim;
            this.forearm_l.xRot += 0.35F * aim;
        }

        // ---- thrusters (hold SPACE)
        float lift = e.getBoostAmount(pt);
        if (lift > 0.01F) {
            float th = -0.35F * lift, kn = 0.75F * lift, ft = -(th + kn) + 0.35F * lift;
            this.thigh_r.xRot += th; this.thigh_l.xRot += th * 0.8F;
            this.thigh_r.zRot += -0.08F * lift; this.thigh_l.zRot += 0.08F * lift;
            this.shin_r.xRot += kn; this.shin_l.xRot += kn * 1.2F;
            this.foot_r.xRot += ft; this.foot_l.xRot += ft;
            this.chest.xRot += -0.1F * lift; this.head.xRot += 0.1F * lift;
            this.shoulder_r.xRot += 0.5F * lift; this.shoulder_l.xRot += 0.5F * lift;
            this.shoulder_l.zRot += 0.5F * lift;
            this.wing_r.xRot += 0.25F * lift; this.wing_r.zRot += -0.5F * lift;
            this.wing_l.xRot += 0.25F * lift; this.wing_l.zRot += 0.5F * lift;
            this.wing2_r.xRot += 0.2F * lift; this.wing2_r.zRot += -0.35F * lift;
            this.wing2_l.xRot += 0.2F * lift; this.wing2_l.zRot += 0.35F * lift;
            flame += 2.4F * lift;
        }
        this.flame_r.yScale = Math.max(0.2F, flame);
        this.flame_l.yScale = Math.max(0.2F, flame);
    }

}
