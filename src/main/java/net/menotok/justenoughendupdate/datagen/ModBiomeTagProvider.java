package net.menotok.justenoughendupdate.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.menotok.justenoughendupdate.worldgen.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends FabricTagsProvider<Biome> {
    public ModBiomeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BIOME, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(BiomeTags.HAS_NETHER_FOSSIL).add(ModBiomes.END_LOWLANDS, ModBiomes.END_SHATTERS, Biomes.END_BARRENS, Biomes.END_MIDLANDS);
    }
}
