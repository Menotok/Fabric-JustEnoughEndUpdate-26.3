package net.menotok.justenoughendupdate.worldgen.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.menotok.justenoughendupdate.worldgen.ModBiomes;
import net.menotok.justenoughendupdate.worldgen.ModPlacedFeatures;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                ModPlacedFeatures.END_SURFACE_PLACED
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.END_VOID),
                GenerationStep.Decoration.RAW_GENERATION,
                ModPlacedFeatures.SMALL_ISLAND_PLACED
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS, Biomes.END_BARRENS),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CHORAL_PLACED
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS, Biomes.END_BARRENS),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.END_PLANTS_PLACED
        );

        BiomeModifications.create(Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "remove_vanilla_small_islands"))
                .add(ModificationPhase.REMOVALS,
                        BiomeSelectors.foundInTheEnd(),
                        context -> context.getGenerationSettings().removeFeature(EndPlacements.END_ISLAND_DECORATED));
    }
}
