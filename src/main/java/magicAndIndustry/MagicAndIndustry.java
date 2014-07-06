package magicAndIndustry;

import magicAndIndustry.blocks.BlockRegistrar;
import magicAndIndustry.gui.GuiHandler;
import magicAndIndustry.items.CraftingRegistrar;
import magicAndIndustry.items.ItemRegistrar;
import magicAndIndustry.items.MAITab;
import magicAndIndustry.machines.MachineRegistrar;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.machines.structure.MachineStructureRegistrar;
import magicAndIndustry.proxy.Proxy;
import magicAndIndustry.utils.BlockPosition;
import magicAndIndustry.utils.Utils;
import net.minecraft.creativetab.CreativeTabs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Utils.ModID, version = Utils.Version, name = "Magic and Industry", guiFactory = "magicAndIndustry.IHateFactoriesTheyAreIcky")
public class MagicAndIndustry
{
	@SidedProxy(clientSide = "magicAndIndustry.proxy.ClientProxy", serverSide = "magicAndIndustry.proxy.ServerProxy")
	public static Proxy proxy;
	
	@Instance
	public static MagicAndIndustry instance;
	
	/** The MagicAndIndustry creative tab. */
	public static CreativeTabs tab = new MAITab();
	
	/** The rendering ID of the mod */
	public static int renderID;
	
	/** Mod logger */
	public static final Logger logger = LogManager.getLogger(Utils.ModID);
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		MAIConfig.loadConfig(event.getSuggestedConfigurationFile());
		if (!MAIConfig.seriousMode)
		{
			logger.info("Magic and Industry, what are we going to do tonight?");
			logger.info("Magic and Industry, we're going to take over the Minecraft!");
		}
		MachineTier.cry(); // tears
		MachineStructureRegistrar.assimilate();    	
		MachineRegistrar.registrate();
		
		// Each of the register classes does their registration.
		BlockRegistrar.RegisterBlocksForKnocks();
		ItemRegistrar.RegisterDemItems();
		CraftingRegistrar.RegisterCraftification();
		
		FMLCommonHandler.instance().bus().register(new MAIConfig());
		
		// If this is the client, it'll do textures.
		proxy.preInit();
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	// More GUI stuff
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    	


    	// TODO put database handling here
    }
}
