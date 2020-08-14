package com.tage.obedient_world.overrides;

import com.tage.obedient_world.ObedientWorldConfig;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorEnd;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.structure.MapGenEndCity;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraft.world.gen.structure.WoodlandMansion;

//Class to empty Structure generation for cases that crash with attempts of canceling and denying
public class MapGenOverride 
{
	public static class Empty extends MapGenBase
	{
	    @Override
	    public void generate (World worldIn, int x, int z, ChunkPrimer primer) 
	    {
	    }
	}
	
	public static class Stronghold extends MapGenStronghold
	{
	    @Override
	    public void generate (World world, int x, int z, ChunkPrimer cp) 
	    {
	    }
		
	    @Override
	    public boolean canSpawnStructureAtCoords (int x, int z) 
	    {
	        return false;
	    }
	}
	
	public static class Village extends MapGenVillage
	{
	    @Override
	    public void generate (World world, int x, int z, ChunkPrimer cp) 
	    {
	    }
		
	    @Override
	    public boolean canSpawnStructureAtCoords (int x, int z) 
	    {
	        return false;
	    }
	}
	
	public static class Mansion extends WoodlandMansion
	{
	    public Mansion() 
	    {
			super(null);
		}

		@Override
	    public void generate (World world, int x, int z, ChunkPrimer cp) 
	    {
	    }
		
	    @Override
	    public boolean canSpawnStructureAtCoords (int x, int z) 
	    {
	        return false;
	    }
	}
	
	public static class EndCity extends MapGenEndCity 
	{
		public EndCity() {
			super(null);
		}

		@Override
	    public void generate (World world, int x, int z, ChunkPrimer cp) 
	    {
	    }
		
	    @Override
	    public boolean canSpawnStructureAtCoords (int x, int z) 
	    {
	        return false;
	    }
	}
	
	public static class Monument extends StructureOceanMonument
	{
	    @Override
	    public void generate (World world, int x, int z, ChunkPrimer cp) 
	    {
	    }
		
	    @Override
	    public boolean canSpawnStructureAtCoords (int x, int z) 
	    {
	        return false;
	    }
	}
	
	public static class Mineshaft extends MapGenMineshaft 
	{
	    @Override
	    public void generate (World world, int x, int z, ChunkPrimer cp) 
	    {
	    }
		
	    @Override
	    public boolean canSpawnStructureAtCoords (int x, int z) 
	    {
	        return false;
	    }
	}
	
	public static class Bridge extends MapGenNetherBridge
	{
	    @Override
	    public void generate (World world, int x, int z, ChunkPrimer cp) 
	    {
	    }
		
	    @Override
	    public boolean canSpawnStructureAtCoords (int x, int z) 
	    {
	        return false;
	    }
	}
	
	public static class Scattered extends MapGenScatteredFeature 
	{
		public static enum Type { DESERTPYRAMID, JUNGLETEMPLE, WITCHHUT, IGLOO;}
	    @Override
	    public void generate (World world, int x, int z, ChunkPrimer cp) 
	    {
	    }
		
	    @Override
	    public boolean canSpawnStructureAtCoords (int x, int z) 
	    {
	    	final Biome biome = this.world.getBiomeProvider().getBiome(new BlockPos(x * 16 + 8, 0, z * 16 + 8));
	    	if(ObedientWorldConfig.TERRAIN.scattered_events.DesertPyramid && (biome == Biomes.DESERT || biome == Biomes.DESERT_HILLS)){
	    		return false;
	    	}
	    	if(ObedientWorldConfig.TERRAIN.scattered_events.JungleTemple && (biome == Biomes.JUNGLE || biome == Biomes.JUNGLE_HILLS)){
	    		return false;
	    	}
	    	if(ObedientWorldConfig.TERRAIN.scattered_events.WitchHut && biome == Biomes.SWAMPLAND){
	    		return false;
	    	}
	    	if(ObedientWorldConfig.TERRAIN.scattered_events.Igloo && (biome == Biomes.ICE_PLAINS || biome == Biomes.COLD_TAIGA)){
	    		return false;
	    	}
			return this.canSpawnStructureAtCoords(x, z);
	    }
	}	
}