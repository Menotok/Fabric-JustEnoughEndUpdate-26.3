package net.menotok.justenoughendupdate.block.custom;

import net.menotok.justenoughendupdate.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CappellaVinesBlock extends GrowingPlantHeadBlock {
    private static final VoxelShape SHAPE = Block.column(8.0, 9.0, 16.0);

    public CappellaVinesBlock(final BlockBehaviour.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1);
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(final RandomSource random) {
        return NetherVines.getBlocksToGrowWhenBonemealed(random);
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.CAPPELLA_VINES_PLANT_BLOCK;
    }

    @Override
    protected boolean canGrowInto(final BlockState state) {
        return NetherVines.isValidGrowthState(state);
    }
}
