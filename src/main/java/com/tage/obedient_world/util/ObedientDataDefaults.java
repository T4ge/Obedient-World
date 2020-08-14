package com.tage.obedient_world.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ObedientDataDefaults 
{
	public static Map<String, OreInformation> registeredOres = new HashMap<String, OreInformation>();
    public static Map<String, PlantInformation> registeredPlants = new HashMap<String, PlantInformation>();
    public static Map<String, EntityInformation> registeredEntities = new HashMap<String, EntityInformation>();
    public static Map<String, DecorationInformation> registeredDecorations = new HashMap<String, DecorationInformation>();
    public static Map<String, PopulationInformation> registeredPopulation = new HashMap<String, PopulationInformation>();
    public static Map<String, TerrainInformation> registeredTerrain = new HashMap<String, TerrainInformation>();
    
    //Default Ore Data - Identical to Vanilla values
    public static Map<String, OreInformation> getDefaultsOres() {
        Map<String, OreInformation> data = new LinkedHashMap<String, OreInformation>();
        data.put("Coal Ore", new OreInformation("minecraft:coal_ore", 0, 17, 20, 0, 128, "minecraft:stone", 0, 0, 0, Arrays.asList(new String[]{"ALL"})));
        data.put("Iron Ore", new OreInformation("minecraft:iron_ore", 0, 9, 20, 0, 64, "minecraft:stone", 0, 0, 0, Arrays.asList(new String[]{"ALL"})));
        data.put("Gold Ore", new OreInformation("minecraft:gold_ore", 0, 9, 2, 0, 32, "minecraft:stone", 0, 0, 0, Arrays.asList(new String[]{"ALL"})));
        data.put("Redstone Ore", new OreInformation("minecraft:redstone_ore", 0, 8, 8, 0, 16, "minecraft:stone", 0, 0, 0, Arrays.asList(new String[]{"ALL"})));
        data.put("Diamond Ore", new OreInformation("minecraft:diamond_ore", 0, 8, 1, 0, 16, "minecraft:stone", 0, 0, 0, Arrays.asList(new String[]{"ALL"})));
        data.put("Lapis Ore", new OreInformation("minecraft:lapis_ore", 0, 7, 1, 0, 32, "minecraft:stone", 0, 1, 0, Arrays.asList(new String[]{"ALL"})));
        data.put("Quartz Ore", new OreInformation("minecraft:quartz_ore", 0, 9, 20, 0, 64, "minecraft:netherrack", 0, 0, -1, Arrays.asList(new String[]{"ALL"})));
        data.put("Emerald Ore", new OreInformation("minecraft:emerald_ore", 0, 7, 1, 0, 32, "minecraft:stone", 0, 2, 0, Arrays.asList(new String[]{"ALL"})));
        data.put("Granite", new OreInformation("minecraft:stone", 1, 33, 10, 0, 80, "minecraft:stone", 0, 0, 0, Arrays.asList(new String[]{"ALL"})));
        data.put("Diorite", new OreInformation("minecraft:stone", 3, 33, 10, 0, 80, "minecraft:stone", 0, 0, 0, Arrays.asList(new String[]{"ALL"})));
        data.put("Andesite", new OreInformation("minecraft:stone", 5, 33, 10, 0, 80, "minecraft:stone", 0, 0, 0, Arrays.asList(new String[]{"ALL"})));
        data.put("Dirt", new OreInformation("minecraft:dirt", 0, 33, 10, 0, 256, "minecraft:stone", 0, 0, 0, Arrays.asList(new String[]{"ALL"})));
        data.put("Gravel", new OreInformation("minecraft:gravel", 0, 33, 8, 0, 256, "minecraft:stone", 0, 0, 0, Arrays.asList(new String[]{"ALL"})));
        data.put("Mesa Gold", new OreInformation("minecraft:gold_ore", 0, 9, 20, 32, 80, "minecraft:stone", 0, 0, 0, Arrays.asList(new String[]{"minecraft:mesa", "minecraft:mesa_rock", "minecraft:mesa_clear_rock", "minecraft:mutated_mesa", "minecraft:mutated_mesa_rock", "minecraft:mutated_mesa_clear_rock"})));
        data.put("Silverfish", new OreInformation("minecraft:monster_egg", 0, 9, 7, 0, 64, "minecraft:stone", 0, 0, 0,Arrays.asList(new String[]{"minecraft:extreme_hills", "minecraft:smaller_extreme_hills", "minecraft:extreme_hills_with_trees", "minecraft:mutated_extreme_hills", "minecraft:mutated_extreme_hills_with_trees"})));
        return data;
    }
    
    //Default Flower Patch Data - With included example
    public static Map<String, PlantInformation> getDefaultsPlants() {
        Map<String, PlantInformation> data = new HashMap<String, PlantInformation>();
        data.put("ExampleWheat", new PlantInformation("minecraft:wheat", 7, true, 5, 5, 32, "minecraft:farmland", 0, "minecraft:grass", 0, Arrays.asList(0), Arrays.asList(new ResourceLocation[]{Biomes.PLAINS.getRegistryName(), Biomes.MUTATED_PLAINS.getRegistryName()})));
        return data;
    }
    
    //Default Entity Data - Built from Entity Registry [postInit dependent]
    public static Map<String, EntityInformation> getDefaultsEntities() {
        Map<String, EntityInformation> data = new HashMap<String, EntityInformation>();
        for(Iterator<Entry<ResourceLocation, EntityEntry>> it_entities = ForgeRegistries.ENTITIES.getEntries().iterator(); it_entities.hasNext();)
        {
        	Entry set = it_entities.next();
        	data.put(set.getKey().toString(), new EntityInformation(true));
        }
		return data;
    }
    
    //Default Decoration Data - Avoiding CUSTOM
    public static Map<String, DecorationInformation> getDefaultsDecorate(){
    	Map<String, DecorationInformation> data = new HashMap<String, DecorationInformation>();
    	Decorate.EventType[] arr = Decorate.EventType.values();
    	Arrays.sort(arr);
    	for(Decorate.EventType type : arr)
    	{
    		if(type != Decorate.EventType.CUSTOM) 
    		{
	    		data.put(type.toString(), new DecorationInformation(true));
    		}
    	}
    	return data;
    }
    
    //Default Population Data - Avoiding CUSTOM
    public static Map<String, PopulationInformation> getDefaultsPopulate(){
    	Map<String, PopulationInformation> data = new HashMap<String, PopulationInformation>();
    	Populate.EventType[] arr = Populate.EventType.values();
    	Arrays.sort(arr);
    	for(Populate.EventType type : arr)
    	{
    		if(type != Populate.EventType.CUSTOM) 
    		{
	    		data.put(type.toString(), new PopulationInformation(true));
    		}
    	}
    	return data;
    }
    
    //Default Terrain Data - Avoiding CUSTOM
    public static Map<String, TerrainInformation> getDefaultsTerrain(){
    	Map<String, TerrainInformation> data = new HashMap<String, TerrainInformation>();
    	InitMapGenEvent.EventType[] arr = InitMapGenEvent.EventType.values();
    	Arrays.sort(arr);
    	for(InitMapGenEvent.EventType type : arr)
    	{
    		if(type != InitMapGenEvent.EventType.CUSTOM) 
    		{
    			data.put(type.toString(), new TerrainInformation(true));
    		}
    	}
    	return data;
    }

    //List of Ore customization
    public static class OreInformation {
        public final String ore;
        public transient final Block oreObject;
        public final int meta;
        public final int size;
        public final int count;
        public final int minY;
        public final int maxY;
        public final String replaceBlock;
        public transient final Block replaceObject;
        public final int replaceMeta;
        public final int generator;
        public final int dim;
        public final List biomes;

        public OreInformation(String ore, int meta, int size, int count, int minY, int maxY, String replaceBlock, int replaceMeta, int generator, int dim, List<String> biomes) {
            this.ore = ore;
            this.oreObject = Block.REGISTRY.getObject(new ResourceLocation(ore));
            this.meta = meta;
            this.size = size;
            this.count = count;
            this.minY = minY;
            this.maxY = maxY;
            this.replaceBlock = replaceBlock;
            this.replaceObject = Block.REGISTRY.getObject(new ResourceLocation(replaceBlock));
            this.replaceMeta = replaceMeta;
            this.generator = generator;
            this.dim = dim;
            this.biomes = biomes;
            
        }
    }
    
    //Currently unused enum.
    public static enum PatchGenerators
    {
    	PUMPKIN,
    	FLOWER,
    	DEFAULT
    }
    
    //List of Patch customization
    public static class PlantInformation {
        public final String crop;
        public transient final Block cropObject;
        public final int meta;
        public final boolean randomMeta;
        public final int spread;
        public final int count;
        public final int chance;
        public final String support;
        public transient final Block supportObject;
        public final int supportMeta;
        public final String placeOn;
        public transient final Block placeOnObject;
        public final int placeOnMeta;
        public final List <Integer> dim;
        public final List <ResourceLocation> biomes;

        public PlantInformation(String crop, int meta, boolean randomMeta, int spread, int count, int chance, String support, int supportMeta, String placeOn, int placeOnMeta, List<Integer> dim, List<ResourceLocation> list) {
            this.crop = crop;
            this.cropObject = Block.REGISTRY.getObject(new ResourceLocation(crop));
            this.meta = meta;
            this.randomMeta = randomMeta;
            this.spread = spread;
            this.count = count;
            this.chance = chance;
            this.support = support;
            this.supportMeta = supportMeta;
            this.supportObject = Block.REGISTRY.getObject(new ResourceLocation(support));
            this.placeOn = placeOn;
            this.placeOnObject = Block.REGISTRY.getObject(new ResourceLocation(placeOn));
            this.placeOnMeta = placeOnMeta;
            this.dim = dim;
            this.biomes = list;
            
        }
    }
    
    //List of Entity customization
    public static class EntityInformation {
        public final boolean canSpawn;
        public EntityInformation(boolean canSpawn) {
            this.canSpawn = canSpawn;     
        }
    }
    
    //List of Decoration customization
    public static class DecorationInformation {
    	public final boolean canGenerate;
    	public DecorationInformation(boolean canGenerate) {
    		this.canGenerate = canGenerate;
    	}
    }
    
    //List of Population customization
    public static class PopulationInformation {
    	public final boolean canGenerate;
    	public PopulationInformation(boolean canGenerate) {
    		this.canGenerate = canGenerate;
    	}
    }
    
    //List of Terrain customization
    public static class TerrainInformation {
    	public final boolean canGenerate;
    	public TerrainInformation(boolean canGenerate) {
    		this.canGenerate = canGenerate;
    	}
    }
}
