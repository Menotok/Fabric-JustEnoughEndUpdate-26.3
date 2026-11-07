package net.menotok.justenoughendupdate.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.menotok.justenoughendupdate.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;

public class ShulkerBackpackLayer extends RenderLayer<AvatarRenderState, PlayerModel> {
    private final ItemStackRenderState itemState = new ItemStackRenderState();

    public ShulkerBackpackLayer(RenderLayerParent<AvatarRenderState, PlayerModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int light, AvatarRenderState state, float yRot, float xRot) {
        if (!state.chestEquipment.is(ModItems.SHULKERBACKPACK)) {
            return;
        }

        Minecraft.getInstance().getItemModelResolver()
                .updateForTopItem(itemState, state.chestEquipment, ItemDisplayContext.FIXED, null, null, 0);

        poseStack.pushPose();
        PlayerModel model = this.getParentModel();
        model.root().translateAndRotate(poseStack);
        model.body.translateAndRotate(poseStack);

        poseStack.translate(0.0F, 0.3125F, 0.0625F);
        poseStack.rotateDegrees(Axis.YP, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        itemState.submit(poseStack, collector, light, OverlayTexture.NO_OVERLAY, state.outlineColor);
        poseStack.popPose();
    }
}