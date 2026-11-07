package net.menotok.justenoughendupdate.mixin;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractContainerScreen.class)
public interface AbstractContainerScreenAccessor {
    @Accessor("leftPos")
    int justenoughendupdate$getLeftPos();

    @Accessor("topPos")
    int justenoughendupdate$getTopPos();

    @Mutable
    @Accessor("imageWidth")
    void justenoughendupdate$setImageWidth(int width);
}
