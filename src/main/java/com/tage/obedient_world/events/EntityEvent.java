package com.tage.obedient_world.events;

import com.tage.obedient_world.ObedientWorldConfig;
import com.tage.obedient_world.util.ObedientData;
import com.tage.obedient_world.util.ObedientData.EntityInformation;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EntityEvent 
{	
	
	// Iterate through Entities.json and remove spawns from registry
	public static void removeSpawn()
	{	
		Biome[] biomes = ForgeRegistries.BIOMES.getValuesCollection().toArray(new Biome[0]);

		Iterator<Entry<String, EntityInformation>> it = ObedientData.registeredEntities.entrySet().iterator();
		while(it.hasNext())
		{
        	Entry<String, EntityInformation> set = it.next();
        	EntityEntry entry = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(set.getKey()));

        	Class<? extends EntityLiving> entityClass = (Class<? extends EntityLiving>) entry.getEntityClass();
        	EnumCreatureType type = getDefaultMobType(entityClass);
        	
        	if(!set.getValue().canSpawn)
        	{
				List<EnumCreatureType> creatureTypes = Arrays.asList(
					EnumCreatureType.AMBIENT,
					EnumCreatureType.CREATURE,
					EnumCreatureType.MONSTER,
					EnumCreatureType.WATER_CREATURE
				);

				if (creatureTypes.contains(type))
				{
					EntityRegistry.removeSpawn(entityClass, type, biomes);
					it.remove();
				}
        	}
        }
	}

	/**
	 * Gets the default entity type for a class.
	 * @param cls The entity class
	 * @return The default type
	 */
	public static EnumCreatureType getDefaultMobType(Class<? extends EntityLiving> cls)
	{
		if (EntityAmbientCreature.class.isAssignableFrom(cls))
			return EnumCreatureType.AMBIENT;
		if (EntityWaterMob.class.isAssignableFrom(cls))
			return EnumCreatureType.WATER_CREATURE;
		if (IMob.class.isAssignableFrom(cls))
			return EnumCreatureType.MONSTER;
		if (EntityCreature.class.isAssignableFrom(cls))
			return EnumCreatureType.CREATURE;

		return null;
	}
	
	// Cancel spawning of entities without registered spawns - Arrows, Snowballs etc...
	@SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event)
    {	
		if(event.getEntity() instanceof EntityPlayer || event.getEntity() instanceof EntityPlayerMP)
		{
			return;
		}
		
		String entityReg = EntityRegistry.getEntry(event.getEntity().getClass()).getRegistryName().toString();

		Map<String, EntityInformation> registeredEntities = ObedientData.registeredEntities;
		if(registeredEntities.containsKey(entityReg) && !registeredEntities.get(entityReg).canSpawn)
		{
			event.setCanceled(true);

			if(ObedientWorldConfig.GENERAL.debug)
			{
				System.out.println(entityReg + " was stopped from spawning @ " + event.getEntity().getPosition());
			}
		}
    }
}