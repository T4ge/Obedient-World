package com.tage.obedient_world.events;


import com.tage.obedient_world.ObedientWorldConfig;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DecorateEvent 
{
    @SubscribeEvent
    public void  PopulateChunkEvent(DecorateBiomeEvent.Decorate event)
    {
    	switch(event.getType())
		{
    	case BIG_SHROOM:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.BigShroom == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case CACTUS:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Cactus == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case CLAY:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Clay == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case DEAD_BUSH:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.DeadBush == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case DESERT_WELL:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.DesertWell == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case FLOWERS:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Flowers == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case FOSSIL:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Fossil == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case GRASS:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Grass == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case ICE:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Ice == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case LAKE_LAVA:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.LakeLava == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case LAKE_WATER:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.LakeWater == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case LILYPAD:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Lilypad == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case PUMPKIN:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Pumpkin == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case REED:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Reed == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case ROCK:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Rock == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case SAND:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Sand == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case SAND_PASS2:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.SandPass2 == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case SHROOM:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Shroom == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	case TREE:
    		if(ObedientWorldConfig.TERRAIN.decoration_events.Tree == false){
    			event.setResult(Result.DENY);
    		}
    		break;
    	default:
    		break;
		}
    }
}