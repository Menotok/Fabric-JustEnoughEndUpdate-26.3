package net.menotok.justenoughendupdate.worldgen;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunctions;

public class ModDensityFunction {
    public static final ResourceKey<DensityFunction> END_TERRAIN = registerKey("end/terrain");
    public static final ResourceKey<DensityFunction> HIGHLANDS_2_MAP = registerKey("end/highlands_2_map");
    public static final ResourceKey<DensityFunction> HIGHLANDS_3_MAP = registerKey("end/highlands_3_map");

    public static void bootstrap(BootstrapContext<DensityFunction> context) {
        var functions = context.lookup(Registries.DENSITY_FUNCTION);

        DensityFunction vanillaIslands = new DensityFunctions.HolderHolder(functions.getOrThrow(vanillaKey("end/islands")));
        DensityFunction detail = new DensityFunctions.HolderHolder(functions.getOrThrow(vanillaKey("end/base_3d_noise")));

        var highlands2Map = context.register(HIGHLANDS_2_MAP, new LayerIslandFunction(-0.92F, 0.8F, 1000));
        var highlands3Map = context.register(HIGHLANDS_3_MAP, new LayerIslandFunction(-0.94F, 0.6F, 2000));
        DensityFunction layer0 = islandLayer(vanillaIslands, detail, 56.0F, 52F);
        DensityFunction layer1 = islandLayer(new DensityFunctions.HolderHolder(highlands2Map), detail, 130.0F, 44.0F);
        DensityFunction layer2 = islandLayer(new DensityFunctions.HolderHolder(highlands3Map), detail, 190.0F, 36F);

        DensityFunction upperLayers = DensityFunctions.max(layer1, layer2);
        DensityFunction allLayers = DensityFunctions.max(layer0, upperLayers);

        context.register(END_TERRAIN, allLayers);
    }

    private static DensityFunction islandLayer(DensityFunction islandMap, DensityFunction detail, float height, float depth) {
        DensityFunction y = DensityFunctions.yClampedGradient(0, 256, 0.0F, 256.0F);
        DensityFunction distance = DensityFunctions.add(y, DensityFunctions.constant(-height));

        DensityFunction topFade = DensityFunctions.add(DensityFunctions.constant(1.0F), DensityFunctions.mul(distance, DensityFunctions.constant(-1.0F / 256.0F))).clamp(0.0F, 1.0F);

        float fadeLength = depth * 0.8F;

        DensityFunction bottomFade = DensityFunctions.add(DensityFunctions.constant(depth / fadeLength),DensityFunctions.mul(distance, DensityFunctions.constant(1.0F / fadeLength))).clamp(0.0F, 1.0F);

        DensityFunction terrain = DensityFunctions.add(islandMap, detail);
        DensityFunction withTop = DensityFunctions.lerp(topFade, DensityFunctions.constant(-23.4375F), terrain);
        return DensityFunctions.lerp(bottomFade, DensityFunctions.constant(-0.234375F), withTop);
    }

    public static ResourceKey<DensityFunction> registerKey(String name) {
        return ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name));
    }
    private static ResourceKey<DensityFunction> vanillaKey(String name) {
        return ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.withDefaultNamespace(name));
    }
}