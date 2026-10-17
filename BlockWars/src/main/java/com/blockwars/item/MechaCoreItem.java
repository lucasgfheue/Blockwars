package com.blockwars.item;

import com.blockwars.entity.BaseMechaEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Supplier;

/** Right-click a block to deploy the mecha this core belongs to. */
public class MechaCoreItem extends Item {
    private final Supplier<? extends EntityType<? extends BaseMechaEntity>> type;

    public MechaCoreItem(Properties properties, Supplier<? extends EntityType<? extends BaseMechaEntity>> type) {
        super(properties);
        this.type = type;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        BaseMechaEntity mecha = this.type.get().create(level);
        if (mecha == null) {
            return InteractionResult.FAIL;
        }

        Player player = context.getPlayer();
        float yaw = player != null ? player.getYRot() : 0.0F;
        mecha.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, yaw, 0.0F);
        mecha.yBodyRot = yaw;
        mecha.yHeadRot = yaw;

        if (!level.noCollision(mecha)) {
            if (player != null) {
                player.displayClientMessage(Component.translatable("message.blockwars.no_space"), true);
            }
            return InteractionResult.FAIL;
        }

        level.addFreshEntity(mecha);
        level.playSound(null, pos, SoundEvents.IRON_GOLEM_REPAIR, SoundSource.NEUTRAL, 1.0F, 0.5F);
        level.playSound(null, pos, SoundEvents.PISTON_EXTEND, SoundSource.NEUTRAL, 1.0F, 0.6F);

        context.getItemInHand().consume(1, player);
        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltip").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.blockwars.core.deploy").withStyle(ChatFormatting.DARK_GRAY));
    }
}
