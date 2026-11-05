package net.menotok.justenoughendupdate;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.menotok.justenoughendupdate.block.ModBlocks;
import net.menotok.justenoughendupdate.creativemodetab.ModCreativeModeTabs;
import net.menotok.justenoughendupdate.food.ModFoods;
import net.menotok.justenoughendupdate.item.ModItems;
import net.menotok.justenoughendupdate.item.ShulkerBackpackContainer;
import net.menotok.justenoughendupdate.menu.ShulkerBackpackMenu;
import net.menotok.justenoughendupdate.network.OpenShulkerBackpackPayload;
import net.menotok.justenoughendupdate.worldgen.EndLayerBiomes;
import net.menotok.justenoughendupdate.worldgen.ModDensityFunctionTypes;
import net.menotok.justenoughendupdate.worldgen.ModFeatureTypes;
import net.menotok.justenoughendupdate.worldgen.gen.ModWorldGeneration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JustEnoughEndUpdate implements ModInitializer {
	public static final String MOD_ID = "justenoughendupdate";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();

		ModItems.registerModItems();
		ModFoods.registerModFoods();

		ModBlocks.registerModBlock();
		EndLayerBiomes.register();

		ModWorldGeneration.generateModWorldGen();
		ModFeatureTypes.register();
		ModDensityFunctionTypes.register();

		PayloadTypeRegistry.serverboundPlay().register(OpenShulkerBackpackPayload.TYPE, OpenShulkerBackpackPayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(OpenShulkerBackpackPayload.TYPE, (payload, context) -> {
			ServerPlayer player = context.player();
			ItemStack backpack = player.getItemBySlot(EquipmentSlot.CHEST);
			if (!backpack.is(ModItems.SHULKERBACKPACK)) {
				return;
			}
			player.openMenu(new SimpleMenuProvider(
					(id, inventory, p) -> new ShulkerBackpackMenu(id, inventory, new ShulkerBackpackContainer(backpack)),
					Component.translatable("container.justenoughendupdate.backpack")));
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}