package net.menotok.justenoughendupdate.worldgen;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class EndLayerBiomes {
    public static Registry<Biome> biomeRegistry;
    public static final int END_HIGHLANDS_2_MIN_Y = 85;
    public static final int END_HIGHLANDS_3_MIN_Y = 160;
    public static final double VOID_THRESHOLD = -0.1;
    public static final double HIGHLANDS_THRESHOLD = 0.25;
    public static final double MIDLANDS_THRESHOLD = -0.0625;

    public static Holder<Biome> biomeFor(int blockX, int blockY, int blockZ, double density, double highlands2Map, double highlands3Map) {        if (biomeRegistry == null) {
            return null;
        }

        int chunkX = blockX >> 4;
        int chunkZ = blockZ >> 4;

        if ((long) chunkX * chunkX + (long) chunkZ * chunkZ <= 4096L) {
            return null;
        }

        if (density < VOID_THRESHOLD) {
            return biomeRegistry.getOrThrow(ModBiomes.END_VOID);
        }

        if (blockY >= END_HIGHLANDS_3_MIN_Y) {
            if (highlands3Map > HIGHLANDS_THRESHOLD) {
                return biomeRegistry.getOrThrow(ModBiomes.END_HIGHLANDS_3);
            }
            if (highlands3Map >= MIDLANDS_THRESHOLD) {
                return biomeRegistry.getOrThrow(Biomes.END_MIDLANDS);
            }
            return biomeRegistry.getOrThrow(Biomes.END_BARRENS);
        }

        if (blockY >= END_HIGHLANDS_2_MIN_Y) {
            if (highlands2Map > HIGHLANDS_THRESHOLD) {
                return biomeRegistry.getOrThrow(ModBiomes.END_HIGHLANDS_2);
            }
            if (highlands2Map >= MIDLANDS_THRESHOLD) {
                return biomeRegistry.getOrThrow(Biomes.END_MIDLANDS);
            }
            return biomeRegistry.getOrThrow(Biomes.END_BARRENS);
        }
        return null;
    }

    public static void register() {
        DynamicRegistrySetupCallback.EVENT.register(view ->
                view.getOptional(Registries.BIOME).ifPresent(registry -> biomeRegistry = registry)
        );
    }
}