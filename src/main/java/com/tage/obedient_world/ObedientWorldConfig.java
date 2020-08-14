package com.tage.obedient_world;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ObedientWorldConfig 
{
	public static Configuration config;
	
	@LangKey("nocaves.config.general")
    @Config(modid = ObedientWorld.MODID, category = "general")
    public static class GENERAL 
    {
        @Comment("Enable Terrain Event Controls")
        public static boolean terrain = false;
        @Comment("Enable Structure Event Controls")
        public static boolean structures = false;
        @Comment("Enable Population Event Controls")
        public static boolean population = false;
        @Comment("Enable Decoration Event Controls")
        public static boolean decoration = false;
        @Comment("Enable Entity Event Controls")
        public static boolean entities = false;
        @Comment("Enable Ore Override Controls")
        public static boolean ores = false;
        @Comment("Enable Flower Patch Controls")
        public static boolean flowers = false;
        @Comment("Enable Debugging")
        public static boolean debug = false;
    }

    @LangKey("nocaves.config.terrain")
    @Config(modid = ObedientWorld.MODID, category = "terrain")
    public static class TERRAIN 
    {
    	public static final TerrainEvent terrain_events = new TerrainEvent();

    	public static class TerrainEvent 
    	{
    		@Config.Comment("Should caves generate?")
    		public boolean Caves = true;
    		@Config.Comment("Should ravines generate?")
    		public boolean Ravines = true;
    		@Config.Comment("Should end cities generate?")
    		public boolean EndCity = true;
    		@Config.Comment("Should mineshafts generate?")
    		public boolean Mineshaft = true;
    		@Config.Comment("Should nether bridges generate?")
    		public boolean NetherBridge = true;
    		@Config.Comment("Should nether caves generate?")
    		public boolean NetherCave = true;
    		@Config.Comment("Should ocean monuments generate?")
    		public boolean OceanMonument = true;
    		@Config.Comment("Should scattered features generate?")
    		public boolean ScatteredFeature = true;
    		@Config.Comment("Should stongholds generate?")
    		public boolean Stronghold = true;
    		@Config.Comment("Should villages generate?")
    		public boolean Village = true;
    		@Config.Comment("Should woodland mansions generate?")
    		public boolean WoodlandMansion = true;
    	}
    	
    	public static final PopulationEvent population_events = new PopulationEvent();

    	public static class PopulationEvent 
    	{
    		@Config.Comment("Should lava generate?")
    		public boolean Lava = true;
    		@Config.Comment("Should lakes generate?")
    		public boolean Lake = true;
    		@Config.Comment("Should animals generate?")
    		public boolean Animals = true;
    		@Config.Comment("Should dungeons generate?")
    		public boolean Dungeon = true;
    		@Config.Comment("Should fires generate?")
    		public boolean Fire = true;
    		@Config.Comment("Should glowstone generate?")
    		public boolean Glowstone = true;
    		@Config.Comment("Should ice generate?")
    		public boolean Ice = true;
    		@Config.Comment("Should nether lava generate?")
    		public boolean NetherLava = true;
    		@Config.Comment("Should nether lava 2 generate?")
    		public boolean NetherLava2 = true;
    		@Config.Comment("Should nether magma generate?")
    		public boolean NetherMagma = true;
    	}
    	
    	public static final DecorationEvent decoration_events = new DecorationEvent();
    	
    	public static class DecorationEvent 
    	{
    		@Config.Comment("Should big mushrooms generate?")
    		public boolean BigShroom = true;
    		@Config.Comment("Should cacti generate?")
    		public boolean Cactus = true;
    		@Config.Comment("Should clay generate?")
    		public boolean Clay = true;
    		@Config.Comment("Should dead bushes generate?")
    		public boolean DeadBush = true;
    		@Config.Comment("Should fires generate?")
    		public boolean Fire = true;
    		@Config.Comment("Should desert wells generate?")
    		public boolean DesertWell = true;
    		@Config.Comment("Should flowers generate?")
    		public boolean Flowers = true;
    		@Config.Comment("Should fossils generate?")
    		public boolean Fossil = true;
    		@Config.Comment("Should grass generate?")
    		public boolean Grass = true;
    		@Config.Comment("Should ice generate?")
    		public boolean Ice = true;
    		@Config.Comment("Should lava lakes generate?")
    		public boolean LakeLava = true;
    		@Config.Comment("Should water lakes generate?")
    		public boolean LakeWater = true;
    		@Config.Comment("Should lilypads generate?")
    		public boolean Lilypad = true;
    		@Config.Comment("Should pumpkins generate?")
    		public boolean Pumpkin = true;
    		@Config.Comment("Should sugarcanes generate?")
    		public boolean Reed = true;
    		@Config.Comment("Should rocks generate?")
    		public boolean Rock = true;
    		@Config.Comment("Should sand generate?")
    		public boolean Sand = true;
    		@Config.Comment("Should sand pass generate?")
    		public boolean SandPass2 = true;
    		@Config.Comment("Should mushrooms generate?")
    		public boolean Shroom = true;
    		@Config.Comment("Should trees generate?")
    		public boolean Tree = true;
    	}
    	
    	public static final ScatteredEvent scattered_events = new ScatteredEvent();
    	
    	public static class ScatteredEvent
    	{
    		@Config.Comment("Should witch huts generate? Requires terrainevent.scatteredfeatures to be true")
    		public boolean WitchHut = true;
    		@Config.Comment("Should igloos generate? Requires terrainevent.scatteredfeatures to be true")
    		public boolean Igloo = true;
    		@Config.Comment("Should desert pyramids generate? Requires terrainevent.scatteredfeatures to be true")
    		public boolean DesertPyramid = true;
    		@Config.Comment("Should jungle temples generate? Requires terrainevent.scatteredfeatures to be true")
    		public boolean JungleTemple = true;
    	}
    }
    
	@Mod.EventBusSubscriber(modid = ObedientWorld.MODID)
	private static class EventHandler
	{
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if (event.getModID().equals(ObedientWorld.MODID))
			{
				ConfigManager.sync(ObedientWorld.MODID, Config.Type.INSTANCE);
				if (config.hasChanged())
					config.save();
			}
		}
	}
}