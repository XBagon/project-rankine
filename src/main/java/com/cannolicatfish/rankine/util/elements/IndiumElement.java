package com.cannolicatfish.rankine.util.elements;

import com.cannolicatfish.rankine.util.PeriodicTableUtils;
import net.minecraft.enchantment.Enchantment;

public class IndiumElement implements ElementInterface{
    @Override
    public PeriodicTableUtils.Element getReference() {
        return PeriodicTableUtils.Element.INDIUM;
    }

    @Override
    public int getDurabilityFromPercent(int x) {
        return Math.round(x/10f);
    }

    @Override
    public float getDamageFromPercent(int x) {
        return 0;
    }

    @Override
    public float getAttackSpeedFromPercent(int x) {
        return x/125f;
    }

    @Override
    public float getMiningSpeedFromPercent(int x) {
        return x/8f;
    }

    @Override
    public int getMiningLevelFromPercent(int x) {
        return 0;
    }

    @Override
    public int getEnchantabilityFromPercent(int x) {
        return Math.round(x/3.3f);
    }

    @Override
    public float getCorrResistFromPercent(int x) {
        return 0;
    }

    @Override
    public float getHeatResistFromPercent(int x) {
        return 0;
    }

    @Override
    public float getToughnessFromPercent(int x) {
        return -x/2000f;
    }

    @Override
    public float getElectrodePotentialFromPercent(int x) {
        return -0.34f;
    }

    @Override
    public Enchantment getEnchantments(int x) {
        return null;
    }
}
