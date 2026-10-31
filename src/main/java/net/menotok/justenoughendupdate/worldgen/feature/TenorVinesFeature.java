package net.menotok.justenoughendupdate.worldgen.feature;

import net.menotok.justenoughendupdate.block.ModBlocks;
import net.menotok.justenoughendupdate.worldgen.ModFeatures;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.BlockColumnFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;

import java.util.List;

public class TenorVinesFeature {
    public static final ResourceKey<Feature> TENOR_VINES = ModFeatures.registerKey("tenor_vines");

    private static Feature createTenorVines(final int minHeight, final int maxHeight, final Direction direction, final Block mainBlock, final Block tipBlock) {
        IntProvider mainHeight = new WeightedListInt(
                WeightedList.<IntProvider>builder()
                        .add(UniformInt.of(minHeight - 1, maxHeight - 1), 10)
                        .add(UniformInt.of(minHeight, maxHeight * 2 - 1), 2)
                        .add(ConstantInt.of(minHeight - 1), 3)
                        .build()
        );
        return new BlockColumnFeature(
                List.of(
                        BlockColumnFeature.layer(mainHeight, BlockStateProvider.of(mainBlock)),
                        BlockColumnFeature.layer(
                                ConstantInt.of(1), new RandomizedIntStateProvider(BlockStateProvider.of(tipBlock), GrowingPlantHeadBlock.AGE, UniformInt.of(17, 25))
                        )
                ),
                direction,
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                true
        );
    }

    public static void bootstrap(BootstrapContext<Feature> context) {
        context.register(TENOR_VINES, createTenorVines(2,9, Direction.DOWN, ModBlocks.TENOR_VINES_PLANT_BLOCK, ModBlocks.TENOR_VINES_BLOCK));
    }
}