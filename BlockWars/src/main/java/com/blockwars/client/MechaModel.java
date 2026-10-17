package com.blockwars.client;

import com.blockwars.BlockWars;
import com.blockwars.entity.MechaEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * Blocky bipedal mecha: two heavy legs, a torso with an open cockpit on top,
 * a cannon on the right arm and a crusher fist on the left.
 * Model units are pixels (16 = one block); y = 24 is the ground.
 */
public class MechaModel extends HierarchicalModel<MechaEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(BlockWars.id("mecha"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public MechaModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.rightArm = this.body.getChild("right_arm");
        this.leftArm = this.body.getChild("left_arm");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(48, 32).addBox(-8.0F, -4.0F, -5.0F, 16, 4, 10)    // pelvis
                        .texOffs(0, 64).addBox(-12.0F, -20.0F, -8.0F, 24, 16, 16)  // torso
                        .texOffs(0, 96).addBox(-12.0F, -30.0F, 4.0F, 24, 10, 4)    // cockpit backrest
                        .texOffs(0, 112).addBox(-12.0F, -24.0F, -8.0F, 24, 4, 2)   // cockpit dashboard
                        .texOffs(80, 64).addBox(-7.0F, -18.0F, 8.0F, 14, 12, 6)    // reactor backpack
                        .texOffs(80, 84).addBox(-6.0F, -22.0F, 10.0F, 4, 4, 4)     // exhaust L
                        .texOffs(80, 84).addBox(2.0F, -22.0F, 10.0F, 4, 4, 4)      // exhaust R
                        .texOffs(100, 84).addBox(9.0F, -38.0F, 5.0F, 1, 8, 1),     // antenna
                PartPose.offset(0.0F, 0.0F, 0.0F));

        body.addOrReplaceChild("right_arm", CubeListBuilder.create()
                        .texOffs(32, 0).addBox(-3.0F, -2.0F, -3.0F, 6, 18, 6)      // arm
                        .texOffs(56, 0).addBox(-4.0F, -5.0F, -4.0F, 8, 4, 8)       // shoulder pad
                        .texOffs(56, 12).addBox(-2.0F, 16.0F, -2.0F, 4, 10, 4),    // cannon barrel
                PartPose.offset(-15.0F, -16.0F, 0.0F));

        body.addOrReplaceChild("left_arm", CubeListBuilder.create()
                        .texOffs(32, 0).mirror().addBox(-3.0F, -2.0F, -3.0F, 6, 18, 6).mirror(false)
                        .texOffs(56, 0).mirror().addBox(-4.0F, -5.0F, -4.0F, 8, 4, 8).mirror(false)
                        .texOffs(88, 0).addBox(-4.0F, 16.0F, -4.0F, 8, 6, 8),      // fist
                PartPose.offset(15.0F, -16.0F, 0.0F));

        root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8, 21, 8)
                        .texOffs(0, 32).addBox(-5.0F, 21.0F, -7.0F, 10, 3, 12),   // foot
                PartPose.offset(-6.0F, 0.0F, 0.0F));

        root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                        .texOffs(0, 0).mirror().addBox(-4.0F, 0.0F, -4.0F, 8, 21, 8)
                        .texOffs(0, 32).addBox(-5.0F, 21.0F, -7.0F, 10, 3, 12).mirror(false),
                PartPose.offset(6.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(MechaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                          float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        float walk = limbSwing * 0.35F;
        float amount = Math.min(limbSwingAmount, 1.0F);

        // Heavy, slow steps
        this.rightLeg.xRot = Mth.cos(walk) * 0.8F * amount;
        this.leftLeg.xRot = Mth.cos(walk + Mth.PI) * 0.8F * amount;
        this.leftArm.xRot = Mth.cos(walk) * 0.5F * amount;
        this.rightArm.xRot = Mth.cos(walk + Mth.PI) * 0.5F * amount;

        // Torso bobs with each footstep and idles with a slight "breathing" motion
        this.body.y += Math.abs(Mth.cos(walk)) * 1.5F * amount + Mth.sin(ageInTicks * 0.08F) * 0.3F;
        this.body.yRot = Mth.cos(walk) * 0.06F * amount;

        // When piloted, the cannon arm aims where the pilot looks
        if (entity.isVehicle()) {
            this.rightArm.xRot = -Mth.HALF_PI + headPitch * Mth.DEG_TO_RAD;
        }

        // Recoil when firing
        int fire = entity.getFireAnim();
        if (fire > 0) {
            this.rightArm.xRot += (fire / 8.0F) * 0.5F;
            this.rightArm.z += (fire / 8.0F) * 2.0F;
        }
    }
}
