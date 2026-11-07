package net.menotok.justenoughendupdate.mixin;

import net.menotok.justenoughendupdate.item.ModItems;
import net.menotok.justenoughendupdate.item.WornBackpackContainer;
import net.minecraft.network.protocol.game.ClientboundSetHeldSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin {
    @Shadow
    public ServerPlayer player;

    @Inject(method = "tryPickItem", at = @At("HEAD"), cancellable = true)
    private void justenoughendupdate$pickFromBackpack(ItemStack itemStack, CallbackInfo ci) {
        Inventory inventory = this.player.getInventory();
        if (inventory.findSlotMatchingItem(itemStack) != -1) {
            return;
        }
        if (!(this.player.inventoryMenu.getSlot(46).container instanceof WornBackpackContainer backpack) || !backpack.isWorn()) {
            return;
        }

        for (int i = 0; i < 27; i++) {
            ItemStack inBackpack = backpack.getItem(i);
            if (!inBackpack.isEmpty() && ItemStack.isSameItemSameComponents(itemStack, inBackpack)) {
                int hotbarSlot = inventory.getSuitableHotbarSlot();
                ItemStack replaced = inventory.getItem(hotbarSlot);
                if (replaced.is(ModItems.SHULKERBACKPACK)) {
                    return;
                }

                inventory.setSelectedSlot(hotbarSlot);
                inventory.setItem(hotbarSlot, inBackpack);
                backpack.setItem(i, replaced);

                this.player.connection.send(new ClientboundSetHeldSlotPacket(hotbarSlot));
                this.player.inventoryMenu.broadcastChanges();
                ci.cancel();
                return;
            }
        }
    }
}
