package net.menotok.justenoughendupdate;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityRenderLayerRegistrationCallback;
import net.menotok.justenoughendupdate.client.ShulkerBackpackLayer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;

public class JustEnoughEndUpdateClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LivingEntityRenderLayerRegistrationCallback.EVENT.register((entityType, entityRenderer, helper, context) -> {
            if (entityRenderer instanceof AvatarRenderer<?> avatarRenderer) {
                JustEnoughEndUpdate.LOGGER.info("Backpack layer registered");
                helper.register(new ShulkerBackpackLayer(avatarRenderer));
            }
        });
    }
}