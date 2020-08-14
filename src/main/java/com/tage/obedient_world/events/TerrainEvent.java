package com.tage.obedient_world.events;

import com.tage.obedient_world.ObedientWorldConfig;
import com.tage.obedient_world.overrides.MapGenOverride;
import com.tage.obedient_world.util.ObedientDataDefaults;

import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TerrainEvent 
{
	@SubscribeEvent
	public void initMapGenEvent(InitMapGenEvent event)
	{
		switch(event.getType())
		{
		case CAVE:
			if(ObedientWorldConfig.TERRAIN.terrain_events.Caves == false){
				event.setNewGen(new MapGenOverride.Empty());
			}
			break;
		case RAVINE:
			if(ObedientWorldConfig.TERRAIN.terrain_events.Ravines == false){
				event.setNewGen(new MapGenOverride.Empty());
			}
			break;
		case END_CITY:
			if(ObedientWorldConfig.TERRAIN.terrain_events.EndCity == false){
				event.setNewGen(new MapGenOverride.EndCity());
			}
			break;
		case MINESHAFT:
			if(ObedientWorldConfig.TERRAIN.terrain_events.Mineshaft == false){
				event.setNewGen(new MapGenOverride.Mineshaft());
			}
			break;
		case NETHER_BRIDGE:
			if(ObedientWorldConfig.TERRAIN.terrain_events.NetherBridge == false){
				event.setNewGen(new MapGenOverride.Bridge());
			}
			break;
		case NETHER_CAVE:
			if(ObedientWorldConfig.TERRAIN.terrain_events.NetherCave == false){
				event.setNewGen(new MapGenOverride.Empty());
			}
			break;
		case OCEAN_MONUMENT:
			if(ObedientWorldConfig.TERRAIN.terrain_events.OceanMonument == false){
				event.setNewGen(new MapGenOverride.Monument());
			}
			break;
		case SCATTERED_FEATURE:
			if(ObedientWorldConfig.TERRAIN.terrain_events.ScatteredFeature == false){
				event.setNewGen(new MapGenOverride.Scattered());
			}
			break;
		case VILLAGE:
			if(ObedientWorldConfig.TERRAIN.terrain_events.Village == false){
				event.setNewGen(new MapGenOverride.Village());
			}
			break;
		case WOODLAND_MANSION:
			if(ObedientWorldConfig.TERRAIN.terrain_events.WoodlandMansion == false){
				event.setNewGen(new MapGenOverride.Mansion());
			}
			break;
		case STRONGHOLD:
			if(ObedientWorldConfig.TERRAIN.terrain_events.Stronghold == false){
				event.setNewGen(new MapGenOverride.Stronghold());
			}
			break;
		case CUSTOM:
			System.out.println(event.getType().CUSTOM);
		default:
			break;
		}
	}
}