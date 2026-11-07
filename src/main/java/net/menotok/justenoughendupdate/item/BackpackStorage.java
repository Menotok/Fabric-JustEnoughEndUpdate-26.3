package net.menotok.justenoughendupdate.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class BackpackStorage {
    public static boolean insert(Player player, ItemStack incoming, boolean onlyMatchingStacks) {
        if (incoming.isEmpty() || incoming.is(ModItems.SHULKERBACKPACK)) {
            return false;
        }
        if (!(player.inventoryMenu.getSlot(46).container instanceof WornBackpackContainer backpack) || !backpack.isWorn()) {
            return false;
        }

        boolean moved = false;

        for (int i = 0; i < 27 && !incoming.isEmpty(); i++) {
            ItemStack existing = backpack.getItem(i);
            if (!existing.isEmpty() && ItemStack.isSameItemSameComponents(existing, incoming)
                    && existing.getCount() < existing.getMaxStackSize()) {
                int amount = Math.min(incoming.getCount(), existing.getMaxStackSize() - existing.getCount());
                existing.grow(amount);
                incoming.shrink(amount);
                moved = true;
            }
        }

        if (!onlyMatchingStacks) {
            for (int i = 0; i < 27 && !incoming.isEmpty(); i++) {
                if (backpack.getItem(i).isEmpty()) {
                    backpack.setItem(i, incoming.split(Math.min(incoming.getCount(), incoming.getMaxStackSize())));
                    moved = true;
                }
            }
        }

        if (moved) {
            backpack.setChanged();
        }
        return moved;
    }
}