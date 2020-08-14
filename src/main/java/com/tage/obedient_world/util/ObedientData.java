package com.tage.obedient_world.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tage.obedient_world.ObedientWorld;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObedientData 
{
    public static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();

	public static Map<String, OreInformation> registeredOres = new HashMap<>();
	public static Map<String, PlantInformation> registeredPlants = new HashMap<>();
	public static Map<String, EntityInformation> registeredEntities = new HashMap<>();
	public static Map<String, DecorationInformation> registeredDecorations = new HashMap<>();
	public static Map<String, PopulationInformation> registeredPopulation = new HashMap<>();
	public static Map<String, TerrainInformation> registeredTerrain = new HashMap<>();

    public enum ConfigCategory
	{
		ORES("Ores", OreInformation.class),
		PLANTS("Plants", PlantInformation.class),
		ENTITIES("Entities", EntityInformation.class),
		DECORATION("Decoration", DecorationInformation.class),
		POPULATION("Population", PopulationInformation.class),
		TERRAIN("Terrain", TerrainInformation.class);

		public String configFilename;
		public Type infoType;

		ConfigCategory(String configFilename, Type infoType)
		{
			this.configFilename = configFilename;
			this.infoType = infoType;
		}
	}

    public static <T> void init(File dataDirectory, ConfigCategory dataCategory, ObedientDataDefaults.DefaultConfigProvider<T> provider)
    {
    	File configDirectory = new File(dataDirectory, ObedientWorld.MODID);
		File configFile = new File(configDirectory, dataCategory.configFilename + ".json");
		
    	try {
    		// Write JSON files
    		configDirectory.mkdir();

			Type mapType = TypeToken.getParameterized(Map.class, String.class, dataCategory.infoType).getType();

			if (!configFile.exists() && configFile.createNewFile())
            {
				String json = gson.toJson(provider.createDefaults(), mapType);
                FileWriter writer = new FileWriter(configFile);
                writer.write(json);
                writer.close();
            }

			Map<String, T> map = gson.fromJson(new FileReader(configFile), mapType);

            // Read JSON files
            switch (dataCategory)
			{
				case ORES:
					registeredOres = (Map<String, OreInformation>) map;
					break;
				case PLANTS:
					registeredPlants = (Map<String, PlantInformation>) map;
					break;
				case ENTITIES:
					registeredEntities = (Map<String, EntityInformation>) map;
					break;
				case DECORATION:
					registeredDecorations = (Map<String, DecorationInformation>) map;
					break;
				case POPULATION:
					registeredPopulation = (Map<String, PopulationInformation>) map;
					break;
				case TERRAIN:
					registeredTerrain = (Map<String, TerrainInformation>) map;
					break;
			}
        }
    	catch (IOException e) 
    	{
            System.out.println("Error creating default configuration.\n" + e.getMessage() + "\n" + e.getCause());
        }
    }


	// List of Ore customization
	public static class OreInformation
	{
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
		public final List<String> biomes;

		public OreInformation(String ore, int meta, int size, int count, int minY, int maxY, String replaceBlock, int replaceMeta, int generator, int dim, List<String> biomes)
		{
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
	public enum PatchGenerators
	{
		PUMPKIN,
		FLOWER,
		DEFAULT
	}

	// List of Patch customization
	public static class PlantInformation
	{
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
		public final List<Integer> dim;
		public final List<ResourceLocation> biomes;

		public PlantInformation(String crop, int meta, boolean randomMeta, int spread, int count, int chance, String support, int supportMeta, String placeOn, int placeOnMeta, List<Integer> dim, List<ResourceLocation> list)
		{
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

	// List of Entity customization
	public static class EntityInformation
	{
		public final boolean canSpawn;

		public EntityInformation(boolean canSpawn)
		{
			this.canSpawn = canSpawn;
		}
	}

	// List of Decoration customization
	public static class DecorationInformation
	{
		public final boolean canGenerate;

		public DecorationInformation(boolean canGenerate)
		{
			this.canGenerate = canGenerate;
		}
	}

	// List of Population customization
	public static class PopulationInformation
	{
		public final boolean canGenerate;

		public PopulationInformation(boolean canGenerate)
		{
			this.canGenerate = canGenerate;
		}
	}

	// List of Terrain customization
	public static class TerrainInformation
	{
		public final boolean canGenerate;

		public TerrainInformation(boolean canGenerate)
		{
			this.canGenerate = canGenerate;
		}
	}
}
