package net.menotok.justenoughendupdate.mixin;

import net.menotok.justenoughendupdate.item.BackpackStorage;
import net.minecraft.util.Prediction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Inventory.class)
public abstract class InventoryMixin {
    @Shadow
    @Final
    public Player player;

    @Shadow public abstract int getFreeSlot();
    @Shadow public abstract int getSlotWithRemainingSpace(ItemStack stack);

    @Unique
    private boolean justenoughendupdate$movedToBackpack;

    @Inject(method = "add(ILnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void justenoughendupdate$matchingStacksFirst(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        justenoughendupdate$movedToBackpack = BackpackStorage.insert(this.player, stack, true);
        if (stack.isEmpty() && justenoughendupdate$movedToBackpack) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "add(ILnet/minecraft/world/item/ItemStack;)Z", at = @At("RETURN"), cancellable = true)
    private void justenoughendupdate$overflowIntoBackpack(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (!stack.isEmpty() && BackpackStorage.insert(this.player, stack, false)) {
            justenoughendupdate$movedToBackpack = true;
        }
        if (justenoughendupdate$movedToBackpack) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "placeItemBackInInventory(Lnet/minecraft/world/item/ItemStack;ZLnet/minecraft/util/Prediction;)V", at = @At("HEAD"))
    private void justenoughendupdate$placeBackIntoBackpack(ItemStack stack, boolean sendPacket, Prediction prediction, CallbackInfo ci) {
        BackpackStorage.insert(this.player, stack, true);
        if (!stack.isEmpty() && this.getSlotWithRemainingSpace(stack) == -1 && this.getFreeSlot() == -1) {
            BackpackStorage.insert(this.player, stack, false);
        }
    }
}
