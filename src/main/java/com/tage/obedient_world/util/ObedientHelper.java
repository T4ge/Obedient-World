package com.tage.obedient_world.util;

import java.util.List;
import java.util.Set;

import com.tage.obedient_world.ObedientWorld;
import com.tage.obedient_world.util.ObedientDataDefaults.DecorationInformation;
import com.tage.obedient_world.util.ObedientDataDefaults.PopulationInformation;
import com.tage.obedient_world.util.ObedientDataDefaults.TerrainInformation;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistry;

//Helper class
@ObjectHolder(ObedientWorld.MODID)
public class ObedientHelper 
{
	//Get Biome Registry Name - Used by world.WorldGenOre
 	public static ResourceLocation getBiomeRegName(BlockPos pos)
	{
		return Minecraft.getMinecraft().world.getBiome(pos).getRegistryName();
	}
 	
 	public static boolean getDecorationValues()
 	{
    	for(DecorationInformation info : ObedientDataDefaults.registeredDecorations.values())
    	{
    		if(!info.canGenerate)
    			return true;
    	}
    	return false;
 	}
 	
 	public static boolean getPopulationValues()
 	{
    	for(PopulationInformation info : ObedientDataDefaults.registeredPopulation.values())
    	{
    		if(!info.canGenerate)
    			return true;
    	}
    	return false;
 	}
 	
 	public static boolean getTerrainValues()
 	{
    	for(TerrainInformation info : ObedientDataDefaults.registeredTerrain.values())
    	{
    		if(!info.canGenerate)
    			return true;
    	}
    	return false;
 	}
}
