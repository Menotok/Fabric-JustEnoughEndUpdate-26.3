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
    public static final ResourceKey<Biome> END_HIGHLANDS_2 = registerKey("end_highlands_2");
    public static final ResourceKey<Biome> END_HIGHLANDS_3 = registerKey("end_highlands_3");
    public static final ResourceKey<Biome> END_VOID = registerKey("end_void");

    public static void bootstrap(BootstrapContext<Biome> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var carvers = context.lookup(Registries.CARVER);

        context.register(END_HIGHLANDS_2, EndBiomes.baseEndBiome(
                new BiomeGenerationSettings.Builder(placedFeatures, carvers)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EndPlacements.CHORUS_PLANT)
        ));

        context.register(END_HIGHLANDS_3, EndBiomes.baseEndBiome(
                new BiomeGenerationSettings.Builder(placedFeatures, carvers)
        ));

        context.register(END_VOID, EndBiomes.baseEndBiome(
                new BiomeGenerationSettings.Builder(placedFeatures, carvers)
        ));
    }

    public static ResourceKey<Biome> registerKey(String name) {
        return ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name));
    }
}