package com.tage.obedient_world.events;


import com.tage.obedient_world.util.ObedientData;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PopulationEvent
{

    @SubscribeEvent
    public void PopulateChunkEvent(PopulateChunkEvent.Populate event)
    {
    	if(!ObedientData.registeredPopulation.get(event.getType().name()).canGenerate)
    	{
    		event.setResult(Result.DENY);
    	}
    }
}