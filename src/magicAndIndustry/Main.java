package magicAndIndustry;

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
	public static SidedProxy proxy;
	
	@EventHandler
	public static void PreLoad(FMLInitializationEvent event)
	{
		// Load the mod's items and blocks
		
	}
	
    @EventHandler
    public static void load(FMLInitializationEvent event)
    {
    	// Build research trees, mechanics, recepies, etc.
    	
        for (int i = 0; i < 20; i++)
        	System.out.println("HI!!!!!!!!!!!!");
    }
    
    @EventHandler
    public static void PostInit(FMLInitializationEvent event)
    {
    	// (name may be wrong) Load API/mod interactivity
    	
    	// This is gonna be empty for a while.
    }
}
