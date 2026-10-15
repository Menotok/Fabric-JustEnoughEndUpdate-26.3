package net.menotok.justenoughendupdate.worldgen.feature;

import net.menotok.justenoughendupdate.block.ModBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public class EndSurfaceLayers {
    public static BlockState blockForDepth(SimplexNoise noise, int x, int z, int depth) {
        double n = noise.get(x * 0.05, z * 0.05);
        int sandDepth = 2 + (int) Math.round(n * 1.5);

        double n2 = noise.get(x * 0.03 + 1000, z * 0.03 + 1000);
        int slateStart = sandDepth + 9 + (int) Math.round(n2 * 6);

        if (depth < sandDepth) {
            return ModBlocks.END_SAND.defaultBlockState();
        }
        if (depth >= slateStart) {
            return ModBlocks.END_SLATE.defaultBlockState();
        }
        return Blocks.END_STONE.defaultBlockState();
    }
}
