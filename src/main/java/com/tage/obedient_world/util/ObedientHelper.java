package com.tage.obedient_world.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class ObedientHelper
{
	// Get Biome Registry Name - Used by world.WorldGenOre
 	public static ResourceLocation getBiomeRegName(BlockPos pos)
	{
		return Minecraft.getMinecraft().world.getBiome(pos).getRegistryName();
	}
 	
 	public static boolean areAnyDecorationsDisabled()
 	{
    	for(ObedientData.DecorationInformation info : ObedientData.registeredDecorations.values())
    	{
    		if(!info.canGenerate)
    			return true;
    	}
    	return false;
 	}
 	
 	public static boolean areAnyPopulationsDisabled()
 	{
    	for(ObedientData.PopulationInformation info : ObedientData.registeredPopulation.values())
    	{
    		if(!info.canGenerate)
    			return true;
    	}
    	return false;
 	}
 	
 	public static boolean getTerrainValues()
 	{
    	for(ObedientData.TerrainInformation info : ObedientData.registeredTerrain.values())
    	{
    		if(!info.canGenerate)
    			return true;
    	}
    	return false;
 	}
}
