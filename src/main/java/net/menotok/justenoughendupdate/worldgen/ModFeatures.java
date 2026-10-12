package net.menotok.justenoughendupdate.worldgen;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.*;

public class ModFeatures {

    public static void bootstrap(BootstrapContext<Feature> context) {

    }

    public static ResourceKey<Feature> registerKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name));
    }
}