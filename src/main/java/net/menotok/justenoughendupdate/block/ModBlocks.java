package net.menotok.justenoughendupdate.block;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.menotok.justenoughendupdate.block.custom.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import org.jspecify.annotations.NullMarked;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ModBlocks {
    public static final Block END_SAND = registerBlock("end_sand",
            properties -> new FallingBlock(properties.strength(2.0F, 6.0F)
                    .requiresCorrectToolForDrops().sound(SoundType.SAND)) {
                @NullMarked @Override
                public int getDustColor(BlockState blockState, BlockGetter level, BlockPos pos) {
                    return 0;
                }});
    public static final Block END_SLATE = registerBlock("end_slate",
            properties -> new Block(properties.strength(4.5F, 12.0F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final Block CHORAL_BLOCK = registerBlock( "choral_block",
            properties -> new Block(properties.strength(1.5F, 6.0F)
                    .requiresCorrectToolForDrops().sound(SoundType.CORAL_BLOCK)),
            p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
    public static final Block CHORAL_FAN = registerBlockWithoutItem( "choral_fan",
            properties -> new ChoralFanBlock(properties.instabreak()
                    .requiresCorrectToolForDrops().noCollision().sound(SoundType.CORAL_BLOCK)));
    public static final Block CHORAL_WALL_FAN = registerBlockWithoutItem( "choral_wall_fan",
            properties -> new ChoralWallFanBlock(properties.instabreak()
                    .requiresCorrectToolForDrops().noCollision().sound(SoundType.CORAL_BLOCK)));
    public static final Block CHORAL = registerBlock( "choral",
            properties -> new ChoralPlantBlock(properties.instabreak()
                    .requiresCorrectToolForDrops().noCollision().sound(SoundType.CORAL_BLOCK)),
            p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));

    public static final Block TENOR_SPROUTS = registerBlock("tenor_sprouts",
            properties -> new EndPlants(properties.replaceable().noCollision().instabreak()
                    .sound(SoundType.NETHER_SPROUTS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.POPPED)));

    public static final Block CAPPELLA_VINES_BLOCK = registerBlock("cappella_vines",
            properties -> new CappellaVinesBlock(properties.randomTicks().noCollision().instabreak()
                    .sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.POPPED)));
    public static final Block CAPPELLA_VINES_PLANT_BLOCK = registerBlockWithoutItem("cappella_vines_plant",
            properties -> new CappellaVinesPlantBlock(properties.randomTicks().noCollision().instabreak()
                    .sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.POPPED)));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        return registerBlock(name, function, p -> p);
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function, UnaryOperator<Item.Properties> itemProperties) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name))));
        registerBlockItem(name, toRegister, itemProperties);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name), toRegister);
    }

    private static Block registerBlockWithoutItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name))));
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name), toRegister);
    }

    public static final Item CHORAL_FAN_ITEM = Registry.register(
            BuiltInRegistries.ITEM,
            Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "choral_fan"),
            new StandingAndWallBlockItem(
                    CHORAL_FAN,
                    CHORAL_WALL_FAN,
                    Direction.DOWN,
                    new Item.Properties().useBlockDescriptionPrefix()
                            .cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL)
                            .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "choral_fan")))
            )
    );

    private static void registerBlockItem(String name, Block block, UnaryOperator<Item.Properties> itemProperties) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name),
                new BlockItem(block, itemProperties.apply(new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, name))))));
    }

    public static void registerModBlock() {
        JustEnoughEndUpdate.LOGGER.info("Registering Mod Blocks for" + JustEnoughEndUpdate.MOD_ID);
    }
}
