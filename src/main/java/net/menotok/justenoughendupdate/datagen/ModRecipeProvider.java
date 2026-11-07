package net.menotok.justenoughendupdate.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.menotok.justenoughendupdate.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                //simpleCookingRecipe("smelting", SmeltingRecipe::new, 100, Items.CHORUS_FRUIT, ModItems.BAKED_CHORUS_FRUIT, 0.10F);
                simpleCookingRecipe("smoking", SmokingRecipe::new, 100, Items.CHORUS_FRUIT, ModItems.BAKED_CHORUS_FRUIT, 0.10F);
                simpleCookingRecipe("campfire_cooking", CampfireCookingRecipe::new, 300, Items.CHORUS_FRUIT, ModItems.BAKED_CHORUS_FRUIT, 0.10F);

                shaped(RecipeCategory.TOOLS, ModItems.SHULKERBACKPACK)
                        .pattern("N N")
                        .pattern("SXS")
                        .pattern("S S")
                        .define('N', Items.IRON_NUGGET)
                        .define('S', Items.STRING)
                        .define('X', ItemTags.SHULKER_BOXES)
                        .unlockedBy(getHasName(Items.SHULKER_BOX), has(Items.SHULKER_BOX))
                        .save(output);
            }
        };
    }
}
