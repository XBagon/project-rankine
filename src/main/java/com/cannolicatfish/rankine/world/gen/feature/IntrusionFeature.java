package com.cannolicatfish.rankine.world.gen.feature;

import com.cannolicatfish.rankine.blocks.RankineOre;
import com.cannolicatfish.rankine.init.ModBlocks;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockBlobFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;
import java.util.function.Function;

public class IntrusionFeature extends Feature<ReplacerFeatureConfig> {
    public IntrusionFeature(Codec<ReplacerFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, ReplacerFeatureConfig config) {

        float CHANCE = rand.nextFloat();
        BlockState INTRUSION = null;

        int startY = 0;
        int endY = 0;
        int radius = 0;

        if (CHANCE < 0.1) {
            INTRUSION = ModBlocks.KIMBERLITE.getDefaultState();
            endY = reader.getHeight(Heightmap.Type.OCEAN_FLOOR,pos.getX(),pos.getZ())-20;
            radius = 5-rand.nextInt(2);
        } else if (CHANCE < 0.14) {
            INTRUSION = ModBlocks.GRANODIORITE.getDefaultState();
            endY = reader.getHeight(Heightmap.Type.OCEAN_FLOOR,pos.getX(),pos.getZ());
            radius = 9-rand.nextInt(3);
        } else if (CHANCE < 0.17) {
            INTRUSION = Blocks.DIORITE.getDefaultState();
            endY = reader.getHeight(Heightmap.Type.OCEAN_FLOOR,pos.getX(),pos.getZ());
            radius = 9-rand.nextInt(3);
        } else if (CHANCE < 0.20) {
            INTRUSION = Blocks.GRANITE.getDefaultState();
            endY = reader.getHeight(Heightmap.Type.OCEAN_FLOOR,pos.getX(),pos.getZ());
            radius = 9-rand.nextInt(3);
        }

        if (INTRUSION!= null) {
            for (int y = startY; y <= endY; ++y) {
                for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-radius, y - 1, -radius), pos.add(radius, y - 1, radius))) {
                    if (blockpos.distanceSq(new BlockPos(pos.getX(), y, pos.getZ())) <= Math.pow(radius + 0.5, 2)) {
                        if (reader.getBlockState(blockpos) == config.target) {
                            if (INTRUSION == ModBlocks.KIMBERLITE.getDefaultState() && y <= 25) {
                                if (rand.nextFloat() < 0.06F) {
                                    reader.setBlockState(blockpos, ModBlocks.DIAMOND_ORE.getDefaultState().with(RankineOre.TYPE, 28), 4);
                                } else if (rand.nextFloat() < 0.075F) {
                                    reader.setBlockState(blockpos, ModBlocks.ILMENITE_ORE.getDefaultState().with(RankineOre.TYPE, 28), 4);
                                } else {
                                    reader.setBlockState(blockpos, INTRUSION, 4);
                                }
                            } else {
                                reader.setBlockState(blockpos, INTRUSION, 4);
                            }
                        }
                    }
                }
                if (rand.nextFloat() < 0.15) {
                    radius -= 1;
                    if (radius <= 0) {
                        return true;
                    }
                }
            }
        }
        return true;
    }


}
