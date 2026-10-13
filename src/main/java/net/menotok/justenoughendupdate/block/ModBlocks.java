package net.menotok.justenoughendupdate.block;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Function;

public class ModBlocks {
    public static final Block END_SAND = registerBlock("end_sand",
            properties -> new FallingBlock(properties.strength(4.5f)
                    .requiresCorrectToolForDrops().sound(SoundType.SAND)) {
                @Override
                public int getDustColor(BlockState blockState, BlockGetter level, BlockPos pos) {
                    return 0;
                }
            });

    public static final Block END_SLATE = registerBlock("end_slate",
            properties -> new Block(properties.strength(4.5f, 9.0F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));



    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name)))));
    }

    public static void registerModBlock() {
        JustEnoughEndUpdate.LOGGER.info("Registering Mod Blocks for" + JustEnoughEndUpdate.MOD_ID);
    }
}
