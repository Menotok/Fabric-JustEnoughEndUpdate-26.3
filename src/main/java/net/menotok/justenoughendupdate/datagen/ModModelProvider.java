package net.menotok.justenoughendupdate.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.menotok.justenoughendupdate.block.ModBlocks;
import net.menotok.justenoughendupdate.item.ModItems;
import net.menotok.justenoughendupdate.tags.ModBlockTags;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createRotatedVariantBlock(ModBlocks.END_SAND);
        blockModelGenerators.createTrivialCube(ModBlocks.END_SLATE);

        blockModelGenerators.createTrivialCube(ModBlocks.CHORAL_BLOCK);
        blockModelGenerators.createCoralFans(ModBlocks.CHORAL_FAN,ModBlocks.CHORAL_WALL_FAN);
        blockModelGenerators.createCrossBlockWithDefaultItem(ModBlocks.CHORAL, BlockModelGenerators.PlantType.NOT_TINTED);

        blockModelGenerators.createCrossBlockWithDefaultItem(ModBlocks.TENOR_SPROUTS, BlockModelGenerators.PlantType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.TEST, ModelTemplates.FLAT_ITEM);
    }
}