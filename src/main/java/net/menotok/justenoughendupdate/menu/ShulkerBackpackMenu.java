package net.menotok.justenoughendupdate.menu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.Slot;

public class ShulkerBackpackMenu extends ChestMenu {
    public ShulkerBackpackMenu(int containerId, Inventory playerInventory, Container container) {
        super(ModMenuTypes.SHULKER_BACKPACK, containerId, playerInventory, container, 3);
        for (int i = 0; i < 27; i++) {
            Slot old = this.slots.get(i);
            Slot replacement = new ShulkerBackpackSlot(container, i, old.x, old.y);
            replacement.index = i;
            this.slots.set(i, replacement);
        }
    }
}