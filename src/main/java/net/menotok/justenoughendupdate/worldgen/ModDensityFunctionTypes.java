package net.menotok.justenoughendupdate.worldgen;

import com.mojang.serialization.MapCodec;
import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModDensityFunctionTypes {
    public static final MapCodec<LayerIslandFunction> LAYER_ISLAND = Registry.register(
            BuiltInRegistries.DENSITY_FUNCTION_TYPE,
            Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "layer_island"),
            LayerIslandFunction.CODEC
    );

    public static void register() {
        JustEnoughEndUpdate.LOGGER.info("Registering density function types for " + JustEnoughEndUpdate.MOD_ID);
    }
}