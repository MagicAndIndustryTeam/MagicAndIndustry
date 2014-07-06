package magicAndIndustry.gui;

import magicAndIndustry.MAIConfig;
import magicAndIndustry.utils.Utils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;

public class ConfigGUI extends GuiConfig
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConfigGUI(GuiScreen screen)
	{
		// TODO create list of IConfigElements to have multiple categories
		super(screen, 
				new ConfigElement(MAIConfig.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				Utils.ModID, false, false, GuiConfig.getAbridgedConfigPath(MAIConfig.config.toString()));
	}
}
