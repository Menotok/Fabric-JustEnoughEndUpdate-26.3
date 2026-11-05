package net.menotok.justenoughendupdate;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.menotok.justenoughendupdate.datagen.*;
import net.menotok.justenoughendupdate.worldgen.*;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class JustEnoughEndUpdateDataGenerator implements DataGeneratorEntrypoint {
	@Override
		public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModRegistryDataProvider::new);
		pack.addProvider(ModBiomeTagProvider::new);
        pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.FEATURE, ModFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
		registryBuilder.add(Registries.STRUCTURE, ModStructures::bootstrap);
		registryBuilder.add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap);
		registryBuilder.add(Registries.DENSITY_FUNCTION, ModDensityFunction::bootstrap);
		registryBuilder.add(Registries.BIOME, ModBiomes::bootstrap);
	}
}
