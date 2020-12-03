package com.cannolicatfish.rankine.world.gen;

import com.cannolicatfish.rankine.Config;
import com.cannolicatfish.rankine.init.ModBlocks;
import com.cannolicatfish.rankine.init.ModFeatures;
import com.cannolicatfish.rankine.world.gen.feature.*;
import com.cannolicatfish.rankine.world.gen.placement.IntrusionPlacement;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber
public class OreGen {

    private static List<ResourceLocation> getBiomeNamesFromCategory(List<Biome.Category> biomeCats, boolean include) {
        List<ResourceLocation> b = new ArrayList<>();
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (!biomeCats.isEmpty()) {
                for (Biome.Category cat : biomeCats) {
                    if (biome.getCategory() == cat && include){
                        b.add(biome.getRegistryName());
                    }
                    if (!include && biome.getCategory() != cat && biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
                        b.add(biome.getRegistryName());
                    }
                }
            }
            else if (!include && biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
                b.add(biome.getRegistryName());
            }
        }
        return b;
    }
/*
    private static void addCrystal() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (biome.getCategory() == Biome.Category.DESERT || biome.getCategory() == Biome.Category.MESA || biome.getCategory() == Biome.Category.SAVANNA) {
                biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, new CrystalFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(6))));
            }
        }
    }
    }*/

    private static List<AbstractMap.SimpleEntry<ConfiguredFeature<?,?>,List<ResourceLocation>>> flatBedrock() {
        if (Config.FLAT_BEDROCK.get())
        {
            return Arrays.asList(
                    new AbstractMap.SimpleEntry<>(ModFeatures.FLAT_BEDROCK,
                            getBiomeNamesFromCategory(Collections.emptyList(),false)),
                    new AbstractMap.SimpleEntry<>(ModFeatures.FLAT_BEDROCK_NETHER,
                            getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)));
        } else
        {
            return Collections.emptyList();
        }
    }

    private static List<AbstractMap.SimpleEntry<ConfiguredFeature<?,?>,List<ResourceLocation>>> getUndergroundOreFeatures() {
        return Arrays.asList(
                new AbstractMap.SimpleEntry<>(ModFeatures.GRAVEL_DISKS,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.OCEAN),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.SAND_DISKS,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.OCEAN),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.CLAY_DISKS,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.OCEAN),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_ALLUVIUM,
                        getBiomeNamesFromCategory(Arrays.asList(Biome.Category.OCEAN, Biome.Category.RIVER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_INTRUSION,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.DEFAULT_STONE_GEN,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ANDESITE_VAR,
                        getBiomeNamesFromCategory(Arrays.asList(Biome.Category.OCEAN, Biome.Category.MUSHROOM, Biome.Category.DESERT, Biome.Category.MESA), false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ANDESITIC_TUFF,
                        getBiomeNamesFromCategory(Arrays.asList(Biome.Category.OCEAN, Biome.Category.MUSHROOM, Biome.Category.DESERT, Biome.Category.MESA), false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NODULE,
                        getBiomeNamesFromCategory(Arrays.asList(Biome.Category.RIVER, Biome.Category.SWAMP), false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_IRONSTONE,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.DESERT), true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_IRONSTONE_RED,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.MESA), true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_OPAL,
                        getBiomeNamesFromCategory(Arrays.asList(Biome.Category.DESERT, Biome.Category.MESA), true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_PHOSPHORITE,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.DESERT), true)),


                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_COPPER,
                        getBiomeNamesFromCategory(Collections.emptyList(), false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_TIN,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_LEAD,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_SILVER,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_ALUMINUM,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_GOLD,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_BISMUTH,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_STIBNITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_HALITE,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.EXTREME_HILLS),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_PINK_HALITE,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.EXTREME_HILLS),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_LIGNITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_SUBBITUMINOUS_COAL,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_BITUMINOUS_COAL,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_MALACHITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_CASSITERITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_BAUXITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_SPHALERITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_MAGNETITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_MAGNESITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_PENTLANDITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_GALENA,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_ACANTHITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_PYROLUSITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_CINNABAR,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_PETALITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_CHROMITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_CELESTINE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_VANADINITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_LAZURITE,
                        getBiomeNamesFromCategory(Arrays.asList(Biome.Category.OCEAN, Biome.Category.MUSHROOM, Biome.Category.DESERT, Biome.Category.MESA), true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_EMERALD,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_AQUAMARINE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_MAJORITE,
                        getBiomeNamesFromCategory(Collections.emptyList(),false)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_PLUMBAGO,
                        getBiomeNamesFromCategory(Collections.emptyList(),false))
                );

    }

    private static List<AbstractMap.SimpleEntry<ConfiguredFeature<?,?>,List<ResourceLocation>>> getNetherOreFeatures() {
        return Arrays.asList(
                new AbstractMap.SimpleEntry<>(ModFeatures.NETHER_ORE_INTRUSION,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_ARSENIC_NETHER,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_SULFUR_NETHER,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_ANTHRACITE_NETHER,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_BISMUTHINITE_NETHER,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_COLUMBITE_NETHER,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_TANTALITE_NETHER,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_GREENOCKITE_NETHER,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_ILMENITE_NETHER,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_WOLFRAMITE_NETHER,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_MOISSANITE_NETHER,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_SPERRYLITE_NETHER,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_COBALTITE_NETHER,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.NETHER),true))
        );
    }

    private static List<AbstractMap.SimpleEntry<ConfiguredFeature<?,?>,List<ResourceLocation>>> getEndOreFeatures() {
        return Arrays.asList(
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_GALLIUM_END,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.THEEND),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_INDIUM_END,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.THEEND),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_TELLURIUM_END,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.THEEND),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_NATIVE_SELENIUM_END,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.THEEND),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_FLUORITE_END,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.THEEND),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_URANINITE_END,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.THEEND),true)),
                new AbstractMap.SimpleEntry<>(ModFeatures.ORE_XENOTIME_END,
                        getBiomeNamesFromCategory(Collections.singletonList(Biome.Category.THEEND),true))
        );
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void removeFeatures(final BiomeLoadingEvent event) {
        if (event.getName() != null) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).removeIf(featureSupplier -> featureSupplier.toString().contains("net.minecraft.util.registry.WorldSettingsImport"));
            //System.out.println(event.getName() + ": " + (event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void addOreGenFeatures(final BiomeLoadingEvent event)
    {
        if (event.getName() != null) {
            // TO-DO: Re-add vanilla features to proper biomes?
            if (event.getCategory() != Biome.Category.NETHER && event.getCategory() != Biome.Category.THEEND)
            {
                List<ConfiguredFeature<?,?>> vanillaFeatures = Arrays.asList(Features.ORE_DIRT,Features.ORE_GRAVEL,Features.DISK_CLAY,Features.DISK_GRAVEL,Features.DISK_SAND);

                List<AbstractMap.SimpleEntry<ConfiguredFeature<?,?>,List<ResourceLocation>>> bedrock = flatBedrock();
                for (AbstractMap.SimpleEntry<ConfiguredFeature<?,?>,List<ResourceLocation>> entry : bedrock)
                {
                    if (entry.getValue().contains(event.getName()))
                    {
                        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES.ordinal(),entry::getKey);
                    }
                }

                List<AbstractMap.SimpleEntry<ConfiguredFeature<?,?>,List<ResourceLocation>>> ores = getUndergroundOreFeatures();
                for (AbstractMap.SimpleEntry<ConfiguredFeature<?,?>,List<ResourceLocation>> entry : ores)
                {
                    if (entry.getValue().contains(event.getName()))
                    {
                        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES.ordinal(),entry::getKey);
                    }
                }
            }
            else if (event.getCategory() == Biome.Category.NETHER)
            {
                List<AbstractMap.SimpleEntry<ConfiguredFeature<?,?>,List<ResourceLocation>>> netherOreFeatures= getNetherOreFeatures();
                for (AbstractMap.SimpleEntry<ConfiguredFeature<?,?>,List<ResourceLocation>> entry : netherOreFeatures)
                {
                    if (entry.getValue().contains(event.getName()))
                    {
                        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES.ordinal(),entry::getKey);
                    }
                }
            } else {
                List<AbstractMap.SimpleEntry<ConfiguredFeature<?,?>,List<ResourceLocation>>> endOreFeatures = getEndOreFeatures();
                for (AbstractMap.SimpleEntry<ConfiguredFeature<?,?>,List<ResourceLocation>> entry : endOreFeatures)
                {
                    if (entry.getValue().contains(event.getName()))
                    {
                        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES.ordinal(),entry::getKey);
                    }
                }
            }




        }
    }
}

