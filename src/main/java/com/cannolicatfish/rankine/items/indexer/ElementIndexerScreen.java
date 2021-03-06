package com.cannolicatfish.rankine.items.indexer;

import com.cannolicatfish.rankine.ProjectRankine;
import com.cannolicatfish.rankine.util.PeriodicTableUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Locale;

public class ElementIndexerScreen extends ContainerScreen<ElementIndexerContainer> {
    private int currentScroll = 100;
    private PeriodicTableUtils.Element element = null;
    private static final PeriodicTableUtils utils = new PeriodicTableUtils();
    private ResourceLocation GUI = new ResourceLocation(ProjectRankine.MODID, "textures/gui/element_indexer.png");
    public ElementIndexerScreen(ElementIndexerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 176;
        this.ySize = 236;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }


    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {

        drawString(matrixStack,Minecraft.getInstance().fontRenderer,this.currentScroll + "%",140,10,0xffffff);
        DecimalFormat df = Util.make(new DecimalFormat("##.#"), (p_234699_0_) -> {
            p_234699_0_.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT));
        });
        if (element != this.container.getSlotItem())
        {
            element = this.container.getSlotItem();
        }
        if (element != null)
        {
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,element.toString(),32,10,0xffffff);
            int durability = utils.calcDurability(Collections.singletonList(element),Collections.singletonList(this.currentScroll));
            int harvest = utils.calcMiningLevel(Collections.singletonList(element),Collections.singletonList(this.currentScroll));
            float mspeed = utils.calcMiningSpeed(Collections.singletonList(element),Collections.singletonList(this.currentScroll));
            float damage = utils.calcDamage(Collections.singletonList(element),Collections.singletonList(this.currentScroll));
            float attspeed = utils.calcAttackSpeed(Collections.singletonList(element),Collections.singletonList(this.currentScroll));
            int enchant = utils.calcEnchantability(Collections.singletonList(element),Collections.singletonList(this.currentScroll));
            float corr = utils.calcCorrResist(Collections.singletonList(element),Collections.singletonList(this.currentScroll));
            float heat = utils.calcHeatResist(Collections.singletonList(element),Collections.singletonList(this.currentScroll));
            float tough = utils.calcToughness(Collections.singletonList(element),Collections.singletonList(this.currentScroll));
            float elec = element.element.getElectrodePotentialFromPercent(this.currentScroll);
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Durability: " + durability,12,30,(durability > 0 ? 0x55FF55 : durability < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Harvest Level: "+ harvest,12,42,(harvest > 0 ? 0x55FF55 : harvest < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Mining Speed: "+ df.format(mspeed),12,54,(mspeed > 0 ? 0x55FF55 : mspeed < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Damage: "+ df.format(damage),12,66,(damage > 0 ? 0x55FF55 : damage < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Attack Speed: "+ df.format(attspeed),12,78,(attspeed > 0 ? 0x55FF55 : attspeed < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Enchantability: "+ enchant,12,90,(enchant > 0 ? 0x55FF55 : enchant < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Corrosion Resistance: "+ df.format(corr * 100) + "%",12,102,(corr > 0 ? 0x55FF55 : corr < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Heat Resistance: "+ df.format(heat * 100) + "%",12,114,(heat > 0 ? 0x55FF55 : heat < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Toughness: "+ df.format(tough * 100) + "%",12,126,(tough > 0 ? 0x55FF55 : tough < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"E: "+ elec +"V",110,126,0x55FFFF);
        } else
        {
            //drawCenteredString(matrixStack,Minecraft.getInstance().fontRenderer,"MATERIALNAME",88,12,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Durability:",12,30,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Harvest Level:",12,42,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Mining Speed:",12,54,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Damage:",12,66,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Attack Speed:",12,78,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Enchantability:",12,90,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Corrosion Resistance:",12,102,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Heat Resistance:",12,114,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"Toughness:",12,126,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().fontRenderer,"E:",110,126,0x55FFFF);
        }

    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(this.GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(matrixStack,relX, relY, 0, 0, this.xSize, this.ySize);
        this.blit(matrixStack, this.guiLeft + 48, this.guiTop + 22, 0, 254, this.currentScroll + 1, 2);
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        this.currentScroll = this.currentScroll + (int) (delta);
        this.currentScroll = MathHelper.clamp(this.currentScroll, 1, 100);
        return true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
