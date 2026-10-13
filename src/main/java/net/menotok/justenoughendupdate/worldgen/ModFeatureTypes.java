package net.menotok.justenoughendupdate.worldgen;

import com.mojang.serialization.MapCodec;
import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.menotok.justenoughendupdate.worldgen.feature.EndSurfaceFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModFeatureTypes {
    public static final MapCodec<EndSurfaceFeature> END_SURFACE = Registry.register(
            BuiltInRegistries.FEATURE_TYPE,
            Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "end_surface"),
            EndSurfaceFeature.CODEC
    );

    public static void register() {
        JustEnoughEndUpdate.LOGGER.info("Registering feature types for " + JustEnoughEndUpdate.MOD_ID);
    }
}