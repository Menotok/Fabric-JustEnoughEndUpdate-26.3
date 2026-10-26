package net.menotok.justenoughendupdate.worldgen;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.EndBiomes;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {
    public static final ResourceKey<Biome> END_LOWLANDS = registerKey("end_lowlands");
    public static final ResourceKey<Biome> END_SHATTERS = registerKey("end_shatters");
    public static final ResourceKey<Biome> END_VOID = registerKey("end_void");

    public static void bootstrap(BootstrapContext<Biome> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var carvers = context.lookup(Registries.CARVER);

        context.register(END_LOWLANDS, EndBiomes.baseEndBiome(
                new BiomeGenerationSettings.Builder(placedFeatures, carvers)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EndPlacements.CHORUS_PLANT)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CHORAL_PLACED)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.END_PLANTS_PLACED)
        ));

        context.register(END_SHATTERS, EndBiomes.baseEndBiome(
                new BiomeGenerationSettings.Builder(placedFeatures, carvers)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CHORAL_PLACED)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.END_PLANTS_PLACED)
        ));

        context.register(END_VOID, EndBiomes.baseEndBiome(
                new BiomeGenerationSettings.Builder(placedFeatures, carvers)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CHORAL_PLACED)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.END_PLANTS_PLACED)
        ));
    }

    public static ResourceKey<Biome> registerKey(String name) {
        return ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name));
    }
}