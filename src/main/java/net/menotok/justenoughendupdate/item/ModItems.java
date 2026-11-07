package net.menotok.justenoughendupdate.item;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.menotok.justenoughendupdate.food.ModFoods;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;

import java.util.function.Function;

public class ModItems {
    public static final Item BAKED_CHORUS_FRUIT = registerItem("baked_chorus_fruit", Item::new,
            new Item.Properties().food(ModFoods.BAKED_CHORUS_FRUIT));

    public static final ResourceKey<EquipmentAsset> SHULKER_BACKPACK_ASSET = ResourceKey.create(
            EquipmentAssets.ROOT_ID,
            Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "shulker_backpack"));
    public static final Item SHULKERBACKPACK = registerItem("shulker_backpack", ShulkerBackpackItem::new, new Item.Properties()
            .stacksTo(1)
            .component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)
            .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST)
                    .setEquipSound(SoundEvents.ARMOR_EQUIP_LEATHER)
                    .setAsset(SHULKER_BACKPACK_ASSET)
                    .build()));

    private static Item registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name),
                factory.apply(properties
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name)))));
    }

    public static void registerModItems() {
        JustEnoughEndUpdate.LOGGER.info("Registering Mod Items for" + JustEnoughEndUpdate.MOD_ID);
    }
}
