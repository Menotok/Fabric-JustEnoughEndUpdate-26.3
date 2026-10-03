package net.menotok.justenoughendupdate.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.menotok.justenoughendupdate.block.ModBlocks;
import net.menotok.justenoughendupdate.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    public static final CreativeModeTab END_UPDATE_ITEMS_Tab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "end_update_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TEST))
                    .title(Component.translatable("creativemodetab.justenoughendupdate.endupdate_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.TEST);

                    }).build());

    public static final CreativeModeTab END_UPDATE_BLOCKS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "end_update_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.END_SAND))
                    .title(Component.translatable("creativemodetab.justenoughendupdate.endupdate_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.END_SAND);
                        output.accept(ModBlocks.END_SLATE);

                    }).build());


    public static void registerModCreativeModeTabs() {
        JustEnoughEndUpdate.LOGGER.info("Registering Creative Mode Tabs for " + JustEnoughEndUpdate.MOD_ID);
    }
}
