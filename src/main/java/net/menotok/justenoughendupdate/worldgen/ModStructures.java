package net.menotok.justenoughendupdate.worldgen;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.menotok.justenoughendupdate.tags.ModBiomeTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.structures.NetherFossilStructure;

public class ModStructures {
    public static final ResourceKey<Structure> END_FOSSIL = registerKey("end_fossil");

    public static void bootstrap(BootstrapContext<Structure> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(END_FOSSIL,
                new NetherFossilStructure(
                        new Structure.StructureSettings.Builder(biomes.getOrThrow(ModBiomeTags.HAS_END_FOSSIL))
                                .generationStep(GenerationStep.Decoration.UNDERGROUND_DECORATION)
                                .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                                .build(),
                        UniformHeight.of(VerticalAnchor.absolute(10), VerticalAnchor.belowTop(2))
                )
        );
    }

    private static ResourceKey<Structure> registerKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name));
    }
}
