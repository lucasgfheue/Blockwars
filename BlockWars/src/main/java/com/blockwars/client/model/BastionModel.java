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
import com.blockwars.entity.BastionEntity;

/** Generated from the BlockWars design files. Geometry and UVs match the textures exactly. */
@SuppressWarnings("unused")
public class BastionModel extends HierarchicalModel<BastionEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(BlockWars.id("bastion"), "main");
    private static final float S = 2F;
    private final ModelPart root;
    public final ModelPart hips;
    public final ModelPart skirt_f;
    public final ModelPart thigh_r;
    public final ModelPart shin_r;
    public final ModelPart foot_r;
    public final ModelPart thigh_l;
    public final ModelPart shin_l;
    public final ModelPart foot_l;
    public final ModelPart hull;
    public final ModelPart sensor;
    public final ModelPart antenna;
    public final ModelPart stack_r;
    public final ModelPart sideplate_r;
    public final ModelPart stack_l;
    public final ModelPart sideplate_l;
    public final ModelPart shoulder_r;
    public final ModelPart gun;
    public final ModelPart barrel;
    public final ModelPart flash;
    public final ModelPart shoulder_l;
    public final ModelPart pod;
    public final ModelPart forearm_l;
    public final ModelPart claw_a;
    public final ModelPart claw_b;
    public final ModelPart claw_c;

    public BastionModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.root = root;
        this.hips = root.getChild("hips");
        this.skirt_f = this.hips.getChild("skirt_f");
        this.thigh_r = root.getChild("thigh_r");
        this.shin_r = this.thigh_r.getChild("shin_r");
        this.foot_r = this.shin_r.getChild("foot_r");
        this.thigh_l = root.getChild("thigh_l");
        this.shin_l = this.thigh_l.getChild("shin_l");
        this.foot_l = this.shin_l.getChild("foot_l");
        this.hull = this.hips.getChild("hull");
        this.sensor = this.hull.getChild("sensor");
        this.antenna = this.hull.getChild("antenna");
        this.stack_r = this.hull.getChild("stack_r");
        this.sideplate_r = this.hull.getChild("sideplate_r");
        this.stack_l = this.hull.getChild("stack_l");
        this.sideplate_l = this.hull.getChild("sideplate_l");
        this.shoulder_r = this.hull.getChild("shoulder_r");
        this.gun = this.shoulder_r.getChild("gun");
        this.barrel = this.gun.getChild("barrel");
        this.flash = this.barrel.getChild("flash");
        this.shoulder_l = this.hull.getChild("shoulder_l");
        this.pod = this.shoulder_l.getChild("pod");
        this.forearm_l = this.shoulder_l.getChild("forearm_l");
        this.claw_a = this.forearm_l.getChild("claw_a");
        this.claw_b = this.forearm_l.getChild("claw_b");
        this.claw_c = this.forearm_l.getChild("claw_c");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition p_hips = root.addOrReplaceChild("hips", CubeListBuilder.create()
                .texOffs(184, 100).addBox(-12F, -6F, -9F, 24F, 12F, 18F)
                .texOffs(316, 100).addBox(-14F, -4F, -11F, 28F, 6F, 22F),
                PartPose.offsetAndRotation(0F, -34F, 0F, 0F, 0F, 0F));
        PartDefinition p_skirt_f = p_hips.addOrReplaceChild("skirt_f", CubeListBuilder.create()
                .texOffs(0, 182).addBox(-10F, 0F, -2F, 20F, 10F, 2F),
                PartPose.offsetAndRotation(0F, 2F, -11F, -0.1F, 0F, 0F));
        PartDefinition p_thigh_r = root.addOrReplaceChild("thigh_r", CubeListBuilder.create()
                .texOffs(136, 100).addBox(-6F, 0F, -6F, 12F, 20F, 12F)
                .texOffs(152, 134).addBox(-8F, -2F, -9F, 16F, 16F, 8F)
                .texOffs(52, 134).addBox(-9F, 0F, -6F, 2F, 14F, 12F),
                PartPose.offsetAndRotation(-15F, -34F, 0F, 0F, 0F, 0F));
        PartDefinition p_shin_r = p_thigh_r.addOrReplaceChild("shin_r", CubeListBuilder.create()
                .texOffs(412, 62).addBox(-8F, 0F, -9F, 16F, 22F, 14F)
                .texOffs(260, 162).addBox(-7F, -5F, -11F, 14F, 10F, 6F)
                .texOffs(448, 134).addBox(-6F, 2F, 5F, 12F, 18F, 4F)
                .texOffs(300, 134).addBox(-2F, -2F, 7F, 4F, 20F, 4F),
                PartPose.offsetAndRotation(0F, 20F, 0F, 0F, 0F, 0F));
        PartDefinition p_foot_r = p_shin_r.addOrReplaceChild("foot_r", CubeListBuilder.create()
                .texOffs(300, 162).addBox(-5F, -2F, -5F, 10F, 6F, 10F)
                .texOffs(256, 62).addBox(-10F, 4F, -16F, 20F, 10F, 26F)
                .texOffs(76, 182).addBox(-9F, 2F, -18F, 18F, 6F, 4F)
                .texOffs(372, 162).addBox(-4F, 4F, 10F, 8F, 8F, 6F),
                PartPose.offsetAndRotation(0F, 24F, 0F, 0F, 0F, 0F));
        PartDefinition p_thigh_l = root.addOrReplaceChild("thigh_l", CubeListBuilder.create()
                .texOffs(136, 100).mirror().addBox(-6F, 0F, -6F, 12F, 20F, 12F).mirror(false)
                .texOffs(152, 134).mirror().addBox(-8F, -2F, -9F, 16F, 16F, 8F).mirror(false)
                .texOffs(52, 134).mirror().addBox(7F, 0F, -6F, 2F, 14F, 12F).mirror(false),
                PartPose.offsetAndRotation(15F, -34F, 0F, 0F, 0F, 0F));
        PartDefinition p_shin_l = p_thigh_l.addOrReplaceChild("shin_l", CubeListBuilder.create()
                .texOffs(412, 62).mirror().addBox(-8F, 0F, -9F, 16F, 22F, 14F).mirror(false)
                .texOffs(260, 162).mirror().addBox(-7F, -5F, -11F, 14F, 10F, 6F).mirror(false)
                .texOffs(448, 134).mirror().addBox(-6F, 2F, 5F, 12F, 18F, 4F).mirror(false)
                .texOffs(300, 134).mirror().addBox(-2F, -2F, 7F, 4F, 20F, 4F).mirror(false),
                PartPose.offsetAndRotation(0F, 20F, 0F, 0F, 0F, 0F));
        PartDefinition p_foot_l = p_shin_l.addOrReplaceChild("foot_l", CubeListBuilder.create()
                .texOffs(300, 162).mirror().addBox(-5F, -2F, -5F, 10F, 6F, 10F).mirror(false)
                .texOffs(256, 62).mirror().addBox(-10F, 4F, -16F, 20F, 10F, 26F).mirror(false)
                .texOffs(76, 182).mirror().addBox(-9F, 2F, -18F, 18F, 6F, 4F).mirror(false)
                .texOffs(372, 162).mirror().addBox(-4F, 4F, 10F, 8F, 8F, 6F).mirror(false),
                PartPose.offsetAndRotation(0F, 24F, 0F, 0F, 0F, 0F));
        PartDefinition p_hull = p_hips.addOrReplaceChild("hull", CubeListBuilder.create()
                .texOffs(80, 134).addBox(-10F, -8F, -8F, 20F, 8F, 16F)
                .texOffs(0, 0).addBox(-22F, -34F, -18F, 44F, 26F, 36F)
                .texOffs(0, 62).addBox(-24F, -36F, -16F, 48F, 6F, 32F)
                .texOffs(380, 134).addBox(-14F, -30F, -24F, 28F, 16F, 6F)
                .texOffs(152, 182).addBox(-16F, -32F, -22F, 32F, 4F, 4F)
                .texOffs(428, 162).addBox(-12F, -14F, -22F, 24F, 6F, 6F)
                .texOffs(160, 62).addBox(-18F, -32F, 18F, 36F, 24F, 12F)
                .texOffs(52, 162).addBox(-22F, -18F, 18F, 12F, 10F, 10F),
                PartPose.offsetAndRotation(0F, -6F, 0F, 0.12F, 0F, 0F));
        PartDefinition p_sensor = p_hull.addOrReplaceChild("sensor", CubeListBuilder.create()
                .texOffs(120, 182).addBox(-4F, -2F, -4F, 8F, 2F, 8F)
                .texOffs(0, 162).addBox(-7F, -10F, -7F, 14F, 8F, 12F)
                .texOffs(280, 182).addBox(-5F, -9F, -8F, 4F, 4F, 2F)
                .texOffs(280, 182).addBox(1F, -9F, -8F, 4F, 4F, 2F)
                .texOffs(292, 182).addBox(-1F, -4.4F, -8F, 2F, 2F, 2F),
                PartPose.offsetAndRotation(0F, -36F, -10F, 0F, 0F, 0F));
        PartDefinition p_antenna = p_hull.addOrReplaceChild("antenna", CubeListBuilder.create()
                .texOffs(320, 0).addBox(-1F, -44F, -1F, 2F, 44F, 2F),
                PartPose.offsetAndRotation(18F, -36F, 12F, -0.25F, 0F, 0.1F));
        PartDefinition p_stack_r = p_hull.addOrReplaceChild("stack_r", CubeListBuilder.create()
                .texOffs(276, 134).addBox(-3F, -18F, -3F, 6F, 18F, 6F)
                .texOffs(44, 182).addBox(-4F, -20F, -4F, 8F, 3F, 8F),
                PartPose.offsetAndRotation(-10F, -32F, 24F, -0.15F, 0F, -0.12F));
        PartDefinition p_sideplate_r = p_hull.addOrReplaceChild("sideplate_r", CubeListBuilder.create()
                .texOffs(260, 0).addBox(-1F, 0F, -14F, 2F, 20F, 28F),
                PartPose.offsetAndRotation(-23F, -28F, 0F, 0F, 0F, -0.1F));
        PartDefinition p_stack_l = p_hull.addOrReplaceChild("stack_l", CubeListBuilder.create()
                .texOffs(276, 134).addBox(-3F, -18F, -3F, 6F, 18F, 6F)
                .texOffs(44, 182).addBox(-4F, -20F, -4F, 8F, 3F, 8F),
                PartPose.offsetAndRotation(10F, -32F, 24F, -0.15F, 0F, 0.12F));
        PartDefinition p_sideplate_l = p_hull.addOrReplaceChild("sideplate_l", CubeListBuilder.create()
                .texOffs(260, 0).addBox(-1F, 0F, -14F, 2F, 20F, 28F),
                PartPose.offsetAndRotation(23F, -28F, 0F, 0F, 0F, 0.1F));
        PartDefinition p_shoulder_r = p_hull.addOrReplaceChild("shoulder_r", CubeListBuilder.create()
                .texOffs(200, 134).addBox(-6F, -6F, -6F, 12F, 12F, 12F)
                .texOffs(68, 100).addBox(-10F, -12F, -10F, 14F, 12F, 20F)
                .texOffs(12, 134).addBox(-5F, 4F, -5F, 10F, 16F, 10F),
                PartPose.offsetAndRotation(-28F, -26F, -2F, 0F, 0F, 0F));
        PartDefinition p_gun = p_shoulder_r.addOrReplaceChild("gun", CubeListBuilder.create()
                .texOffs(160, 0).addBox(-8F, -4F, -24F, 16F, 16F, 34F)
                .texOffs(268, 100).addBox(-16F, -2F, -12F, 8F, 14F, 16F)
                .texOffs(168, 162).addBox(-6F, 12F, -8F, 12F, 4F, 12F)
                .texOffs(316, 134).addBox(-6F, -7F, -22F, 12F, 3F, 20F),
                PartPose.offsetAndRotation(0F, 20F, 0F, 0F, 0F, 0F));
        PartDefinition p_barrel = p_gun.addOrReplaceChild("barrel", CubeListBuilder.create()
                .texOffs(0, 100).addBox(-3F, -3F, -28F, 6F, 6F, 28F)
                .texOffs(120, 162).addBox(-5F, -5F, -8F, 10F, 10F, 8F)
                .texOffs(340, 162).addBox(-5F, -5F, -34F, 10F, 10F, 6F),
                PartPose.offsetAndRotation(0F, 4F, -24F, 0F, 0F, 0F));
        PartDefinition p_flash = p_barrel.addOrReplaceChild("flash", CubeListBuilder.create()
                .texOffs(416, 100).addBox(-4F, -4F, -20F, 8F, 8F, 20F)
                .texOffs(224, 182).addBox(-12F, -1F, -6F, 24F, 2F, 4F)
                .texOffs(0, 134).addBox(-1F, -12F, -6F, 2F, 24F, 4F),
                PartPose.offsetAndRotation(0F, 0F, -34F, 0F, 0F, 0F));
        PartDefinition p_shoulder_l = p_hull.addOrReplaceChild("shoulder_l", CubeListBuilder.create()
                .texOffs(200, 134).mirror().addBox(-6F, -6F, -6F, 12F, 12F, 12F).mirror(false)
                .texOffs(472, 100).mirror().addBox(-5F, 4F, -5F, 10F, 18F, 10F).mirror(false),
                PartPose.offsetAndRotation(28F, -26F, -2F, 0F, 0F, 0F));
        PartDefinition p_pod = p_shoulder_l.addOrReplaceChild("pod", CubeListBuilder.create()
                .texOffs(328, 0).addBox(-10F, -16F, -14F, 22F, 16F, 26F)
                .texOffs(216, 162).addBox(-9F, -15F, -15F, 20F, 14F, 2F)
                .texOffs(120, 182).addBox(-4F, 0F, -4F, 8F, 2F, 8F),
                PartPose.offsetAndRotation(2F, -8F, 0F, 0F, 0F, 0F));
        PartDefinition p_forearm_l = p_shoulder_l.addOrReplaceChild("forearm_l", CubeListBuilder.create()
                .texOffs(348, 62).mirror().addBox(-8F, 0F, -8F, 16F, 20F, 16F).mirror(false)
                .texOffs(156, 162).mirror().addBox(-2F, 2F, -10F, 4F, 16F, 2F).mirror(false)
                .texOffs(168, 162).mirror().addBox(-6F, 20F, -6F, 12F, 4F, 12F).mirror(false),
                PartPose.offsetAndRotation(0F, 20F, 0F, 0F, 0F, 0F));
        PartDefinition p_claw_a = p_forearm_l.addOrReplaceChild("claw_a", CubeListBuilder.create()
                .texOffs(248, 134).addBox(-3F, 0F, -4F, 6F, 16F, 8F)
                .texOffs(400, 162).addBox(-3F, 14F, -4F, 6F, 6F, 8F),
                PartPose.offsetAndRotation(-4F, 24F, 0F, 0F, 0F, 0.2F));
        PartDefinition p_claw_b = p_forearm_l.addOrReplaceChild("claw_b", CubeListBuilder.create()
                .texOffs(248, 134).mirror().addBox(-3F, 0F, -4F, 6F, 16F, 8F).mirror(false)
                .texOffs(400, 162).mirror().addBox(-3F, 14F, -4F, 6F, 6F, 8F).mirror(false),
                PartPose.offsetAndRotation(4F, 24F, 0F, 0F, 0F, -0.2F));
        PartDefinition p_claw_c = p_forearm_l.addOrReplaceChild("claw_c", CubeListBuilder.create()
                .texOffs(96, 162).addBox(-3F, 0F, -3F, 6F, 14F, 6F),
                PartPose.offsetAndRotation(0F, 24F, 4F, 0.25F, 0F, 0F));
        return LayerDefinition.create(mesh, 512, 256);
    }

    /** Shot / launch times in seconds — shared with BastionEntity so effects line up with the animation. */
    public static final float[] SHOTS = BastionEntity.SHOT_TIMES;
    public static final float[] ROCKETS = BastionEntity.ROCKET_TIMES;

    private void legs(float thr, float knr, float thl, float knl) {
        float hr = 10 * Mth.cos(thr) + 12 * Mth.cos(thr + knr);
        float hl = 10 * Mth.cos(thl) + 12 * Mth.cos(thl + knl);
        float drop = 22.0F - Math.max(hr, hl);
        this.thigh_r.xRot += thr; this.thigh_l.xRot += thl;
        this.thigh_r.y += drop * S; this.thigh_l.y += drop * S;
        this.shin_r.xRot += knr; this.shin_l.xRot += knl;
        this.foot_r.xRot += -(thr + knr); this.foot_l.xRot += -(thl + knl);
        this.hips.y += drop * S;
    }

    @Override
    public void setupAnim(BastionEntity e, float limbSwing, float limbSwingAmount, float age, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        float pt = age - e.tickCount;
        float T = age / 20.0F;
        float a = Math.min(1.0F, limbSwingAmount * 1.3F);

        // ---- idle hydraulics
        this.hull.xRot += Mth.sin(T * 1.3F) * 0.012F;
        this.hull.y += Mth.sin(T * 1.3F) * 0.15F * S;
        float scan = Mth.sin(T * 0.9F) * 0.55F;
        scan = Math.copySign((float) Math.pow(Math.abs(scan), 0.6), scan) * 0.7F;
        this.sensor.yRot += scan;
        this.antenna.xRot += Mth.sin(T * 2.1F) * 0.04F;
        this.antenna.zRot += Mth.sin(T * 1.7F) * 0.06F;
        float cl = 0.08F * Mth.sin(T * 1.1F);
        this.claw_a.zRot += cl; this.claw_b.zRot -= cl;
        this.forearm_l.xRot += -0.25F; this.shoulder_l.xRot += 0.05F;
        this.flash.visible = false;

        float burst = e.primaryTime(pt);
        float salvo = e.secondaryTime(pt);
        boolean legsSet = false;

        // ---- heavy walk
        if (a > 0.01F) {
            float ph = limbSwing * 0.25F;
            float th = 0.42F * a * Mth.cos(ph);
            float kr = (Math.max(0.0F, Mth.sin(ph)) * 0.95F + 0.08F) * a;
            float kl = (Math.max(0.0F, -Mth.sin(ph)) * 0.95F + 0.08F) * a;
            legs(th, kr, -th, kl);
            legsSet = true;
            this.hull.xRot += 0.05F * a; this.hull.yRot += Mth.cos(ph) * 0.07F * a; this.hull.zRot += -Mth.sin(ph) * 0.06F * a;
            this.hips.yRot += -Mth.cos(ph) * 0.06F * a; this.hips.zRot += Mth.sin(ph) * 0.04F * a;
            this.sensor.xRot += 0.05F * a;
            this.shoulder_r.xRot += Mth.cos(ph) * 0.08F * a;
            this.shoulder_l.xRot += -Mth.cos(ph) * 0.28F * a;
            this.antenna.xRot += -Mth.cos(2 * ph) * 0.12F * a;
        }

        // ---- autocannon burst
        if (burst >= 0.0F) {
            float t = burst;
            float aim = MechaAnim.ease(t / 0.35F) * (1.0F - MechaAnim.ease((t - 1.55F) / 0.4F));
            if (!legsSet) { legs(-0.25F * aim, Mth.lerp(aim, 0.08F, 0.35F), 0.3F * aim, Mth.lerp(aim, 0.08F, 0.2F)); legsSet = true; }
            this.hull.xRot += 0.08F * aim; this.hull.yRot += 0.12F * aim;
            this.shoulder_r.xRot += -0.22F * aim; this.shoulder_r.yRot += 0.12F * aim;
            this.sensor.yRot += -scan * aim - 0.15F * aim;
            float rec = 0.0F;
            for (float ts : SHOTS) {
                if (t >= ts) rec = Math.max(rec, (float) Math.exp(-(t - ts) * 14.0F));
                if (t - ts >= 0.0F && t - ts < 0.06F) {
                    this.flash.visible = true;
                    this.flash.zRot += (ts * 37.0F) % 1.6F;
                }
            }
            this.barrel.z += 3.5F * rec * S;
            this.gun.xRot += -0.07F * rec;
            this.hull.xRot += -0.02F * rec;
        }

        // ---- rocket salvo
        if (salvo >= 0.0F) {
            float t = salvo;
            float up = MechaAnim.ease(t / 0.5F) * (1.0F - MechaAnim.ease((t - 1.9F) / 0.45F));
            this.pod.xRot += -0.65F * up;
            this.shoulder_l.zRot += 0.1F * up;
            if (!legsSet) { legs(0.2F * up, Mth.lerp(up, 0.08F, 0.3F), -0.15F * up, Mth.lerp(up, 0.08F, 0.2F)); legsSet = true; }
            float kick = 0.0F;
            for (float tr : ROCKETS) {
                if (t >= tr) kick = Math.max(kick, (float) Math.exp(-(t - tr) * 10.0F));
            }
            this.hull.xRot += -0.05F * up - 0.03F * kick;
            this.pod.xRot += 0.06F * kick;
            this.sensor.xRot += -0.25F * up;
            this.sensor.yRot += -scan * up;
        }

        if (!legsSet) legs(-0.05F, 0.08F, 0.05F, 0.08F);
    }

}
