package net.menotok.justenoughendupdate.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.menotok.justenoughendupdate.block.ModBlocks;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super (packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.END_SAND);
        dropSelf(ModBlocks.END_SLATE);
        dropSelf(ModBlocks.CHORAL_BLOCK);
        dropSelf(ModBlocks.CHORAL_FAN);
        dropSelf(ModBlocks.CHORAL_WALL_FAN);
        dropSelf(ModBlocks.TENOR_SPROUTS);
        addNetherVinesDropTable(ModBlocks.CAPPELLA_VINES_BLOCK, ModBlocks.CAPPELLA_VINES_PLANT_BLOCK);
    }
}
