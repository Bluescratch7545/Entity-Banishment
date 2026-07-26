package com.bluescratch.entitybanishment.client;

import com.bluescratch.entitybanishment.EntityBanishment;
import com.bluescratch.entitybanishment.menu.BanishmentMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.components.Button;
import com.bluescratch.entitybanishment.network.SetTargetEntityPacket;
import net.neoforged.neoforge.network.PacketDistributor;


public class BanishmentScreen extends AbstractContainerScreen<BanishmentMenu> {

    private EditBox entityBox;
    private final List<String> bannedEntities = new ArrayList<>();


    public BanishmentScreen(BanishmentMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        this.imageHeight = 176;
        this.imageWidth = 166;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = leftPos;
        int y = topPos;

        guiGraphics.blit(
                TEXTURE,
                leftPos,
                topPos,
                0,
                0,
                imageWidth,
                imageHeight,
                imageWidth,
                imageHeight
        );
    }


    @Override
    protected void init() {
        super.init();

        entityBox = new EditBox(
                font,
                leftPos + 10,
                topPos + 30,
                146,
                20,
                Component.literal("Entity ID")
        );

        addRenderableWidget(entityBox);

        addRenderableWidget(
                Button.builder(
                                Component.literal("Banish"),
                                button -> {
                                    System.out.println("BUTTON CLICKED: " + entityBox.getValue());
                                    PacketDistributor.sendToServer(
                                            new SetTargetEntityPacket(
                                                    menu.getBlockPos(),
                                                    entityBox.getValue()
                                            )
                                    );
                                }
                        )
                        .bounds(
                                leftPos + 10,
                                topPos + 60,
                                146,
                                20
                        )
                        .build()
        );
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        return entityBox.charTyped(codePoint, modifiers)
                || super.charTyped(codePoint, modifiers);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (entityBox.keyPressed(keyCode, scanCode, modifiers)
                || entityBox.canConsumeInput()) {
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(
                font,
                "Entity ID",
                10,
                18,
                0xFFFFFF
        );
    }

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    EntityBanishment.MOD_ID,
                    "textures/gui/banishment.png"
            );
}
