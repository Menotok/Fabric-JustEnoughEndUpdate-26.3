package net.menotok.justenoughendupdate.block;

import net.minecraft.world.level.block.BaseCoralPlantBlock;

public class ChoralPlantBlock extends BaseCoralPlantBlock {

    public ChoralPlantBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }
}