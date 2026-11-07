package net.menotok.justenoughendupdate.item;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

public class WornBackpackContainer implements Container {
    private final Player player;
    private final NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
    private ItemStack loadedFrom = ItemStack.EMPTY;

    public WornBackpackContainer(Player player) {
        this.player = player;
    }

    public boolean isWorn() {
        return player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.SHULKERBACKPACK);
    }

    private void sync() {
        ItemStack worn = player.getItemBySlot(EquipmentSlot.CHEST);
        if (worn != loadedFrom) {
            loadedFrom = worn;
            for (int i = 0; i < 27; i++) {
                items.set(i, ItemStack.EMPTY);
            }
            if (isWorn()) {
                worn.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(items);
            }
        }
    }

    @Override
    public void setChanged() {
        if (isWorn()) {
            player.getItemBySlot(EquipmentSlot.CHEST).set(DataComponents.CONTAINER, ItemContainerContents.fromItems(items));
        }
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        sync();
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        sync();
        return items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        sync();
        ItemStack result = ContainerHelper.removeItem(items, slot, amount);
        setChanged();
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        sync();
        ItemStack result = ContainerHelper.takeItem(items, slot);
        setChanged();
        return result;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        sync();
        items.set(slot, stack);
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        sync();
        for (int i = 0; i < 27; i++) {
            items.set(i, ItemStack.EMPTY);
        }
        setChanged();
    }
}