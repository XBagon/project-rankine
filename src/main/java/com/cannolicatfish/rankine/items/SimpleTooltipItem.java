package com.cannolicatfish.rankine.items;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class SimpleTooltipItem extends Item {
    List<String> tip;
    public SimpleTooltipItem(String tooltip, Properties properties) {
        super(properties);
        this.tip = Collections.singletonList(tooltip);
    }
    public SimpleTooltipItem(List<String> tooltips, Properties properties) {
        super(properties);
        this.tip = tooltips;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
     //   if (Screen.hasShiftDown()) {
            for (String s : tip) {
                tooltip.add(new StringTextComponent(s).mergeStyle(TextFormatting.GRAY));
            }
    //    } else {
   //         tooltip.add(new StringTextComponent("Hold shift for information...").mergeStyle(TextFormatting.GRAY));
 //       }

    }
}
