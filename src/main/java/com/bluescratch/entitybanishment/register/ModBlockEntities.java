package com.bluescratch.entitybanishment.register;

import com.bluescratch.entitybanishment.EntityBanishment;
import com.bluescratch.entitybanishment.block.BanishmentBlock;
import com.bluescratch.entitybanishment.block.entity.BanishmentBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(
                    Registries.BLOCK_ENTITY_TYPE,
                    EntityBanishment.MOD_ID
            );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BanishmentBlockEntity>> BANISHMENT_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("banishment_block", () -> BlockEntityType.Builder.of(BanishmentBlockEntity::new, ModBlocks.BANISHMENT_BLOCK.get()).build(null));
}
