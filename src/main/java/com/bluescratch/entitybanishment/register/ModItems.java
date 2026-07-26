package com.bluescratch.entitybanishment.register;

import com.bluescratch.entitybanishment.EntityBanishment;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.Items.createItems(EntityBanishment.MOD_ID);

    public static final DeferredItem<BlockItem> BANISHMENT_BLOCK_ITEM =
            ITEMS.register("banishment_block", () -> new BlockItem(ModBlocks.BANISHMENT_BLOCK.get(), new Item.Properties()));
}
