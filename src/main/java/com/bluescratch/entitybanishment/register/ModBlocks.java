package com.bluescratch.entitybanishment.register;

import com.bluescratch.entitybanishment.EntityBanishment;

import com.bluescratch.entitybanishment.block.BanishmentBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EntityBanishment.MOD_ID);

    public static final DeferredBlock<Block> BANISHMENT_BLOCK =
            BLOCKS.register(
                    "banishment_block",
                    () -> new BanishmentBlock(
                        BlockBehaviour.Properties.of()
                                .lightLevel(state -> 8)
                    )
            );
}
