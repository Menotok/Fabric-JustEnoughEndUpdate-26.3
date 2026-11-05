package net.menotok.justenoughendupdate.worldgen.feature;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public record SmallIslandFeature() implements Feature {
    public static final MapCodec<SmallIslandFeature> CODEC = MapCodec.unit(SmallIslandFeature::new);

    @Override
    public MapCodec<SmallIslandFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        SimplexNoise noise = new SimplexNoise(RandomSource.create(level.getSeed()));
        int radius = 3 + random.nextInt(10);

        float stretchX = 0.7F + random.nextFloat() * 0.3F;
        float stretchZ = 0.7F + random.nextFloat() * 0.3F;

        float depthRatio = 1.0F + random.nextFloat() * 0.3F;
        int depth = Math.max(3, Math.round(radius * depthRatio));
        int topHeight = Math.max(1, Math.round(radius * 0.35F));
        int reachX = Mth.ceil(radius * stretchX) + 2;
        int reachZ = Mth.ceil(radius * stretchZ) + 2;

        for (int x = -reachX; x <= reachX; x += 3) {
            for (int z = -reachZ; z <= reachZ; z += 3) {
                for (int y = topHeight + 2; y >= -depth; y -= 3) {
                    if (!level.isEmptyBlock(origin.offset(x, y, z))) {
                        return false;
                    }
                }
            }
        }

            for (int x = -reachX; x <= reachX; x++) {
                for (int z = -reachZ; z <= reachZ; z++) {
                    int surfaceDepth = -1;

                    for (int y = topHeight; y >= -depth; y--) {
                        float nx = x / (radius * stretchX);
                        float nz = z / (radius * stretchZ);
                        float ny = y > 0 ? (float) y / topHeight : (float) -y / depth;
                        float shape = 1.0F - (nx * nx + ny * ny + nz * nz);

                        BlockPos pos = origin.offset(x, y, z);
                        double lumps = noise.get(pos.getX() * 0.06, pos.getY() * 0.06, pos.getZ() * 0.06) * 0.25;

                        if (shape + lumps > 0) {
                            surfaceDepth++;
                            this.setBlock(level, pos, EndSurfaceLayers.blockForDepth(noise, pos.getX(), pos.getZ(), surfaceDepth));
                        } else {
                            surfaceDepth = -1;
                        }
                    }
                }
            }
        return true;
    }
}
