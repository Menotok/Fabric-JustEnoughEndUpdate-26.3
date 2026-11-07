package net.menotok.justenoughendupdate.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.material.EndMaterialRules;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunctions;

import java.util.List;
import java.util.Optional;

public class ModNoiseSettings {
    public static NoiseGeneratorSettings end(HolderLookup.Provider registries) {
        HolderGetter<DensityFunction> functions = registries.lookupOrThrow(Registries.DENSITY_FUNCTION);

        DensityFunction terrain = NoiseRouterData.getFunction(functions, ModDensityFunction.END_TERRAIN);
        DensityFunction highlands2Map = NoiseRouterData.getFunction(functions, ModDensityFunction.HIGHLANDS_2_MAP);
        DensityFunction highlands3Map = NoiseRouterData.getFunction(functions, ModDensityFunction.HIGHLANDS_3_MAP);
        DensityFunction islands = NoiseRouterData.getFunction(functions, NoiseRouterData.END_ISLANDS);

        DensityFunction finalDensity = DensityFunctions.add(
                NoiseRouterData.postProcess(terrain, 8, 4),
                DensityFunctions.beardifier());

        NoiseRouter router = new NoiseRouter(
                DensityFunctions.zero(),   // temperature
                DensityFunctions.zero(),   // vegetation
                highlands2Map,             // continents
                islands,                   // erosion
                terrain,                   // depth
                highlands3Map,             // ridges
                DensityFunctions.zero(),   // chunk_surface_level
                finalDensity);             // final_density

        return new NoiseGeneratorSettings(
                NoiseSettings.create(0, 256),
                Blocks.END_STONE.defaultBlockState(),
                Blocks.AIR.defaultBlockState(),
                router,
                registries.lookupOrThrow(Registries.MATERIAL_RULE).getOrThrow(EndMaterialRules.END),
                List.of(),
                0,
                true,
                Optional.empty(),
                true,
                new NoiseGeneratorSettings.DebugFunctions(List.of(
                        new NoiseGeneratorSettings.DebugFunctionEntry("N", router.finalDensity()),
                        new NoiseGeneratorSettings.DebugFunctionEntry("IS", router.erosion()))));
    }
}