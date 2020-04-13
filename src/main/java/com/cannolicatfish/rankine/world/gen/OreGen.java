package com.cannolicatfish.rankine.world.gen;

import com.cannolicatfish.rankine.blocks.ModBlocks;
import com.cannolicatfish.rankine.blocks.RankineOre;
import com.cannolicatfish.rankine.world.biome.ModBiomes;
import com.cannolicatfish.rankine.world.feature.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OreGen {

    public static final Feature<RankineOreFeatureConfig> RANKINE_ORE = new RankineOreFeature(RankineOreFeatureConfig::deserialize);

    public static void setupOreGeneration() {

        removeFeatures();
        addMeteoricIron();
        addCrystal();


        intrusionGenDef(ModBlocks.KIMBERLITE, Collections.emptyList(),false,0, 25, .05f);

        replaceGenDef(Blocks.RED_SANDSTONE, Collections.singletonList(Biome.Category.MESA), true, 61, 80);
        replaceGenDef(ModBlocks.GNEISS, Arrays.asList(Biome.Category.MUSHROOM, Biome.Category.JUNGLE, Biome.Category.FOREST, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA), true, 0, 10);
        replaceGenDef(ModBlocks.MARBLE, Arrays.asList(Biome.Category.MUSHROOM, Biome.Category.JUNGLE, Biome.Category.FOREST, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA), false, 0, 10);
        replaceGenDef(ModBlocks.BASALT, Arrays.asList(Biome.Category.OCEAN, Biome.Category.BEACH, Biome.Category.MUSHROOM), true, 11, 30);
        replaceGenDef(ModBlocks.LIMESTONE, Arrays.asList(Biome.Category.OCEAN, Biome.Category.BEACH, Biome.Category.MUSHROOM), true, 31, 50);
        replaceGenDef(ModBlocks.SHALE, Arrays.asList(Biome.Category.OCEAN, Biome.Category.BEACH, Biome.Category.MUSHROOM, Biome.Category.RIVER, Biome.Category.SWAMP), true, 51, 70);
        replaceGenDef(ModBlocks.RHYOLITE, Arrays.asList(Biome.Category.OCEAN, Biome.Category.BEACH, Biome.Category.MUSHROOM), false, 11, 30);
        replaceGenDef(ModBlocks.GRANITE, Arrays.asList(Biome.Category.OCEAN, Biome.Category.BEACH, Biome.Category.MUSHROOM), false, 31, 50);
        replaceGenDef(ModBlocks.LIMESTONE, Arrays.asList(Biome.Category.OCEAN, Biome.Category.BEACH, Biome.Category.MUSHROOM, Biome.Category.RIVER, Biome.Category.SWAMP), false, 51,70);
        replaceGenDef(ModBlocks.ANDESITE, Collections.emptyList(), false, 71, 90);
        replaceGenDef(ModBlocks.DIORITE, Collections.emptyList(), false, 91, 128);




        chunkGenDef(ModBlocks.NATIVE_COPPER_ORE,15,1F,51,70, RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_LOW);
        chunkGenDef(ModBlocks.NATIVE_TIN_ORE,15,1F,51,70, RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_LOW);
        chunkGenDef(ModBlocks.NATIVE_COPPER_ORE,15,1F,71,128, RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_HIGH);
        chunkGenDef(ModBlocks.NATIVE_TIN_ORE,15,1F,71,128, RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_HIGH);
        chunkGenDef(ModBlocks.NATIVE_GOLD_ORE,15,.5F,11,128, RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_GOLD);
        chunkGenDef(ModBlocks.MAGNETITE_ORE,50,0.1F,0,70, RankineOreFeatureConfig.RankineFillerBlockType.OW_MARBLE_NOTOP);
        chunkGenDef(ModBlocks.MALACHITE_ORE,50,0.07F,31,128, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_BASIC_B);
        chunkGenDef(ModBlocks.CASSITERITE_ORE,50,0.07F,31,128, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_BASIC_B);
        chunkGenDef(ModBlocks.BAUXITE_ORE,50,0.07F,31,128, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_BASIC_B);
        chunkGenDef(ModBlocks.SPHALERITE_ORE,50,0.07F,31,128, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_NOSHALE);
        chunkGenDef(ModBlocks.MAGNESITE_ORE, 30, 0.07F, 11, 50, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_NOSHALE);
        chunkGenDef(ModBlocks.PENTLANDITE_ORE, 30, 0.02F, 11, 50, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_NOSHALE);
        chunkGenDef(ModBlocks.GALENA_ORE, 40, 0.02F, 11, 50, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_NOSHALE);
        chunkGenDef(ModBlocks.ACANTHITE_ORE, 30, 0.02F, 11, 50, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_NOSHALE);
        chunkGenDef(ModBlocks.PYROLUSITE_ORE, 30, 0.02F, 11, 50, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_NOSHALE);
        chunkGenDef(ModBlocks.CINNABAR_ORE, 30, 0.2F, 11, 50, RankineOreFeatureConfig.RankineFillerBlockType.OW_IGNEOUS);
        chunkGenDef(ModBlocks.LIGNITE_ORE, 30, 0.5F, 51, 128, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_BASIC);
        chunkGenDef(ModBlocks.SUBBITUMINOUS_ORE, 25, 0.5F, 31, 50, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_BASIC);
        chunkGenDef(ModBlocks.BITUMINOUS_ORE, 20, 0.5F, 11, 30, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_NOSHALE);
        chunkGenDef(ModBlocks.LAZURITE_ORE, 20, 0.1F, 31, 128, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_BASIC_B);
        chunkGenDef(ModBlocks.EMERALD_ORE, 15, 0.15F, 11, 30, RankineOreFeatureConfig.RankineFillerBlockType.OW_TOP_NOSHALE);
        chunkGenDef(ModBlocks.PLUMBAGO_ORE, 8, 0.10F, 0, 11, RankineOreFeatureConfig.RankineFillerBlockType.MARBLE);

        rockGenDef(ModBlocks.LIMESTONE_NODULE.getDefaultState(),Arrays.asList(Biome.Category.RIVER, Biome.Category.SWAMP),false, ModBlocks.LIMESTONE.getDefaultState(),6,10,31,70);
        rockGenDef(ModBlocks.DIAMOND_ORE.getDefaultState().with(RankineOre.TYPE,11),Collections.emptyList(),false, ModBlocks.KIMBERLITE.getDefaultState(),5,3,0,25);
        rockGenDef(ModBlocks.VANADINITE_ORE.getDefaultState().with(RankineOre.TYPE,4),Collections.emptyList(),false, ModBlocks.GALENA_ORE.getDefaultState().with(RankineOre.TYPE,4),8,1,31,50);
        rockGenDef(ModBlocks.BISMITE_ORE.getDefaultState().with(RankineOre.TYPE,1),Collections.emptyList(),false, ModBlocks.GALENA_ORE.getDefaultState().with(RankineOre.TYPE,1),8,1,11,50);
        rockGenDef(Blocks.CLAY.getDefaultState(),Collections.emptyList(),false, Blocks.DIRT.getDefaultState(),10,1,55,70);
    }



    private static void rockGenDef(BlockState block, List<Biome.Category> biomes, boolean genType, BlockState replace, int veinSize, int count, int minHeight, int maxHeight)
    {
        final Feature<OreFeatureConfig> MODULE = new ModularOreFeature(OreFeatureConfig::deserialize,replace);
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (genType && biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome != ModBiomes.MANTLE) {
                if (biomes.contains(biome.getCategory())) // if biome is supposed to be included (reverse)
                {
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, MODULE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, block, veinSize))
                            .withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(count, minHeight, 0, maxHeight))));
                }
            }
            if (!genType && biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome != ModBiomes.MANTLE)
            {
                if (!biomes.contains(biome.getCategory())) // if biomes in biomesExcluded are not supposed to be included
                {
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, MODULE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, block, veinSize))
                            .withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(count, minHeight, 0, maxHeight))));
                }
            }

        }
    }

    private static void replaceGenDef(Block block, List<Biome.Category> biomes, boolean genType, int lowerBound, int upperBound) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (genType && biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome != ModBiomes.MANTLE) {
                if (biomes.contains(biome.getCategory())) // if biome is supposed to be included (reverse)
                {
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, new ReplacerFeature(ReplacerFeatureConfig::deserialize).withConfiguration(
                            new ReplacerFeatureConfig(Blocks.STONE.getDefaultState(), block.getDefaultState(), lowerBound, upperBound)).withPlacement(new ReplacerPlacement(NoPlacementConfig::deserialize).configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
                }
            }
            if (!genType && biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome != ModBiomes.MANTLE)
            {
                if (!biomes.contains(biome.getCategory())) // if biomes in biomesExcluded are not supposed to be included
                {
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, new ReplacerFeature(ReplacerFeatureConfig::deserialize).withConfiguration(
                            new ReplacerFeatureConfig(Blocks.STONE.getDefaultState(), block.getDefaultState(), lowerBound, upperBound)).withPlacement(new ReplacerPlacement(NoPlacementConfig::deserialize).configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
                }
            }

        }

    }

    private static void intrusionGenDef(Block block, List<Biome.Category> biomes, boolean genType, int lowerBound, int upperBound, float chance) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (genType && biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome != ModBiomes.MANTLE) {
                if (biomes.contains(biome.getCategory())) // if biome is supposed to be included (reverse)
                {
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, new IntrusionReplacerFeature(ReplacerFeatureConfig::deserialize).withConfiguration(
                            new ReplacerFeatureConfig(Blocks.STONE.getDefaultState(), block.getDefaultState(), lowerBound, upperBound)).withPlacement(Placement.CHANCE_RANGE.configure(new ChanceRangeConfig(chance, lowerBound, 0, upperBound))));
                }
            }
            if (!genType && biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome != ModBiomes.MANTLE)
            {
                if (!biomes.contains(biome.getCategory())) // if biomes in biomesExcluded are not supposed to be included
                {
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, new IntrusionReplacerFeature(ReplacerFeatureConfig::deserialize).withConfiguration(
                            new ReplacerFeatureConfig(Blocks.STONE.getDefaultState(), block.getDefaultState(), lowerBound, upperBound)).withPlacement(Placement.CHANCE_RANGE.configure(new ChanceRangeConfig(chance, lowerBound, 0, upperBound))));
                }
            }

        }

    }

    private static void chunkGenDef(RankineOre block, int veinSize, float chance, int minHeight, int maxHeight, RankineOreFeatureConfig.RankineFillerBlockType type) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome != ModBiomes.MANTLE) {
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, RANKINE_ORE.withConfiguration(
                            new RankineOreFeatureConfig(type, block.getStateContainer().getBaseState(), veinSize)).withPlacement(Placement.CHANCE_RANGE.configure(new ChanceRangeConfig(chance, minHeight, 0, maxHeight))));
            }
        }

    }

    private static void addMeteoricIron()
    {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome != ModBiomes.MANTLE) {
                biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,Feature.FOREST_ROCK.withConfiguration(new BlockBlobConfig(ModBlocks.METEORIC_IRON_ORE.getDefaultState(),2))
                        .withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(100))));
            }
        }
    }

    private static void addCrystal()
    {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (biome.getCategory() == Biome.Category.DESERT || biome.getCategory() == Biome.Category.MESA || biome.getCategory() == Biome.Category.SAVANNA) {
                biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,new CrystalFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(4))));
            }
        }
    }

    private static void removeFeatures() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            List<ConfiguredFeature> features = new ArrayList<ConfiguredFeature>();
            for (ConfiguredFeature<?,?> f : biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)) {
                if (((DecoratedFeatureConfig) f.config).feature.feature instanceof OreFeature) {
                    if (((OreFeatureConfig) ((DecoratedFeatureConfig) f.config).feature.config).state.getBlock() == Blocks.GRANITE) {
                        features.add(f);
                    }
                    if (((OreFeatureConfig) ((DecoratedFeatureConfig) f.config).feature.config).state.getBlock() == Blocks.ANDESITE) {
                        features.add(f);
                    }
                    if (((OreFeatureConfig) ((DecoratedFeatureConfig) f.config).feature.config).state.getBlock() == Blocks.DIORITE) {
                        features.add(f);
                    }
                    if (((OreFeatureConfig) ((DecoratedFeatureConfig) f.config).feature.config).state.getBlock() == Blocks.IRON_ORE) {
                        features.add(f);
                    }
                    if (((OreFeatureConfig) ((DecoratedFeatureConfig) f.config).feature.config).state.getBlock() == Blocks.COAL_ORE) {
                        features.add(f);
                    }
                    if (((OreFeatureConfig) ((DecoratedFeatureConfig) f.config).feature.config).state.getBlock() == Blocks.REDSTONE_ORE) {
                        features.add(f);
                    }
                    if (((OreFeatureConfig) ((DecoratedFeatureConfig) f.config).feature.config).state.getBlock() == Blocks.GOLD_ORE) {
                        features.add(f);
                    }
                    if (((OreFeatureConfig) ((DecoratedFeatureConfig) f.config).feature.config).state.getBlock() == Blocks.DIAMOND_ORE) {
                        features.add(f);
                    }
                    if (((OreFeatureConfig) ((DecoratedFeatureConfig) f.config).feature.config).state.getBlock() == Blocks.EMERALD_ORE) {
                        features.add(f);
                    }
                    if (((OreFeatureConfig) ((DecoratedFeatureConfig) f.config).feature.config).state.getBlock() == Blocks.LAPIS_ORE) {
                        features.add(f);
                    }
                }
            }
            biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).removeAll(features);
        }
    }

}
