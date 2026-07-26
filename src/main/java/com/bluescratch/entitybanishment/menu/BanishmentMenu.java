package com.bluescratch.entitybanishment.menu;

import com.bluescratch.entitybanishment.block.entity.BanishmentBlockEntity;
import com.bluescratch.entitybanishment.register.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class BanishmentMenu extends AbstractContainerMenu {
    private final BanishmentBlockEntity blockEntity;
    private final BlockPos blockPos;

    public BanishmentMenu(int id, Inventory inventory, BanishmentBlockEntity blockEntity) {
        super(ModMenus.BANISHMENT_MENU.get(), id);

        this.blockEntity = blockEntity;
        this.blockPos = blockEntity.getBlockPos();
    }

    public BanishmentMenu(int id, Inventory inventory) {
        super(ModMenus.BANISHMENT_MENU.get(), id);

        this.blockEntity = null;
        this.blockPos = BlockPos.ZERO;
    }

    public BanishmentBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}