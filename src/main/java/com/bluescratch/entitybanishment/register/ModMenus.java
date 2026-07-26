package com.bluescratch.entitybanishment.register;

import com.bluescratch.entitybanishment.EntityBanishment;
import com.bluescratch.entitybanishment.menu.BanishmentMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, EntityBanishment.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<BanishmentMenu>> BANISHMENT_MENU =
            MENUS.register("banishment_menu", () -> new MenuType<>(BanishmentMenu::new,FeatureFlags.DEFAULT_FLAGS));
}