package com.tage.obedient_world.events;

import java.util.HashMap;
import java.util.Map;

import com.tage.obedient_world.ObedientWorldConfig;
import com.tage.obedient_world.util.ObedientDataDefaults;
import com.tage.obedient_world.util.ObedientDataDefaults.PopulationInformation;

import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class PopulationEvent 
{
    @SubscribeEvent
    public void  PopulateChunkEvent(PopulateChunkEvent.Populate event)
    {
    	//New test event handler code to use JSON data
    	if(!ObedientDataDefaults.registeredPopulation.get(event.getType().name()).canGenerate)
    	{
    		event.setResult(Result.DENY);
    	}
    	
    	//Old switch
    	switch(event.getType())
		{
    	case LAVA:
    		if(ObedientWorldConfig.TERRAIN.population_events.Lava == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case LAKE:
    		if(ObedientWorldConfig.TERRAIN.population_events.Lake == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case ANIMALS:
    		if(ObedientWorldConfig.TERRAIN.population_events.Animals == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case DUNGEON:
    		if(ObedientWorldConfig.TERRAIN.population_events.Dungeon == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case FIRE:
    		if(ObedientWorldConfig.TERRAIN.population_events.Fire == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case GLOWSTONE:
    		if(ObedientWorldConfig.TERRAIN.population_events.Glowstone == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case ICE:
    		if(ObedientWorldConfig.TERRAIN.population_events.Ice == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case NETHER_LAVA:
    		if(ObedientWorldConfig.TERRAIN.population_events.NetherLava == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case NETHER_LAVA2:
    		if(ObedientWorldConfig.TERRAIN.population_events.NetherLava2 == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case NETHER_MAGMA:
    		if(ObedientWorldConfig.TERRAIN.population_events.NetherMagma == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	default:
    		break;
		}
    }
}