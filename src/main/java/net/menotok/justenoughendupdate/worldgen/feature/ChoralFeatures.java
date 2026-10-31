package net.menotok.justenoughendupdate.worldgen.feature;

import net.menotok.justenoughendupdate.block.ModBlocks;
import net.menotok.justenoughendupdate.tags.ModBlockTags;
import net.menotok.justenoughendupdate.worldgen.ModFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomBlockProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RotatedBlockProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.OffsetPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RandomChancePlacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChoralFeatures {
    public static final ResourceKey<Feature> CHORAL_BLOCK = ModFeatures.registerKey("choral/choral_block");

    public static final ResourceKey<Feature> CHORAL_DECORATION = ModFeatures.registerKey("choral/block_decoration");
    public static final ResourceKey<Feature> CHORAL_VEGETATION = ModFeatures.registerKey("choral/vegetation");

    private static Holder<PlacedFeature> WallChoral(HolderGetter<Block> blocks, Direction direction) {
        return PlacementUtils.inlinePlaced(
                new SimpleBlockFeature(new RotatedBlockProvider(
                        Holder.direct(new RandomBlockProvider(blocks.getOrThrow(ModBlockTags.CHORAL_WALL_FANS))),
                        Optional.of(direction)
                )),
                new RandomChancePlacement(0.2F),
                OffsetPlacement.of(direction),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.AIR))
        );
    }

    private static Feature ChoralBlock(final HolderGetter<Feature> features,    final Block block) {
        return new OverlayFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(new SimpleBlockFeature(BlockStateProvider.of(block))),
                        PlacementUtils.inlinePlaced(features.getOrThrow(CHORAL_DECORATION))
                )
        );
    }

    private static List<Holder<PlacedFeature>> ChoralShapes(Holder<Feature> choralBlock, BlockPredicateFilter allowed) {
        Holder<PlacedFeature> checkedBlock = PlacementUtils.inlinePlaced(choralBlock, allowed);

        return List.of(
                PlacementUtils.inlinePlaced(new CoralTreeFeature(checkedBlock)),
                PlacementUtils.inlinePlaced(new CoralClawFeature(checkedBlock)),
                PlacementUtils.inlinePlaced(choralBlock,
                        OffsetPlacement.vertical(UniformInt.of(-3, -1)),
                        new CuboidPlacement(UniformInt.of(3, 5), UniformInt.of(3, 5), false, false),
                        new RandomChancePlacement(0.9F),
                        allowed)
        );
    }

    public static void bootstrap(BootstrapContext<Feature> context) {
        var blocks = context.lookup(Registries.BLOCK);
        var features = context.lookup(Registries.FEATURE);

        Holder<PlacedFeature> Choral = PlacementUtils.inlinePlaced(
                new SimpleBlockFeature(new RandomBlockProvider(blocks.getOrThrow(ModBlockTags.CHORAL_FANS)))
        );
        Holder<PlacedFeature> nothing = PlacementUtils.inlinePlaced(new NoOpFeature());

        Holder<PlacedFeature> topDecoration = PlacementUtils.inlinePlaced(
                new WeightedRandomSelectorFeature(WeightedList.of(
                        new Weighted<>(Choral, 20),
                        new Weighted<>(nothing, 60)
                )),
                OffsetPlacement.above(),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.AIR))
        );

        context.register(CHORAL_DECORATION, new OverlayFeature(HolderSet.direct(
                topDecoration,
                WallChoral(blocks, Direction.NORTH),
                WallChoral(blocks, Direction.EAST),
                WallChoral(blocks, Direction.SOUTH),
                WallChoral(blocks, Direction.WEST)
        )));

        BlockPredicateFilter ChoralAllowed = BlockPredicateFilter.forPredicate(
                BlockPredicate.allOf(
                        BlockPredicate.anyOf(BlockPredicate.matchesBlocks(Blocks.AIR), BlockPredicate.matchesTag(ModBlockTags.CHORAL_FANS)),
                        BlockPredicate.matchesBlocks(Direction.UP, Blocks.AIR)
                )
        );

        Holder.Reference<Feature> choralBlock = context.register(CHORAL_BLOCK, ChoralBlock(features, ModBlocks.CHORAL_BLOCK));

        List<Holder<PlacedFeature>> allShapes = new ArrayList<>();
        allShapes.addAll(ChoralShapes(choralBlock, ChoralAllowed));

        context.register(CHORAL_VEGETATION, new SimpleRandomSelectorFeature(HolderSet.direct(allShapes)));
    }
}
