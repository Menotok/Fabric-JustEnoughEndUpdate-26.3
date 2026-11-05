package net.menotok.justenoughendupdate.item;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

import java.util.ArrayList;
import java.util.List;

public class ShulkerBackpack extends SimpleContainer {
    private final ItemStack backpack;

    public ShulkerBackpack(ItemStack backpack) {
        super(27);
        this.backpack = backpack;

        NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
        backpack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(items);
        for (int i = 0; i < 27; i++) {
            this.setItem(i, items.get(i));
        }
    }

    @Override
    public void setChanged() {
        super.setChanged();
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < this.getContainerSize(); i++) {
            items.add(this.getItem(i));
        }
        backpack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(items));
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getItemBySlot(EquipmentSlot.CHEST) == backpack;
    }
}
