package com.tage.obedient_world.util;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tage.obedient_world.ObedientWorld;
import com.tage.obedient_world.util.ObedientDataDefaults.DecorationInformation;
import com.tage.obedient_world.util.ObedientDataDefaults.EntityInformation;
import com.tage.obedient_world.util.ObedientDataDefaults.OreInformation;
import com.tage.obedient_world.util.ObedientDataDefaults.PlantInformation;
import com.tage.obedient_world.util.ObedientDataDefaults.PopulationInformation;
import com.tage.obedient_world.util.ObedientDataDefaults.TerrainInformation;

public class ObedientData 
{
    public static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();

    public static void init(File dataDirectory, String dataName, Map defaultMap) 
    {
    	File configDirectory = new File(dataDirectory, ObedientWorld.MODID);
    	File configFile = new File(configDirectory, dataName + ".json");
    	try {
    		//Write JSON files
    		if(configDirectory.mkdir())
    		{
    			configDirectory.mkdir();
    		}
            if (!configFile.exists() && configFile.createNewFile()) 
            {
            	String json = "";
            	if(dataName == "Ores")
            	{
            		json = gson.toJson(defaultMap, new TypeToken<Map<String, OreInformation>>(){}.getType());
            	}
            	if(dataName == "Plants")
            	{
            		json = gson.toJson(defaultMap, new TypeToken<Map<String, PlantInformation>>(){}.getType());
            	}
            	if(dataName == "Entities")
            	{
            		json = gson.toJson(defaultMap, new TypeToken<Map<String, EntityInformation>>(){}.getType());
            	}
            	if(dataName == "Decoration")
            	{
            		json = gson.toJson(defaultMap, new TypeToken<Map<String, DecorationInformation>>(){}.getType());
            	}
            	if(dataName == "Population")
            	{
            		json = gson.toJson(defaultMap, new TypeToken<Map<String, PopulationInformation>>(){}.getType());
            	}
            	if(dataName == "Terrain")
            	{
            		json = gson.toJson(defaultMap, new TypeToken<Map<String, TerrainInformation>>(){}.getType());
            	}
                FileWriter writer = new FileWriter(configFile);
                writer.write(json);
                writer.close();
            }
            //Read JSON files
            if(dataName == "Ores")
        	{
            	ObedientDataDefaults.registeredOres = gson.fromJson(new FileReader(configFile), new TypeToken<Map<String, OreInformation>>(){}.getType());
        	}
            if(dataName == "Plants")
        	{
            	ObedientDataDefaults.registeredPlants = gson.fromJson(new FileReader(configFile), new TypeToken<Map<String, PlantInformation>>(){}.getType());
        	}
            if(dataName == "Entities")
        	{
            	ObedientDataDefaults.registeredEntities = gson.fromJson(new FileReader(configFile), new TypeToken<Map<String, EntityInformation>>(){}.getType());
        	}
            if(dataName == "Decoration")
        	{
            	ObedientDataDefaults.registeredDecorations = gson.fromJson(new FileReader(configFile), new TypeToken<Map<String, DecorationInformation>>(){}.getType());
        	}
            if(dataName == "Population")
        	{
            	ObedientDataDefaults.registeredPopulation = gson.fromJson(new FileReader(configFile), new TypeToken<Map<String, PopulationInformation>>(){}.getType());
        	}
            if(dataName == "Terrain")
        	{
            	ObedientDataDefaults.registeredTerrain = gson.fromJson(new FileReader(configFile), new TypeToken<Map<String, TerrainInformation>>(){}.getType());
        	}
        } 
    	catch (IOException e) 
    	{
            System.out.println("Error creating default configuration.\n" + e.getMessage() + "\n" + e.getCause());
        }
    }
}
