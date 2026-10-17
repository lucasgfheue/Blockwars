package com.blockwars;

import com.blockwars.item.MechaCoreItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BlockWars.MODID);

    public static final DeferredItem<MechaCoreItem> MECHA_CORE = ITEMS.register("mecha_core",
            () -> new MechaCoreItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), ModEntities.MECHA));

    public static final DeferredItem<MechaCoreItem> GOLIATH_CORE = ITEMS.register("goliath_core",
            () -> new MechaCoreItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), ModEntities.GOLIATH));

    public static final DeferredItem<MechaCoreItem> VANGUARD_CORE = ITEMS.register("vanguard_core",
            () -> new MechaCoreItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), ModEntities.VANGUARD));

    public static final DeferredItem<MechaCoreItem> BASTION_CORE = ITEMS.register("bastion_core",
            () -> new MechaCoreItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), ModEntities.BASTION));
}
