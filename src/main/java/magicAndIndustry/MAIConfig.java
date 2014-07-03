package magicAndIndustry;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

@SuppressWarnings("unused") // loadBool
public final class MAIConfig
{
	private static Configuration config;
	
	/**
	 * DISables "DOTS!!!" on the engineering table gui, easter eggs, some references and tooltips.
	 * Ignored serverside, defaults to false.
	 * Mods using the API are encouraged to check this if they need.
	 */
	public static boolean seriousMode = false;
	/**
	 * Enables sending research in packets after the player has joined to take up less time when logging in.
	 * Prevents them from opening the book during this time. 
	 * Required serverside, defaults to false.
	 */
	public static boolean delayResearchSending = false;
	/**
	 * Enables version check.
	 * Separate client/server. Defaults t false
	 */
	public static boolean versionCheckEnabled = false;
	
	public static void loadConfig(File file)
	{
		config = new Configuration(file);
		config.load();
		
		versionCheckEnabled = loadBool("versionCheck",
			"The version check gets TODO OUR GITHUB, checks for a new version, and downloads it. Many, many thanks to Vaskii's inspirational, revolutionary clicky code!" + 
			" Please do not enable by default in modpacks if they don't keep up to date with all mods all the time. Defaults to false.", false);
		
		delayResearchSending = loadBool("delayResearch", 
			"When you first join a world on multiplayer, we've gotta send you all of your completed research. This is a lot; it could keep you on the \"Logging in...\" screen for a while." + 
			"So, with this enabled, research is sent to you in pieces after you join so you can see the world sooner. Only effective serverside in multiplayer, defaults to false.", false);
		
		seriousMode = loadBool("seriousMode", 
				"For those of us who prefer to ruin others' fun, there is SERIOUS MODE, Not Suitable For Children. Obviously, this defaults to false.", false);
	}
	
	private static int loadInt(String name, String info, int deefault)
	{
		Property propp = config.get(Configuration.CATEGORY_GENERAL, name, deefault);
		propp.comment = info; return propp.getInt(deefault);
	}
	private static boolean loadBool(String name, String info, boolean deefault)
	{
		Property propp = config.get(Configuration.CATEGORY_GENERAL, name, deefault);
		propp.comment = info; return propp.getBoolean(deefault);
	}
}
