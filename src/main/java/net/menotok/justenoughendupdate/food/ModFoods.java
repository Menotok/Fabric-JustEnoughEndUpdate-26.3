package net.menotok.justenoughendupdate.food;

import net.menotok.justenoughendupdate.JustEnoughEndUpdate;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties BAKED_CHORUS_FRUIT = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).build();

    public static void registerModFoods() {
        JustEnoughEndUpdate.LOGGER.info("Registering Mod Food Items for" + JustEnoughEndUpdate.MOD_ID);
    }
}
