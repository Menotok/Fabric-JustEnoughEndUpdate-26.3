package net.menotok.justenoughendupdate.mixin;

import net.menotok.justenoughendupdate.item.BackpackStorage;
import net.menotok.justenoughendupdate.item.ModItems;
import net.menotok.justenoughendupdate.item.WornBackpackContainer;
import net.menotok.justenoughendupdate.menu.WornBackpackSlot;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InventoryMenu.class)
public abstract class InventoryMenuMixin extends AbstractContainerMenu {
    protected InventoryMenuMixin(MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void justenoughendupdate$addBackpackSlots(Inventory inventory, boolean active, Player owner, CallbackInfo ci) {
        WornBackpackContainer backpack = new WornBackpackContainer(owner);
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 3; column++) {
                this.addSlot(new WornBackpackSlot(backpack, row * 3 + column, 178 + 8 + column * 18, 3 + row * 18));
            }
        }
    }

    @Inject(method = "quickMoveStack", at = @At("HEAD"), cancellable = true)
    private void justenoughendupdate$shiftClickFromBackpack(Player player, int index, CallbackInfoReturnable<ItemStack> cir) {
        if (index < 46) {
            return;
        }
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) {
            cir.setReturnValue(ItemStack.EMPTY);
            return;
        }
        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();

        boolean moved = this.moveItemStackTo(stack, 36, 45, false);
        moved |= this.moveItemStackTo(stack, 9, 36, false);

        if (!moved) {
            cir.setReturnValue(ItemStack.EMPTY);
            return;
        }
        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        cir.setReturnValue(original);
    }

    @Inject(method = "quickMoveStack", at = @At("RETURN"), cancellable = true)
    private void justenoughendupdate$shiftClickIntoBackpack(Player player, int index, CallbackInfoReturnable<ItemStack> cir) {
        if (index < 9 || index >= 45) {
            return;
        }
        if (!player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.SHULKERBACKPACK)) {
            return;
        }
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) {
            return;
        }
        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();

        if (this.moveItemStackTo(stack, 46, 73, false)) {
            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (cir.getReturnValue().isEmpty()) {
                cir.setReturnValue(original);
            }
        }
    }

    @Inject(method = "quickMoveStack", at = @At("HEAD"), cancellable = true)
    private void justenoughendupdate$shiftClickMatchingIntoBackpack(Player player, int index, CallbackInfoReturnable<ItemStack> cir) {
        if (index < 9 || index >= 45) {
            return;
        }
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) {
            return;
        }
        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();

        if (BackpackStorage.insert(player, stack, true)) {
            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
                cir.setReturnValue(original);
            } else {
                slot.setChanged();
            }
        }
    }
}
