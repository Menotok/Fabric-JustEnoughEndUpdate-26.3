package net.menotok.justenoughendupdate.item;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {
    public static final Item BAKED_CHORUS_FRUIT = registerItem("baked_chorus_fruit", Item::new);


    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name)))));
    }

    public static void registerModItems() {
        JustEnoughEndUpdate.LOGGER.info("Registering Mod Items for" + JustEnoughEndUpdate.MOD_ID);
    }
}
