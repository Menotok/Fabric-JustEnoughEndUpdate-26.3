package net.menotok.justenoughendupdate.tags;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> CHORAL_BLOCKS;
    public static final TagKey<Block> CHORAL_WALL_FANS;
    public static final TagKey<Block> CHORAL_FANS;
    public static final TagKey<Block> CHORAL_PLANTS;

    public static final TagKey<Block> TENOR_SPROUTS;
    public static final TagKey<Block> SUPPORTS_END_PLANTS;

    public static final TagKey<Block> CAPPELLA_VINES_GENERATE_ON;

    private ModBlockTags() {
    }

    private static TagKey<Block> create(final String name) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name));
    }

    static {
        CHORAL_BLOCKS = create("choral_blocks");
        CHORAL_WALL_FANS = create("wall_chorals");
        CHORAL_FANS = create("chorals");
        CHORAL_PLANTS = create("choral_plants");
        TENOR_SPROUTS = create("tenor_sprouts");
        SUPPORTS_END_PLANTS = create("support_end_plants");
        CAPPELLA_VINES_GENERATE_ON = create("cappella_vines_generate_on");
    }
}