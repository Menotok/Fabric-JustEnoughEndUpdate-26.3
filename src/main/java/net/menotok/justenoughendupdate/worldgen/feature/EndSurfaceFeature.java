package net.menotok.justenoughendupdate.worldgen.feature;

import com.mojang.serialization.MapCodec;
import net.menotok.justenoughendupdate.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public record EndSurfaceFeature() implements Feature {
    public static final MapCodec<EndSurfaceFeature> CODEC = MapCodec.unit(EndSurfaceFeature::new);

    @Override
    public MapCodec<EndSurfaceFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        SimplexNoise noise = new SimplexNoise(RandomSource.create(level.getSeed()));

        // Work on the whole chunk this feature runs in
        int startX = origin.getX() & ~15;
        int startZ = origin.getZ() & ~15;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        boolean changed = false;

        for (int x = startX; x < startX + 16; x++) {
            for (int z = startZ; z < startZ + 16; z++) {
                int top = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);

                int depth = -1; // -1 = we're in air
                for (int y = top; y >= level.getMinY(); y--) {
                    pos.set(x, y, z);
                    BlockState state = level.getBlockState(pos);

                    if (state.isAir()) {
                        depth = -1;
                        continue;
                    }
                    if (!state.is(Blocks.END_STONE)) {
                        continue;
                    }

                    depth++;
                    BlockState newState = EndSurfaceLayers.blockForDepth(noise, x, z, depth);
                    if (!newState.is(Blocks.END_STONE)) {
                        this.setBlock(level, pos, newState);
                        changed = true;
                    }
                }
            }
        }
        return changed;
    }
}