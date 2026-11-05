package net.menotok.justenoughendupdate.menu;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<ShulkerBackpackMenu> SHULKER_BACKPACK = Registry.register(
            BuiltInRegistries.MENU,
            Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "shulker_backpack"),
            new MenuType<>((id, inventory) -> new ShulkerBackpackMenu(id, inventory, new SimpleContainer(27)), FeatureFlags.VANILLA_SET));

    public static void register() {
        JustEnoughEndUpdate.LOGGER.info("Registering menu types for " + JustEnoughEndUpdate.MOD_ID);
    }
}