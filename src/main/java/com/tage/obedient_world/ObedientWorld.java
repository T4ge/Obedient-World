package com.tage.obedient_world;

import java.io.File;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.logging.log4j.Logger;

import com.tage.obedient_world.events.DecorateEvent;
import com.tage.obedient_world.events.EntityEvent;
import com.tage.obedient_world.events.PopulationEvent;
import com.tage.obedient_world.events.TerrainEvent;
import com.tage.obedient_world.util.ObedientData;
import com.tage.obedient_world.util.ObedientDataDefaults;
import com.tage.obedient_world.util.ObedientHelper;
import com.tage.obedient_world.util.ObedientDataDefaults.DecorationInformation;
import com.tage.obedient_world.util.ObedientDataDefaults.EntityInformation;
import com.tage.obedient_world.world.WorldGenOre;
import com.tage.obedient_world.world.WorldGenPatch;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistry;

@Mod(modid = ObedientWorld.MODID, name = ObedientWorld.NAME, version = ObedientWorld.VERSION)
public class ObedientWorld
{
    public static final String MODID = "obedient_world";
    public static final String NAME = "Obedient World";
    public static final String VERSION = "1.0.0";
    
    public static File dataFile;
    
    public static Logger logger;
    private File modConfigDir;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        //Register generator for Ores
    	if(ObedientWorldConfig.GENERAL.ores)
    	{
    		GameRegistry.registerWorldGenerator(new WorldGenOre(), 3);
    	}
    	//Register generator for custom Flower Patches
    	if(ObedientWorldConfig.GENERAL.flowers)
    	{
    		GameRegistry.registerWorldGenerator(new WorldGenPatch(), 1);
    	}
    	//Construct JSON Data
    	modConfigDir = event.getModConfigurationDirectory();
    	ObedientData.init(modConfigDir, "Decoration", ObedientDataDefaults.getDefaultsDecorate());
    	ObedientData.init(modConfigDir, "Population", ObedientDataDefaults.getDefaultsPopulate());
    	ObedientData.init(modConfigDir, "Terrain", ObedientDataDefaults.getDefaultsTerrain());
    	ObedientData.init(modConfigDir, "Ores", ObedientDataDefaults.getDefaultsOres());
    	ObedientData.init(modConfigDir, "Plants", ObedientDataDefaults.getDefaultsPlants());
    	
    	
}

	@EventHandler
    public void init(FMLInitializationEvent event)
    {
    	if(ObedientHelper.getTerrainValues())
    	{
    		System.out.println("TerrainEvent registered on the Bus");
    		MinecraftForge.TERRAIN_GEN_BUS.register(new TerrainEvent());
    	}
    	if(ObedientHelper.getPopulationValues())
    	{
    		MinecraftForge.TERRAIN_GEN_BUS.register(new PopulationEvent());
    	}
    	if(ObedientHelper.getDecorationValues())
    	{
    		MinecraftForge.TERRAIN_GEN_BUS.register(new DecorateEvent());
    	}
    	if(ObedientWorldConfig.GENERAL.ores)
    	{
    		MinecraftForge.ORE_GEN_BUS.register(new WorldGenOre());
    	}
    	if(ObedientWorldConfig.GENERAL.entities)
    	{
    		FMLCommonHandler.instance().bus().register(new EntityEvent());
    	}
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	//Construct Entity Data during post after the Entity Registry is filled.
    	ObedientData.init(modConfigDir, "Entities", ObedientDataDefaults.getDefaultsEntities());
    	
    	//Initiate Entity Control Handler
    	if(ObedientWorldConfig.GENERAL.entities)
    	{
    		EntityEvent entity = new EntityEvent();
    		entity.removeSpawn();
    	}
    }
}