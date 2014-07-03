package magicAndIndustry.gui;

import java.util.Random;

import magicAndIndustry.tileEntity.EngineeringTableEntity;
import magicAndIndustry.utils.Textures;
import magicAndIndustry.utils.Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EngineeringTableGUI extends GuiContainer 
{
	private static final ResourceLocation texture = new ResourceLocation(Textures.gui("engineeringTable"));
	private EngineeringTableEntity table;
	private Random rand; // just in case
	private int[][] numbers; private int textNum; private int counter = 0;
	
	public EngineeringTableGUI(InventoryPlayer player, EngineeringTableEntity tableEntity)
	{
		super(new EngineeringTableContainer(player, tableEntity));
		xSize = 176; ySize = 222;
		table = tableEntity;
		rand = new Random();
		numbers = new int[8][8];
		for (int i = 0; i < numbers.length; i++)
			for (int j = 0; j < numbers.length; j++)
				numbers[i][j] = rand.nextInt(7);
		textNum = 0;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		String name = Utils.Translate("container.engineeringTable");
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		
		String text = "My text code stopped working.";
		if (textNum < 10) 
		{
			if (counter >= (textNum < 4 ? 120 : 100)) { textNum++; counter = 0; } 
			else counter++;
		}
		switch (textNum)
		{
			case 0: text = "This GUI is too cramped, Noah."; break;
			case 1: text = "Please make the dots bigger."; break;
			case 2: text = "Would fewer slots be so bad?"; break;
			case 3: text = "Think of all the space!"; break;
			case 4: text = "I spent a lot of time on this."; break;
			case 5: text = "And it looks pretty cool!"; break;
			case 6: text = "Don't worry, Noah."; break;
			case 7: text = "This is a breakthrough!"; break;
			case 8: text = "I'll be able to do animations"; break;
			case 9: text = "in the books, for example."; break;
			case 10: text = "Yay random dots!!!!!!!!!!!!!!!!"; break;
		}
		fontRendererObj.drawString(text, 8, 128, 4210752);
		GL11.glColor4f(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(texture);
		
		// maxX is legendary
		int startX, maxX;
		for (int iy = 0; iy < 7; iy++)
		{
			// Row even/odd affects offset and count of thingies.
			if (iy % 2 == 0) { startX = 21; maxX = 6; }
			else { startX = 11; maxX = 7; }
			
			for (int ix = 0; ix < maxX; ix++)
			{
				drawDotOverlay(numbers[iy][ix], startX + ix * 20,  24 + iy * 10);
			}
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) 
	{
		GL11.glColor4f(1, 1, 1, 1);
		
		mc.getTextureManager().bindTexture(texture);
		
		int x = (width - xSize) / 2, y = (height - ySize) / 2;
		
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
	private void drawDotOverlay(int dotType, int x, int y)
	{
		int drawX = 176, drawY = 115 + dotType * 4;
		
		drawTexturedModalRect(x, y, drawX, drawY, 4, 4);
	}
}
