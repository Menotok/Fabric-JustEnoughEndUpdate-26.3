package net.menotok.justenoughendupdate.worldgen;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;

public class EndLayerBiomes {
    public static Registry<Biome> biomeRegistry;
    public static final int LAYER_1_MIN_Y = 85;
    public static final int LAYER_2_MIN_Y = 160;

    public static Holder<Biome> biomeFor(int blockX, int blockY, int blockZ) {
        if (biomeRegistry == null) {
            return null;
        }

        int chunkX = blockX >> 4;
        int chunkZ = blockZ >> 4;
        if ((long) chunkX * chunkX + (long) chunkZ * chunkZ <= 4096L) {
            return null;
        }

        if (blockY >= LAYER_2_MIN_Y) {
            return biomeRegistry.getOrThrow(ModBiomes.END_LAYER_2);
        }
        if (blockY >= LAYER_1_MIN_Y) {
            return biomeRegistry.getOrThrow(ModBiomes.END_LAYER_1);
        }
        return null;
    }

    public static void register() {
        DynamicRegistrySetupCallback.EVENT.register(view ->
                view.getOptional(Registries.BIOME).ifPresent(registry -> biomeRegistry = registry)
        );
    }
}