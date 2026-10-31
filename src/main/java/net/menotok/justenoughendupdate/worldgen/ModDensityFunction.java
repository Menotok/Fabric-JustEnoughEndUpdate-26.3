package net.menotok.justenoughendupdate.worldgen;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunctions;
import net.minecraft.world.level.levelgen.densityfunction.DistanceMetric;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class ModDensityFunction {
    public static final ResourceKey<DensityFunction> END_TERRAIN = registerKey("end/terrain");
    public static final ResourceKey<DensityFunction> LOWLANDS_MAP = registerKey("end/lowlands_map");

    public static void bootstrap(BootstrapContext<DensityFunction> context) {
        var functions = context.lookup(Registries.DENSITY_FUNCTION);

        var noises = context.lookup(Registries.NOISE);
        DensityFunction vanillaIslands = new DensityFunctions.HolderHolder(functions.getOrThrow(vanillaKey("end/islands")));
        DensityFunction detail = new DensityFunctions.HolderHolder(functions.getOrThrow(vanillaKey("end/base_3d_noise")));

        DensityFunction centerDistance = DensityFunctions.sliceY(DensityFunctions.distanceToPoint(Vec3i.ZERO, DistanceMetric.EUCLIDEAN), 0);
        DensityFunction centerMask = DensityFunctions.add(DensityFunctions.mul(centerDistance, DensityFunctions.constant(1.0F / 100.0F)), DensityFunctions.constant(-1000.0F / 100.0F)).clamp(0.0F, 1.0F);

        var lowlandsMap = context.register(LOWLANDS_MAP, islandMap(noises,1000F, 0.45, 0.35F));
        DensityFunction layer0 = islandLayer(vanillaIslands, detail, 56.0F, 52F);
        DensityFunction layer1 = islandLayer(new DensityFunctions.HolderHolder(lowlandsMap), detail, 130.0F, 40.0F);
        DensityFunction layer2 = islandLayer(islandMap(noises, 2000.0F, 0.7, 0.45F), detail, 190.0F, 26F);

        DensityFunction upperLayers = DensityFunctions.max(layer1, layer2);
        DensityFunction maskedUpper = DensityFunctions.lerp(centerMask, DensityFunctions.constant(-1.0F), upperLayers);
        DensityFunction allLayers = DensityFunctions.max(layer0, maskedUpper);

        context.register(END_TERRAIN, allLayers);
    }

    private static DensityFunction islandLayer(DensityFunction islandMap, DensityFunction detail, float height, float depth) {
        DensityFunction y = DensityFunctions.yClampedGradient(0, 256, 0.0F, 256.0F);
        DensityFunction distance = DensityFunctions.add(y, DensityFunctions.constant(-height));

        DensityFunction topFade = DensityFunctions.add(DensityFunctions.constant(1.0F), DensityFunctions.mul(distance, DensityFunctions.constant(-1.0F / 256.0F))).clamp(0.0F, 1.0F);

        float fadeLength = depth * 28.0F / 52.0F;

        DensityFunction bottomFade = DensityFunctions.add(DensityFunctions.constant(52.0F / 28.0F),DensityFunctions.mul(distance, DensityFunctions.constant(1.0F / fadeLength))).clamp(0.0F, 1.0F);

        DensityFunction terrain = DensityFunctions.add(islandMap, detail);
        DensityFunction withTop = DensityFunctions.lerp(topFade, DensityFunctions.constant(-23.4375F), terrain);
        return DensityFunctions.lerp(bottomFade, DensityFunctions.constant(-0.234375F), withTop);
    }

    private static DensityFunction islandMap(HolderGetter<NormalNoise> noises, float offset, double scale, float threshold) {
        {
            DensityFunction noise = DensityFunctions.shiftedNoise2d(
                    DensityFunctions.constant(offset),
                    DensityFunctions.constant(offset),
                    scale,
                    noises.getOrThrow(Noises.CAVE_CHEESE)
            );
            DensityFunction map = DensityFunctions.mul(
                    DensityFunctions.add(noise, DensityFunctions.constant(-threshold)),
                    DensityFunctions.constant(2.0F)
            );
            return DensityFunctions.cache(map);
        }
    }

    public static ResourceKey<DensityFunction> registerKey(String name) {
        return ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name));
    }
    private static ResourceKey<DensityFunction> vanillaKey(String name) {
        return ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.withDefaultNamespace(name));
    }
}