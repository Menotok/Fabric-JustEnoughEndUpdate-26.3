package net.menotok.justenoughendupdate.tags;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomeTags {
    public static final TagKey<Biome> HAS_END_FOSSIL;

    private ModBiomeTags() {
    }

    private static TagKey<Biome> create(final String name) {
        return TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name));
    }

    static {
        HAS_END_FOSSIL = create("has_structure/end_fossil");
    }
}
