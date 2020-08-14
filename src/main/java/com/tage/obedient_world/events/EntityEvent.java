package com.tage.obedient_world.events;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.tage.obedient_world.ObedientWorldConfig;
import com.tage.obedient_world.util.ObedientDataDefaults;
import com.tage.obedient_world.util.ObedientHelper;
import com.tage.obedient_world.util.ObedientDataDefaults.EntityInformation;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import scala.collection.parallel.Tasks;

public class EntityEvent 
{	
	
	//Iterate through Entities.json and remove spawns from registry
	public void removeSpawn()
	{	
		List<Biome> biomes = ForgeRegistries.BIOMES.getValues();
		
		for(Iterator<Entry<String, EntityInformation>> it = ObedientDataDefaults.registeredEntities.entrySet().iterator(); it.hasNext();) 
		{
        	Entry<String, EntityInformation> set = it.next();
        	EntityEntry entry = ((ForgeRegistry<EntityEntry>) ForgeRegistries.ENTITIES).getValue(new ResourceLocation(set.getKey()));

        	EnumCreatureType type = getDefaultMobType((Class<? extends EntityLiving>) entry.getEntityClass(), null);
        	
        	if(!set.getValue().canSpawn)
        	{
        		if(type == EnumCreatureType.AMBIENT)
        		{
        			EntityRegistry.removeSpawn((Class<? extends EntityLiving>) entry.getEntityClass(), EnumCreatureType.AMBIENT, biomes.toArray(new Biome[0]));
        			it.remove();
        			continue;
        		}
        		if(type == EnumCreatureType.CREATURE)
        		{
        			EntityRegistry.removeSpawn((Class<? extends EntityLiving>) entry.getEntityClass(), EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
        			it.remove();
        			continue;
        		}
        		if(type == EnumCreatureType.MONSTER)
        		{
        			EntityRegistry.removeSpawn((Class<? extends EntityLiving>) entry.getEntityClass(), EnumCreatureType.MONSTER, biomes.toArray(new Biome[0]));
        			it.remove();
        			continue;
        		}
        		if(type == EnumCreatureType.WATER_CREATURE)
        		{
        			EntityRegistry.removeSpawn((Class<? extends EntityLiving>) entry.getEntityClass(), EnumCreatureType.WATER_CREATURE, biomes.toArray(new Biome[0]));
        			it.remove();
        			continue;
        		}
        	}
        }
	}
	
	//Get default entity type
	public static EnumCreatureType getDefaultMobType(Class<? extends EntityLiving> cls, EnumCreatureType type) {

			if (EntityAmbientCreature.class.isAssignableFrom(cls))
				return EnumCreatureType.AMBIENT;
			if (EntityWaterMob.class.isAssignableFrom(cls))
				return EnumCreatureType.WATER_CREATURE;
			if (IMob.class.isAssignableFrom(cls))
				return EnumCreatureType.MONSTER;
			if (EntityCreature.class.isAssignableFrom(cls))
				return EnumCreatureType.CREATURE;
		return type;
	}
	
	//Prepare Hashmap for entities without registered spawns
	public static HashMap<String, Boolean> disableSpawns = new HashMap<>();
	{
		for(Iterator<Entry<String, EntityInformation>> it = ObedientDataDefaults.registeredEntities.entrySet().iterator(); it.hasNext();) 
		{
			Entry<String, EntityInformation> set = it.next();
			disableSpawns.put(set.getKey(), set.getValue().canSpawn);
		}
	}
	
	//Cancel spawning of entities without registered spawns - Arrows, Snowballs etc...
	@SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event)
    {	
		if(event.getEntity() instanceof EntityPlayer || event.getEntity() instanceof EntityPlayerMP)
		{
			return;
		}
		
		String entityReg = EntityRegistry.getEntry(event.getEntity().getClass()).getRegistryName().toString();
		
		if(ObedientWorldConfig.GENERAL.debug)
		{
			System.out.println(entityReg + " was stopped from spawning @ " + event.getEntity().getPosition());
		}
		
		if(disableSpawns.containsKey(entityReg) && !(boolean)disableSpawns.get(entityReg))
		{
			event.setCanceled(true);
		}
    }
}