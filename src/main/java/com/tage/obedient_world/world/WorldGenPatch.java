package com.tage.obedient_world.world;

import com.tage.obedient_world.util.ObedientData;
import com.tage.obedient_world.util.ObedientData.PlantInformation;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

public class WorldGenPatch implements IWorldGenerator 
{
	public BlockPos chunkPos;
	
	// Generate flower patches
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
		final int x = chunkX * 16 + 8;
		final int z = chunkZ * 16 + 8;
		for(Iterator<Entry<String, PlantInformation>> it = ObedientData.registeredPlants.entrySet().iterator(); it.hasNext();)
		{
			Entry<String, PlantInformation> set = it.next();
			if(set.getValue().dim.contains(world.provider.getDimension()))
			{
				generateDefault(world, random, set.getValue(), x, z);
			}
		}
	}
	
	//Generates standard patches - much like vanilla flower gen.
	private void generateDefault(World world, Random random, PlantInformation set, int x, int z)
	{
		BlockPos pos = new BlockPos(x, world.getHeight(x, z), z);
		Biome biome = world.getBiome(pos);
		this.chunkPos = pos;
		if(!set.biomes.isEmpty())
		{
			if(!set.biomes.contains(biome.getRegistryName()))
			{
				return;
			}
		}
		if(random.nextInt(set.chance) == 0)
		{
			for(int i = 0; i < random.nextInt(set.count); ++i)
			{
				BlockPos blockpos = pos.add(random.nextInt(set.spread) - random.nextInt(set.spread), 0, random.nextInt(set.spread) - random.nextInt(set.spread));
				if(!set.support.isEmpty())
				{
					if(biome.topBlock.getBlock().getBlockState().getValidStates().contains(Block.getBlockFromName(set.placeOn).getStateFromMeta(set.placeOnMeta)))
					{
						if(world.getBlockState(blockpos.down()) == Block.getBlockFromName(set.placeOn).getStateFromMeta(set.placeOnMeta))
						{
							world.setBlockState(blockpos.down(), Block.getBlockFromName(set.support).getStateFromMeta(set.supportMeta), 2);
						}
					}
				}
				int meta = set.meta;
				if(set.randomMeta)
				{
					meta = random.nextInt(set.meta);
				}
				if(world.isAirBlock(blockpos) && !world.getBlockState(blockpos.down()).getMaterial().isLiquid() && world.getBlockState(blockpos).getBlock().isReplaceable(world, blockpos))
				{
					if(world.getBlockState(blockpos.down()) == Block.getBlockFromName(set.support).getStateFromMeta(set.supportMeta))
					{
						//System.out.println("Placed " + Block.getBlockFromName(set.crop).getLocalizedName().toString() + " @ " + blockpos);
						world.setBlockState(blockpos, Block.getBlockFromName(set.crop).getStateFromMeta(meta), 2);
					}
				}
			}
		}
	}
}
