package net.menotok.justenoughendupdate;

import net.fabricmc.api.ModInitializer;

import net.menotok.justenoughendupdate.block.ModBlocks;
import net.menotok.justenoughendupdate.creativemodetab.ModCreativeModeTabs;
import net.menotok.justenoughendupdate.item.ModItems;
import net.menotok.justenoughendupdate.worldgen.EndLayerBiomes;
import net.menotok.justenoughendupdate.worldgen.ModDensityFunctionTypes;
import net.menotok.justenoughendupdate.worldgen.ModFeatureTypes;
import net.menotok.justenoughendupdate.worldgen.gen.ModWorldGeneration;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JustEnoughEndUpdate implements ModInitializer {
	public static final String MOD_ID = "justenoughendupdate";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();

		ModItems.registerModItems();
		ModBlocks.registerModBlock();
		EndLayerBiomes.register();

		ModWorldGeneration.generateModWorldGen();
		ModFeatureTypes.register();
		ModDensityFunctionTypes.register();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
