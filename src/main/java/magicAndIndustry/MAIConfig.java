package magicAndIndustry;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import magicAndIndustry.utils.Utils;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class MAIConfig
{
	public static Configuration config;
	
	/**
	 * DISables "DOTS!!!" on the engineering table gui, easter eggs, some references and tooltips.
	 * Ignored serverside, defaults to false.
	 * Mods using the API are encouraged to check this if they need.
	 */
	public static boolean seriousMode = false;
	private static final String seriousComment = "For those of us who prefer to ruin others' fun, there is SERIOUS MODE, Not Suitable For Children. Obviously, this defaults to false.";
	/**
	 * Enables sending research in packets after the player has joined to take up less time when logging in.
	 * Prevents them from opening the book during this time. 
	 * Required serverside, defaults to false.
	 */
	public static boolean delayResearchSending = false;
	private final static String delayResearchComment = "When you first join a world on multiplayer, we've gotta send you all of your completed research. This is a lot; it could keep you on the \"Logging in...\" screen for a while." + 
			"So, with this enabled, research is sent to you in pieces after you join so you can see the world sooner. Only effective serverside in multiplayer, defaults to false.";
	/**
	 * Enables version check.
	 * Separate client/server. Defaults t false
	 */
	public static boolean versionCheckEnabled = false;
	private final static String versionCheckComment = "The version check gets TODO OUR GITHUB, checks for a new version, and downloads it. Many, many thanks to Vaskii's inspirational, revolutionary clicky code!" + 
		" Please do not enable by default in modpacks if they don't keep up to date with all mods all the time. Defaults to false.";
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent args)
	{
		if (args.modID.equals(Utils.ModID))
			syncConfig();
	}
	
	public static void loadConfig(File file)
	{
		if (config == null)
			config = new Configuration(file);
		config.load();
		
		syncConfig();
	}
	
	private static void syncConfig()
	{
		versionCheckEnabled = loadBool("versionCheck", versionCheckComment, false);
		delayResearchSending = loadBool("delayResearch", delayResearchComment, false);
		seriousMode = loadBool("seriousMode", seriousComment, false);
		
		if (config.hasChanged()) config.save();
	}
	
	private static int loadInt(String name, String info, int deefault)
	{
		return config.get(Configuration.CATEGORY_GENERAL, name, deefault, info).getInt(deefault);
	}
	
	private static boolean loadBool(String name, String info, boolean deefault)
	{
		return config.get(Configuration.CATEGORY_GENERAL, name, deefault, info).getBoolean(deefault);
	}
}
