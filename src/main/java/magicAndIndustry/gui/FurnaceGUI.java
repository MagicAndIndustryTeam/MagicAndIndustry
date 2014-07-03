package magicAndIndustry.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import magicAndIndustry.tileEntity.FurnaceCoreEntity;
import magicAndIndustry.utils.Utils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FurnaceGUI extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation(Utils.ModID, "/gui/cobbleFurnace.png");
	private FurnaceCoreEntity furnace;
	
	public FurnaceGUI(InventoryPlayer player, FurnaceCoreEntity furnaceEntity) 
	{
		super(new FurnaceContainer(player, furnaceEntity));
		
		// Size defaults to 176, 166.
		//xSize = 176;
		//ySize = 154;
		furnace = furnaceEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		// Gets the renamed inventory name or translates container.cobbleFuranceGui 
		String name = furnace.hasCustomInventoryName() ? furnace.getInventoryName() : StatCollector.translateToLocal(furnace.getInventoryName());
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		//fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) 
	{
		// You know, colors!
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		// Get the texture
		mc.getTextureManager().bindTexture(texture);
		
		// Divide and conquer
		int x = (width - xSize) / 2, y = (height - ySize) / 2;
		
		// Draw the furnace gui part.
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		int scaled;
		
		// If the furnace is cooking
		if (furnace.fuelBurnTime > 0)
		{
			// Get the height of the flame
			scaled = furnace.getScaledFuelBurnTime(12);
			// Draw in the flame - coords are from outside of the gui part of the image of a flame image.
			drawTexturedModalRect(x + 56, y + 36 + 12 - scaled, 176, 12 - scaled, 14, scaled + 2);
		}
		// Get width of the item cook time
		scaled = furnace.getScaledItemCookTime(24);
		// Paste in the arrow from outside of the gui.
		drawTexturedModalRect(x + 79, y + 34, 176, 14, scaled + 1, 16);
	}
}
