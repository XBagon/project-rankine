package com.cannolicatfish.rankine.client.integration.jei.categories;

import com.cannolicatfish.rankine.ProjectRankine;
import com.cannolicatfish.rankine.init.RankineBlocks;
import com.cannolicatfish.rankine.recipe.BeehiveOvenRecipe;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BeehiveOvenRecipeCategory implements IRecipeCategory<BeehiveOvenRecipe> {

    public static ResourceLocation UID = new ResourceLocation(ProjectRankine.MODID, "beeoven");
    private final IDrawable background;
    private final String localizedName;
    private final IDrawable overlay;
    private final IDrawable icon;

    public BeehiveOvenRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createBlankDrawable(145, 95);
        localizedName = I18n.format("rankine.jei.beeoven");
        overlay = guiHelper.createDrawable(new ResourceLocation(ProjectRankine.MODID, "textures/gui/beeoven_jei.png"),
                0, 15, 140, 90);
        icon = guiHelper.createDrawableIngredient(new ItemStack(RankineBlocks.BEEHIVE_OVEN_PIT.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends BeehiveOvenRecipe> getRecipeClass() {
        return BeehiveOvenRecipe.class;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void draw(BeehiveOvenRecipe recipe, MatrixStack ms, double mouseX, double mouseY) {
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        overlay.draw(ms, 0, 4);
        RenderSystem.disableBlend();
        RenderSystem.disableAlphaTest();
    }

    @Override
    public void setIngredients(BeehiveOvenRecipe recipe, IIngredients iIngredients) {
        ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
        for (Ingredient i : recipe.getIngredients()) {
            builder.add(Arrays.asList(i.getMatchingStacks()));
        }
        iIngredients.setInputLists(VanillaTypes.ITEM, builder.build());
        iIngredients.setOutputs(VanillaTypes.ITEM, Collections.singletonList(recipe.getRecipeOutput()));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, BeehiveOvenRecipe recipe, IIngredients ingredients) {
        int index = 0, posX = 31;
        for (List<ItemStack> o : ingredients.getInputs(VanillaTypes.ITEM)) {
            recipeLayout.getItemStacks().init(index, true, posX, 21);
            recipeLayout.getItemStacks().set(index, o);
            index++;
            posX += 18;
        }

        for (int i = 0; i < ingredients.getOutputs(VanillaTypes.ITEM).size(); i++) {
            List<ItemStack> stacks = ingredients.getOutputs(VanillaTypes.ITEM).get(i);
            recipeLayout.getItemStacks().init(index + i, false, 106, 34);
            recipeLayout.getItemStacks().set(index + i, stacks);
        }

        int endIndex = index;
        ResourceLocation recipeId = recipe.getId();
        recipeLayout.getItemStacks().addTooltipCallback((slotIndex, input, ingredient, tooltip) -> {
            if (slotIndex >= endIndex) {
                if (Minecraft.getInstance().gameSettings.advancedItemTooltips || Screen.hasShiftDown()) {
                    tooltip.add(new TranslationTextComponent("jei.tooltip.recipe.id", recipeId).mergeStyle(TextFormatting.DARK_GRAY));
                }
            }
        });
    }
}

