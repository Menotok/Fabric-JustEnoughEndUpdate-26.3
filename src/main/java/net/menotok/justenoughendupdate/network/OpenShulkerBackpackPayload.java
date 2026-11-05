package net.menotok.justenoughendupdate.network;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record OpenShulkerBackpackPayload() implements CustomPacketPayload {
    public static final Type<OpenShulkerBackpackPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(JustEnoughEndUpdate.MOD_ID, "open_backpack"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenShulkerBackpackPayload> CODEC =
            StreamCodec.unit(new OpenShulkerBackpackPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}