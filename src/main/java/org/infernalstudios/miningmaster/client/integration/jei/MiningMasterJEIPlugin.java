package org.infernalstudios.miningmaster.client.integration.jei;

import com.google.common.collect.ImmutableList;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IAdvancedRegistration;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.infernalstudios.miningmaster.MiningMaster;
import org.infernalstudios.miningmaster.init.MMBlocks;
import org.infernalstudios.miningmaster.init.MMRecipes;

import java.util.Collection;
import java.util.List;

@JeiPlugin
public class MiningMasterJEIPlugin implements IModPlugin {
    private static final ResourceLocation UID = new ResourceLocation(MiningMaster.MOD_ID, "jei_main");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new GemForgeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @SuppressWarnings("resource")
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        Collection<Recipe<?>> recipes = Minecraft.getInstance().level.getRecipeManager().getRecipes();
        List<Recipe<?>> gemForgingRecipes = recipes.stream()
            .filter(recipe -> recipe.getType() == MMRecipes.FORGING_RECIPE_TYPE)
            .collect(ImmutableList.toImmutableList());
        registration.addRecipes(gemForgingRecipes, GemForgeRecipeCategory.UID);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {

    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(MMBlocks.GEM_FORGE.get().asItem()), GemForgeRecipeCategory.UID);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

    }

    @Override
    public void registerAdvanced(IAdvancedRegistration registration) {

    }
}
