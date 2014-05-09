package magicAndIndustry;

import magicAndIndustry.Blocks.BlockRegistrar;
import magicAndIndustry.Proxy.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Utils.ModID, name="Magic and Industry", version = Utils.Version)
public class Main
{
	@SidedProxy(clientSide="magicAndIndustry.Proxy.ClientProxy", serverSide="magicAndIndustry.Proxy.ServerProxy")
	public static ServerProxy proxy;
	
	@EventHandler
	public static void PreLoad(FMLInitializationEvent event)
	{
		// Load the mod's items and blocks
		BlockRegistrar.RegisterBlocksForKnocks();
	}
	
    @EventHandler
    public static void load(FMLInitializationEvent event)
    {
    	// Build research trees, mechanics, recipes, etc.
    	
        for (int i = 0; i < 20; i++)
        	System.out.println("HI!1!!!11!1!!!1!11!!!1");
    }
    
    @EventHandler
    public static void PostInit(FMLInitializationEvent event)
    {
    	// (name may be wrong) Load API/mod interactivity
    	
    	// This is gonna be empty for a while.
    }
}
