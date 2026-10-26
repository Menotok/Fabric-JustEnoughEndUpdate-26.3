package net.menotok.justenoughendupdate.block;

import net.minecraft.world.level.block.BaseCoralFanBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ChoralFanBlock extends BaseCoralFanBlock {

    public ChoralFanBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }
}