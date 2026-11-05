package net.menotok.justenoughendupdate.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.menotok.justenoughendupdate.block.ModBlocks;
import net.menotok.justenoughendupdate.tags.ModBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @NullMarked @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SLATE).orElseThrow());

        builder(BlockTags.MINEABLE_WITH_AXE)
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.CHORAL_BLOCK).orElseThrow());

        builder(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SAND).orElseThrow());

        builder(BlockTags.SUPPORTS_CHORUS_PLANT).add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SAND).orElseThrow());
        builder(BlockTags.SUPPORTS_CHORUS_FLOWER).add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SAND).orElseThrow());

        builder(ModBlockTags.CHORAL_BLOCKS).add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.CHORAL_BLOCK).orElseThrow());
        builder(ModBlockTags.CHORAL_PLANTS).add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.CHORAL).orElseThrow());
        builder(ModBlockTags.CHORAL_WALL_FANS).add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.CHORAL_WALL_FAN).orElseThrow());
        builder(ModBlockTags.CHORAL_FANS)
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.CHORAL_FAN).orElseThrow())
                .addTag(ModBlockTags.CHORAL_PLANTS);

        builder(ModBlockTags.SUPPORTS_END_PLANTS)
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SAND).orElseThrow())
                .forceAddTag(BlockTags.SUPPORTS_NETHER_SPROUTS);

        builder(ModBlockTags.CAPPELLA_VINES_GENERATE_ON)
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SAND).orElseThrow())
                .add(BuiltInRegistries.BLOCK.getResourceKey(Blocks.END_STONE).orElseThrow())
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SLATE).orElseThrow());
        builder(BlockTags.CLIMBABLE)
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.CAPPELLA_VINES_BLOCK).orElseThrow())
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.CAPPELLA_VINES_PLANT_BLOCK).orElseThrow());

        builder(BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP)
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SAND).orElseThrow())
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SLATE).orElseThrow());
        builder(BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP_NO_LEAVES)
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SAND).orElseThrow())
                .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.END_SLATE).orElseThrow());
    }
}
