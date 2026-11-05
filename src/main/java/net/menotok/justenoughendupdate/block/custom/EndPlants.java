package net.menotok.justenoughendupdate.block.custom;

import net.menotok.justenoughendupdate.tags.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EndPlants extends VegetationBlock {
    public EndPlants(BlockBehaviour.Properties properties) {
        super(properties);
    }
    private static final VoxelShape SHAPE = Block.column(16.0, 0.0, 13.0);

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return state.is(ModBlockTags.SUPPORTS_END_PLANTS);
    }
}
