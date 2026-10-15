package net.menotok.justenoughendupdate.mixin;

import net.menotok.justenoughendupdate.worldgen.EndLayerBiomes;
import net.menotok.justenoughendupdate.worldgen.ModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.stream.Stream;

@Mixin(TheEndBiomeSource.class) public class TheEndBiomeSourceMixin {

    @Inject(method = "getNoiseBiome", at = @At("HEAD"), cancellable = true)
    private void justenoughendupdate$layerBiomes(int quartX, int quartY, int quartZ, Climate.Sampler sampler, CallbackInfoReturnable<Holder<Biome>> cir) {
        Holder<Biome> biome = EndLayerBiomes.biomeFor(
                QuartPos.toBlock(quartX),
                QuartPos.toBlock(quartY),
                QuartPos.toBlock(quartZ),
                sampler.depth().sampleValue(QuartPos.toBlock(quartX), QuartPos.toBlock(quartY), QuartPos.toBlock(quartZ))
        );
        if (biome != null) {
            cir.setReturnValue(biome);
        }
    }

    @Inject(method = "collectPossibleBiomes", at = @At("RETURN"), cancellable = true)
    private void justenoughendupdate$addLayerBiomes(CallbackInfoReturnable<Stream<Holder<Biome>>> cir) {
        if (EndLayerBiomes.biomeRegistry == null) {
            return;
        }
        cir.setReturnValue(Stream.concat(
                cir.getReturnValue(),
                Stream.of(
                        EndLayerBiomes.biomeRegistry.getOrThrow(ModBiomes.END_LOWLANDS),
                        EndLayerBiomes.biomeRegistry.getOrThrow(ModBiomes.END_SHATTERS),
                        EndLayerBiomes.biomeRegistry.getOrThrow(ModBiomes.END_VOID)
                )
        ));
    }
}
