package net.menotok.justenoughendupdate.block.custom;

import net.minecraft.world.level.block.BaseCoralWallFanBlock;

public class ChoralWallFanBlock extends BaseCoralWallFanBlock {

    public ChoralWallFanBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }
}