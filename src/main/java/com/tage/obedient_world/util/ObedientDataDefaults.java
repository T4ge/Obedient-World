package com.tage.obedient_world.util;

import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ObedientDataDefaults
{

    @FunctionalInterface
    public interface DefaultConfigProvider<T>
    {
        Map<String, T> createDefaults();
    }

    // Default Ore Data - Identical to Vanilla values
    public static Map<String, ObedientData.OreInformation> getDefaultsOres()
    {
        Map<String, ObedientData.OreInformation> data = new LinkedHashMap<>();
        data.put("Coal Ore", new ObedientData.OreInformation("minecraft:coal_ore", 0, 17, 20, 0, 128, "minecraft:stone", 0, 0, 0, Arrays.asList("ALL")));
        data.put("Iron Ore", new ObedientData.OreInformation("minecraft:iron_ore", 0, 9, 20, 0, 64, "minecraft:stone", 0, 0, 0, Arrays.asList("ALL")));
        data.put("Gold Ore", new ObedientData.OreInformation("minecraft:gold_ore", 0, 9, 2, 0, 32, "minecraft:stone", 0, 0, 0, Arrays.asList("ALL")));
        data.put("Redstone Ore", new ObedientData.OreInformation("minecraft:redstone_ore", 0, 8, 8, 0, 16, "minecraft:stone", 0, 0, 0, Arrays.asList("ALL")));
        data.put("Diamond Ore", new ObedientData.OreInformation("minecraft:diamond_ore", 0, 8, 1, 0, 16, "minecraft:stone", 0, 0, 0, Arrays.asList("ALL")));
        data.put("Lapis Ore", new ObedientData.OreInformation("minecraft:lapis_ore", 0, 7, 1, 0, 32, "minecraft:stone", 0, 1, 0, Arrays.asList("ALL")));
        data.put("Quartz Ore", new ObedientData.OreInformation("minecraft:quartz_ore", 0, 9, 20, 0, 64, "minecraft:netherrack", 0, 0, -1, Arrays.asList("ALL")));
        data.put("Emerald Ore", new ObedientData.OreInformation("minecraft:emerald_ore", 0, 7, 1, 0, 32, "minecraft:stone", 0, 2, 0, Arrays.asList("ALL")));
        data.put("Granite", new ObedientData.OreInformation("minecraft:stone", 1, 33, 10, 0, 80, "minecraft:stone", 0, 0, 0, Arrays.asList("ALL")));
        data.put("Diorite", new ObedientData.OreInformation("minecraft:stone", 3, 33, 10, 0, 80, "minecraft:stone", 0, 0, 0, Arrays.asList("ALL")));
        data.put("Andesite", new ObedientData.OreInformation("minecraft:stone", 5, 33, 10, 0, 80, "minecraft:stone", 0, 0, 0, Arrays.asList("ALL")));
        data.put("Dirt", new ObedientData.OreInformation("minecraft:dirt", 0, 33, 10, 0, 256, "minecraft:stone", 0, 0, 0, Arrays.asList("ALL")));
        data.put("Gravel", new ObedientData.OreInformation("minecraft:gravel", 0, 33, 8, 0, 256, "minecraft:stone", 0, 0, 0, Arrays.asList("ALL")));
        data.put("Mesa Gold", new ObedientData.OreInformation("minecraft:gold_ore", 0, 9, 20, 32, 80, "minecraft:stone", 0, 0, 0, Arrays.asList("minecraft:mesa", "minecraft:mesa_rock", "minecraft:mesa_clear_rock", "minecraft:mutated_mesa", "minecraft:mutated_mesa_rock", "minecraft:mutated_mesa_clear_rock")));
        data.put("Silverfish", new ObedientData.OreInformation("minecraft:monster_egg", 0, 9, 7, 0, 64, "minecraft:stone", 0, 0, 0, Arrays.asList("minecraft:extreme_hills", "minecraft:smaller_extreme_hills", "minecraft:extreme_hills_with_trees", "minecraft:mutated_extreme_hills", "minecraft:mutated_extreme_hills_with_trees")));
        return data;
    }

    // Default Flower Patch Data - With included example
    public static Map<String, ObedientData.PlantInformation> getDefaultsPlants()
    {
        Map<String, ObedientData.PlantInformation> data = new HashMap<>();
        data.put("ExampleWheat", new ObedientData.PlantInformation("minecraft:wheat", 7, true, 5, 5, 32, "minecraft:farmland", 0, "minecraft:grass", 0, Arrays.asList(0), Arrays.asList(Biomes.PLAINS.getRegistryName(), Biomes.MUTATED_PLAINS.getRegistryName())));
        return data;
    }

    // Default Entity Data - Built from Entity Registry [postInit dependent]
    public static Map<String, ObedientData.EntityInformation> getDefaultsEntities()
    {
        Map<String, ObedientData.EntityInformation> data = new HashMap<>();
        for (Entry<ResourceLocation, EntityEntry> entry : ForgeRegistries.ENTITIES.getEntries())
        {
            data.put(entry.getKey().toString(), new ObedientData.EntityInformation(true));
        }
        return data;
    }

    // Default Decoration Data - Avoiding CUSTOM
    public static Map<String, ObedientData.DecorationInformation> getDefaultsDecorate()
    {
        Map<String, ObedientData.DecorationInformation> data = new HashMap<>();
        Decorate.EventType[] arr = Decorate.EventType.values();
        Arrays.sort(arr);

        for (Decorate.EventType type : arr)
        {
            if (type != Decorate.EventType.CUSTOM)
            {
                data.put(type.toString(), new ObedientData.DecorationInformation(true));
            }
        }
        return data;
    }

    // Default Population Data - Avoiding CUSTOM
    public static Map<String, ObedientData.PopulationInformation> getDefaultsPopulate()
    {
        Map<String, ObedientData.PopulationInformation> data = new HashMap<>();
        Populate.EventType[] arr = Populate.EventType.values();
        Arrays.sort(arr);

        for (Populate.EventType type : arr)
        {
            if (type != Populate.EventType.CUSTOM)
            {
                data.put(type.toString(), new ObedientData.PopulationInformation(true));
            }
        }
        return data;
    }

    // Default Terrain Data - Avoiding CUSTOM
    public static Map<String, ObedientData.TerrainInformation> getDefaultsTerrain()
    {
        Map<String, ObedientData.TerrainInformation> data = new HashMap<>();
        InitMapGenEvent.EventType[] arr = InitMapGenEvent.EventType.values();
        Arrays.sort(arr);

        for (InitMapGenEvent.EventType type : arr)
        {
            if (type != InitMapGenEvent.EventType.CUSTOM)
            {
                data.put(type.toString(), new ObedientData.TerrainInformation(true));
            }
        }
        return data;
    }

}
