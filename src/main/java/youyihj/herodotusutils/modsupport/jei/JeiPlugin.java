package youyihj.herodotusutils.modsupport.jei;

import mezz.jei.Internal;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import youyihj.herodotusutils.HerodotusUtils;
import youyihj.herodotusutils.block.alchemy.BlockAlchemyCrafter;
import youyihj.herodotusutils.modsupport.jei.helper.ImpetusHelper;
import youyihj.herodotusutils.modsupport.jei.recipes.AlchemyCraftRecipesCategory;
import youyihj.herodotusutils.modsupport.jei.recipes.AlchemyCraftRecipesWrapper;
import youyihj.herodotusutils.modsupport.jei.render.ImpetusRender;
import youyihj.herodotusutils.modsupport.modularmachinery.crafting.ingredient.Impetus;
import youyihj.herodotusutils.recipe.AlchemyRecipes;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
@JEIPlugin
public class JeiPlugin implements IModPlugin {
    public static IJeiHelpers JEI_HELPER;

    @Override
    public void register(IModRegistry registry) {
        JEI_HELPER = registry.getJeiHelpers();

        if (!AlchemyRecipes.craftRecipes.isEmpty()) {
            registry.addRecipeCatalyst(new ItemStack(BlockAlchemyCrafter.INSTANCE), "AlchemyCraftJEI");

            List<AlchemyCraftRecipesWrapper> recipesWrappers =
                    AlchemyRecipes.craftRecipes.stream().map(AlchemyCraftRecipesWrapper::new).collect(Collectors.toList());
            registry.addRecipes(recipesWrappers, "AlchemyCraftJEI");
        }
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
        HerodotusUtils.logger.info("JEI Loading");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        if (!AlchemyRecipes.craftRecipes.isEmpty()) {
            registry.addRecipeCategories(new AlchemyCraftRecipesCategory(Internal.getHelpers().getGuiHelper()));
        }
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {
        registry.register(() -> Impetus.class, Collections.emptyList(), new ImpetusHelper(), new ImpetusRender());
    }
}
