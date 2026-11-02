package net.menotok.justenoughendupdate.worldgen.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.menotok.justenoughendupdate.worldgen.ModBiomes;
import net.menotok.justenoughendupdate.worldgen.ModPlacedFeatures;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModWorldGeneration {
    private static final List<ResourceKey<Biome>> LUSH = List.of(Biomes.END_HIGHLANDS, ModBiomes.END_HIGHLANDS_2);
    private static final List<ResourceKey<Biome>> VOID = List.of(ModBiomes.END_VOID);
    private static final List<ResourceKey<Biome>> ALL_END = List.of(
            Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS, Biomes.END_BARRENS,
            ModBiomes.END_HIGHLANDS_2, ModBiomes.END_HIGHLANDS_3, ModBiomes.END_VOID);

    public static void generateModWorldGen() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                ModPlacedFeatures.END_SURFACE_PLACED
        );

        add(VOID, GenerationStep.Decoration.RAW_GENERATION, ModPlacedFeatures.SMALL_ISLAND_PLACED);
        add(LUSH, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CHORAL_PLACED);
        add(ALL_END, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.END_PLANTS_PLACED);
        add(ALL_END, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TENOR_VINES_PLACED);

        BiomeModifications.create(Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "remove_vanilla_small_islands"))
                .add(ModificationPhase.REMOVALS,
                        BiomeSelectors.foundInTheEnd(),
                        context -> context.getGenerationSettings().removeFeature(EndPlacements.END_ISLAND_DECORATED));
    }

    private static void add(List<ResourceKey<Biome>> biomes, GenerationStep.Decoration step, ResourceKey<PlacedFeature> feature) {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(biomes), step, feature);
    }
}