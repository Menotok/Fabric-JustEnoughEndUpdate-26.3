package net.menotok.justenoughendupdate.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.menotok.justenoughendupdate.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(BlockTags.SUPPORTS_CHORUS_PLANT).add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SAND).orElseThrow());
        builder(BlockTags.SUPPORTS_CHORUS_FLOWER).add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SAND).orElseThrow());
        builder(BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP)
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SAND).orElseThrow())
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SLATE).orElseThrow());
        builder(BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP_NO_LEAVES)
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SAND).orElseThrow())
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SLATE).orElseThrow());
    }
}
