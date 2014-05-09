package magicAndIndustry;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = Main.MODID, name="Magic and Industry", version = Main.VERSION)
public class Main
{
    public static final String MODID = "magicAndIndustry";
    public static final String VERSION = "0.0.1";
    
    @EventHandler
    public static void load(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK is "+Blocks.dirt.getUnlocalizedName());
        
        for (int i = 0; i < 20; i++)
        	System.out.println("HI!!!!!!!!!!!!");
    }
}
