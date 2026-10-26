package net.menotok.justenoughendupdate.worldgen;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.menotok.justenoughendupdate.block.ModBlocks;
import net.menotok.justenoughendupdate.worldgen.feature.EndSurfaceFeature;
import net.menotok.justenoughendupdate.worldgen.feature.SmallIslandFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModFeatures {
    public static final ResourceKey<Feature> END_SURFACE = registerKey("end_surface");
    public static final ResourceKey<Feature> SMALL_ISLAND = registerKey("small_island");
    public static final ResourceKey<Feature> END_PLANTS = registerKey("end_plants");

    public static void bootstrap(BootstrapContext<Feature> context) {
        context.register(END_SURFACE, new EndSurfaceFeature());
        context.register(SMALL_ISLAND, new SmallIslandFeature());
        context.register(END_PLANTS, new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.TENOR_SPROUTS)));
        ChoralFeatures.bootstrap(context);
    }

    public static ResourceKey<Feature> registerKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name));
    }
}