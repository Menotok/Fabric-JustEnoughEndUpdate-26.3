package net.menotok.justenoughendupdate.worldgen;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.menotok.justenoughendupdate.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> END_SURFACE_PLACED = registerKey("end_surface_placed");
    public static final ResourceKey<PlacedFeature> SMALL_ISLAND_PLACED = registerKey("small_island_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var features = context.lookup(Registries.FEATURE);

        register(context, END_SURFACE_PLACED, features.getOrThrow(ModFeatures.END_SURFACE), List.of(
                BiomeFilter.biome()));
        register(context, SMALL_ISLAND_PLACED, features.getOrThrow(ModFeatures.SMALL_ISLAND), List.of(
                RarityFilter.onAverageOnceEvery(12),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(15), VerticalAnchor.absolute(250)),
                BiomeFilter.biome()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<Feature> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}