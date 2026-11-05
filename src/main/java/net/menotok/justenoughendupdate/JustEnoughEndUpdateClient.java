package net.menotok.justenoughendupdate;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.menotok.justenoughendupdate.item.ModItems;
import net.menotok.justenoughendupdate.menu.ModMenuTypes;
import net.menotok.justenoughendupdate.network.OpenShulkerBackpackPayload;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket;
import net.minecraft.world.entity.EquipmentSlot;

public class JustEnoughEndUpdateClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.SHULKER_BACKPACK, ContainerScreen::new);
        ScreenEvents.AFTER_INIT.register((client, screen, width, height) -> {
            if (client.player == null || !client.player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.SHULKERBACKPACK)) {
                return;
            }
            int x = width / 2 + 88 - 22;
            int y = height / 2 - 83 - 22;

            if (screen instanceof InventoryScreen) {
                Screens.getWidgets(screen).add(Button.builder(Component.literal("B"),
                                button -> ClientPlayNetworking.send(new OpenShulkerBackpackPayload()))
                        .bounds(x, y, 22, 20).build());
            } else if (screen instanceof ContainerScreen
                    && screen.getTitle().getString().equals(Component.translatable("container.justenoughendupdate.backpack").getString())) {
                Screens.getWidgets(screen).add(Button.builder(Component.literal("I"),
                                button -> {
                                    var player = client.player;
                                    player.connection.send(new ServerboundContainerClosePacket(player.containerMenu.containerId));
                                    player.containerMenu = player.inventoryMenu;
                                    client.gui.setScreen(new InventoryScreen(player));
                                })
                        .bounds(x, y, 22, 20).build());
            }
        });
    }
}