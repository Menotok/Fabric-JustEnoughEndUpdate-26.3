package net.menotok.justenoughendupdate;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.menotok.justenoughendupdate.datagen.ModBiomeTagProvider;
import net.menotok.justenoughendupdate.datagen.ModBlockTagProvider;
import net.menotok.justenoughendupdate.datagen.ModModelProvider;
import net.menotok.justenoughendupdate.datagen.ModRegistryDataProvider;
import net.menotok.justenoughendupdate.worldgen.ModBiomes;
import net.menotok.justenoughendupdate.worldgen.ModDensityFunction;
import net.menotok.justenoughendupdate.worldgen.ModFeatures;
import net.menotok.justenoughendupdate.worldgen.ModPlacedFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class JustEnoughEndUpdateDataGenerator implements DataGeneratorEntrypoint {
	@Override
		public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModRegistryDataProvider::new);
		pack.addProvider(ModBiomeTagProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.FEATURE, ModFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
		registryBuilder.add(Registries.DENSITY_FUNCTION, ModDensityFunction::bootstrap);
		registryBuilder.add(Registries.BIOME, ModBiomes::bootstrap);
	}
}
