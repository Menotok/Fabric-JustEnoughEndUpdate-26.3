package net.menotok.justenoughendupdate.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.menotok.justenoughendupdate.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractRecipeBookScreen.class)
public class InventoryScreenMixin {

    @Unique
    private boolean justenoughendupdate$isInventoryWithBackpack() {
        Minecraft client = Minecraft.getInstance();
        return (Object) this instanceof InventoryScreen
                && client.player != null
                && client.player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.SHULKERBACKPACK);
    }

    @Inject(method = "init", at = @At("HEAD"))
    private void justenoughendupdate$widenForBackpack(CallbackInfo ci) {
        if (!((Object) this instanceof InventoryScreen)) {
            return;
        }
        ((AbstractContainerScreenAccessor) this).justenoughendupdate$setImageWidth(
                justenoughendupdate$isInventoryWithBackpack() ? 244 : 176);
    }

    @WrapOperation(method = "init", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookComponent;init(IILnet/minecraft/client/Minecraft;Z)V"))
    private void justenoughendupdate$shiftBook(RecipeBookComponent book, int width, int height, Minecraft minecraft, boolean widthTooNarrow, Operation<Void> original) {
        original.call(book, justenoughendupdate$isInventoryWithBackpack() ? width - 68 : width, height, minecraft, widthTooNarrow);
    }

    @WrapOperation(method = "*", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookComponent;updateScreenPosition(II)I"))
    private int justenoughendupdate$positionWithBook(RecipeBookComponent book, int width, int imageWidth, Operation<Integer> original) {
        if (justenoughendupdate$isInventoryWithBackpack() && book.isVisible()) {
            return original.call(book, width - 68, 176);
        }
        return original.call(book, width, imageWidth);
    }
}