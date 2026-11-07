package net.menotok.justenoughendupdate.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.menotok.justenoughendupdate.worldgen.ModNoiseSettings;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.concurrent.CompletableFuture;

public class ModRegistryDataProvider extends FabricDynamicRegistryProvider {
    public ModRegistryDataProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {

        entries.addAll(registries.lookupOrThrow(Registries.FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.STRUCTURE));
        entries.addAll(registries.lookupOrThrow(Registries.STRUCTURE_SET));
        entries.addAll(registries.lookupOrThrow(Registries.DENSITY_FUNCTION));
        entries.addAll(registries.lookupOrThrow(Registries.BIOME));

        entries.add(NoiseGeneratorSettings.END, ModNoiseSettings.end(registries));
    }

    @Override
    public String getName() {
        return "JustEnoughEndUpdate Data Provider";
    }
}
