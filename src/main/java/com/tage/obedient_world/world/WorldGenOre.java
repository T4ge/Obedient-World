package com.tage.obedient_world.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import com.google.common.base.Predicate;
import com.tage.obedient_world.ObedientWorldConfig;
import com.tage.obedient_world.util.ObedientDataDefaults;
import com.tage.obedient_world.util.ObedientHelper;
import com.tage.obedient_world.util.ObedientDataDefaults.OreInformation;

import net.minecraft.block.Block;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class WorldGenOre implements IWorldGenerator 
{
	//Event handler to cancel vanilla Ore Generation
	@SubscribeEvent()
	public void onOreGen(OreGenEvent.GenerateMinable event)
	{
		event.setResult(Result.DENY);
	}
	
	//New Ore Generation with data from JSON
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
		for (Iterator<Entry<String, OreInformation>> it = ObedientDataDefaults.registeredOres.entrySet().iterator(); it.hasNext();) 
		{
            Entry<String, OreInformation> pair = it.next();
			String block = pair.getValue().ore;
			int meta = pair.getValue().meta;
			int size = pair.getValue().size;
			int count = pair.getValue().count;
			int miny = pair.getValue().minY;
			int maxy = pair.getValue().maxY;
			String replace = pair.getValue().replaceBlock;
			int replacemeta = pair.getValue().replaceMeta;
			int generator = pair.getValue().generator;
			int dim = pair.getValue().dim;
			List<String> biomes = pair.getValue().biomes;
			
			WorldGenerator oreGen = new WorldGenMinable(Block.getBlockFromName(block).getStateFromMeta(meta), size, (Predicate<IBlockState>) Block.getBlockFromName(replace).getStateFromMeta(replacemeta));
			
    		if (world.provider.getDimension() == dim) 
    		{
    			if(generator == 0)
    			{	
    				genStandardOre1(world, random, count, oreGen, miny, maxy, chunkX * 16, chunkZ * 16, biomes, block);
    			}	
    			if(generator == 1)
    			{
    				genStandardOre2(world, random, count, oreGen, miny, maxy, chunkX * 16, chunkZ * 16, biomes, block);
    			}
    			if(generator == 2)
    			{
    				genEmeraldOre(world, random, count, oreGen, miny, maxy, chunkX * 16, chunkZ * 16, biomes, block, replace, meta);
    			}	
	    	}
		}
	}
	
	//Vanilla standard generation - Used by most common ores Coal, Iron, Gold, Diamond, Redstone
    protected void genStandardOre1(World worldIn, Random random, int blockCount, WorldGenerator generator, int minHeight, int maxHeight, int x, int z, List<String> biomes, String block)
    {
        if (maxHeight < minHeight)
        {
            int i = minHeight;
            minHeight = maxHeight;
            maxHeight = i;
        }
        else if (maxHeight == minHeight)
        {
            if (minHeight < 255)
            {
                ++maxHeight;
            }
            else
            {
                --minHeight;
            }
        }
        for (int j = 0; j < blockCount; ++j)
        {
        	BlockPos blockpos = new BlockPos(x   + random.nextInt(16), random.nextInt(maxHeight - minHeight) + minHeight, z + random.nextInt(16));
        	
        	String actualBiome = ObedientHelper.getBiomeRegName(blockpos).toString();
        	if(biomes.contains(actualBiome) || biomes.contains("all"))
        	{
        		generator.generate(worldIn, random, blockpos);
        	}
        }
    }

    //Spread generation - used by Lapis
    protected void genStandardOre2(World worldIn, Random random, int blockCount, WorldGenerator generator, int centerHeight, int spread, int x, int z, List<String> biomes, String block)
    {
        for (int i = 0; i < blockCount; ++i)
        {
        	BlockPos blockpos = new BlockPos(x   + random.nextInt(16), random.nextInt(spread) + random.nextInt(spread) + centerHeight - spread, z + random.nextInt(16));
        	
        	String actualBiome = ObedientHelper.getBiomeRegName(blockpos).toString();
        	if(biomes.contains(actualBiome) || biomes.contains("all"))
        	{
        		generator.generate(worldIn, random, blockpos);
        	}
        }
    }
    
    //Emerald generation - used Emerald
    public boolean genEmeraldOre(World worldIn, Random random, int blockCount, WorldGenerator generator, int centerHeight, int spread, int x, int z, List<String> biomes, String block, String replace, int meta)
    {
        int count = 3 + random.nextInt(6);
        for (int i = 0; i < count; i++)
        {
            int offset = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 8 : 0;
            BlockPos blockpos = new BlockPos(x + random.nextInt(16) + offset, random.nextInt(28) + 4, z + random.nextInt(16) + offset);

            net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos);
            if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, net.minecraft.block.state.pattern.BlockMatcher.forBlock(Block.getBlockFromName(replace))))
            {
            	String actualBiome = ObedientHelper.getBiomeRegName(blockpos).toString();
            	if(biomes.contains(actualBiome) || biomes.contains("all"))
            	{
            		worldIn.setBlockState(blockpos, Block.getBlockFromName(block).getStateFromMeta(meta), 16 | 2);
            	}
            }
        }
        return true;
    }
}