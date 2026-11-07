package net.menotok.justenoughendupdate.mixin;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.menotok.justenoughendupdate.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public class InventoryBackgroundMixin {
    private static final Identifier PANEL =
            Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "textures/gui/backpack_panel.png");

    @Inject(method = "extractBackground", at = @At("HEAD"))
    private void justenoughendupdate$normalWidthForBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        ((AbstractContainerScreenAccessor) this).justenoughendupdate$setImageWidth(176);
    }

    @Inject(method = "extractBackground", at = @At("TAIL"))
    private void justenoughendupdate$drawPanel(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        boolean worn = client.player != null && client.player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.SHULKERBACKPACK);
        AbstractContainerScreenAccessor screen = (AbstractContainerScreenAccessor) this;
        screen.justenoughendupdate$setImageWidth(worn ? 244 : 176);
        if (!worn) {
            return;
        }
        graphics.blit(RenderPipelines.GUI_TEXTURED, PANEL,
                screen.justenoughendupdate$getLeftPos() + 178, screen.justenoughendupdate$getTopPos() -5,
                0.0F, 0.0F, 68, 176, 256, 256);
    }
}
