package magicAndIndustry;

import java.util.Set;

import magicAndIndustry.gui.ConfigGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.client.IModGuiFactory;

/**
 * Handles client side config gui.
 */
public class IHateFactoriesTheyAreIcky implements IModGuiFactory
{

	@Override
	public void initialize(Minecraft minecraftInstance)
	{
	}

	@Override
	// This is the only one used ATM
	public Class<? extends GuiScreen> mainConfigGuiClass()
	{
		return ConfigGUI.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
	{
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
	{
		return null;
	}

}
