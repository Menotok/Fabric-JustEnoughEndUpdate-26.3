package net.menotok.justenoughendupdate.item;

import net.minecraft.world.item.Item;

public class ShulkerBackpackItem extends Item {
    public ShulkerBackpackItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }
}