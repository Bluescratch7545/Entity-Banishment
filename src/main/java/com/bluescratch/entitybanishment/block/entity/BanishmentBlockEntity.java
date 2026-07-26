package com.bluescratch.entitybanishment.block.entity;

import com.bluescratch.entitybanishment.menu.BanishmentMenu;
import com.bluescratch.entitybanishment.register.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.phys.AABB;

public class BanishmentBlockEntity extends BlockEntity implements MenuProvider {

    private String targetEntity = "";

    public BanishmentBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BANISHMENT_BLOCK_ENTITY.get(), pos, state);

    }

    public void tick() {
        if (level == null || level.isClientSide) return;
        if (targetEntity.isEmpty()) return;

        level.getEntitiesOfClass(
                Entity.class,
                new AABB(
                        -30000000, -2048, -30000000,
                        30000000,  2048,  30000000
                )
        ).forEach(entity -> {
            String id = entity.getType()
                    .builtInRegistryHolder()
                    .key()
                    .location()
                    .toString();

            if (id.equals(targetEntity)) {
                entity.discard();
            }
        });
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Banishment Block");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new BanishmentMenu(id, inventory, this);
    }

    public String getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(String targetEntity) {
        this.targetEntity = targetEntity;
        System.out.println("Target set: " + targetEntity);
        setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        tag.putString("TargetEntity", targetEntity);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        targetEntity = tag.getString("TargetEntity");
    }
}
