package net.menotok.justenoughendupdate.menu;

import net.menotok.justenoughendupdate.item.ModItems;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ShulkerBackpackSlot extends Slot {
    public ShulkerBackpackSlot(Container container, int index, int x, int y) {
        super(container, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return !stack.is(ModItems.SHULKERBACKPACK);
    }
}