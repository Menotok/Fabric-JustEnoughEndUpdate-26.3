package net.menotok.justenoughendupdate.worldgen;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.menotok.justenoughendupdate.tags.ModBlockTags;
import net.menotok.justenoughendupdate.worldgen.feature.ChoralFeatures;
import net.menotok.justenoughendupdate.worldgen.feature.CappellaVinesFeature;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> END_SURFACE_PLACED = registerKey("end_surface_placed");
    public static final ResourceKey<PlacedFeature> SMALL_ISLAND_PLACED = registerKey("small_island_placed");
    public static final ResourceKey<PlacedFeature> CHORAL_PLACED = registerKey("choral_placed");
    public static final ResourceKey<PlacedFeature> END_PLANTS_PLACED = registerKey("end_plants_placed");
    public static final ResourceKey<PlacedFeature> CAPPELLA_VINES_PLACED = registerKey("cappella_vines_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var features = context.lookup(Registries.FEATURE);

        register(context, END_SURFACE_PLACED, features.getOrThrow(ModFeatures.END_SURFACE), List.of(
                BiomeFilter.biome()));
        register(context, SMALL_ISLAND_PLACED, features.getOrThrow(ModFeatures.SMALL_ISLAND), List.of(
                RarityFilter.onAverageOnceEvery(12),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(15), VerticalAnchor.absolute(250)),
                BiomeFilter.biome()));

        register(context, CHORAL_PLACED, features.getOrThrow(ChoralFeatures.CHORAL_VEGETATION), List.of(
                NoiseBasedCountPlacement.of(12, 100, 0),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(20),VerticalAnchor.absolute(236)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(),BlockPredicate.ONLY_IN_AIR_PREDICATE,24),
                OffsetPlacement.above(),
                BiomeFilter.biome()));

        register(context, END_PLANTS_PLACED, features.getOrThrow(ModFeatures.END_PLANTS), List.of(
                NoiseBasedCountPlacement.of(24,50,0.2),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(20),VerticalAnchor.absolute(236)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(),BlockPredicate.ONLY_IN_AIR_PREDICATE,24),
                OffsetPlacement.above(),
                BiomeFilter.biome(),
                CountPlacement.of(32),
                OffsetPlacement.ofTriangle(5, 2),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)));

        register(context, CAPPELLA_VINES_PLACED, features.getOrThrow(CappellaVinesFeature.CAPPELLA_VINES), List.of(
                NoiseBasedCountPlacement.of(24, 100, 0.2),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0),VerticalAnchor.absolute(236)),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(),BlockPredicate.ONLY_IN_AIR_PREDICATE,24),
                OffsetPlacement.of(0, -1, 0),
                BiomeFilter.biome(),
                CountPlacement.of(75),
                OffsetPlacement.ofTriangle(5,2),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesTag(Direction.UP, ModBlockTags.CAPPELLA_VINES_GENERATE_ON)))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<Feature> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}