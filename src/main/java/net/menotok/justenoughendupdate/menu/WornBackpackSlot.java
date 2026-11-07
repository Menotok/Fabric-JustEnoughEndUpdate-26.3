package net.menotok.justenoughendupdate.menu;

import net.menotok.justenoughendupdate.item.ModItems;
import net.menotok.justenoughendupdate.item.WornBackpackContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class WornBackpackSlot extends Slot {
    private final WornBackpackContainer backpack;

    public WornBackpackSlot(WornBackpackContainer container, int index, int x, int y) {
        super(container, index, x, y);
        this.backpack = container;
    }

    @Override
    public boolean isActive() {
        return backpack.isWorn();
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return backpack.isWorn() && !stack.is(ModItems.SHULKERBACKPACK);
    }

    @Override
    public boolean mayPickup(Player player) {
        return backpack.isWorn();
    }
}
