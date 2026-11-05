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
import net.minecraft.world.level.block.Blocks;

public class ModCreativeModeTabs {

    public static final CreativeModeTab END_UPDATE_ITEMS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "end_update_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BAKED_CHORUS_FRUIT))
                    .title(Component.translatable("creativemodetab.justenoughendupdate.endupdate_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.SHULKERBACKPACK);
                        output.accept(ModItems.BAKED_CHORUS_FRUIT);
                    }).build());

    public static final CreativeModeTab END_UPDATE_PLANTS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "end_update_plants"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.TENOR_SPROUTS))
                    .title(Component.translatable("creativemodetab.justenoughendupdate.endupdate_plants"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.CHORAL);
                        output.accept(ModBlocks.CAPPELLA_VINES_BLOCK);
                        output.accept(ModBlocks.CHORAL_FAN_ITEM);
                        output.accept(ModBlocks.TENOR_SPROUTS);
                    }).build());

                        public static final CreativeModeTab END_UPDATE_BLOCKS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "end_update_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(Blocks.END_STONE))
                    .title(Component.translatable("creativemodetab.justenoughendupdate.endupdate_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.END_SAND);
                        output.accept(ModBlocks.END_SLATE);
                        output.accept(ModBlocks.CHORAL_BLOCK);

                    }).build());

    public static void registerModCreativeModeTabs() {
        JustEnoughEndUpdate.LOGGER.info("Registering Creative Mode Tabs for " + JustEnoughEndUpdate.MOD_ID);
    }
}
