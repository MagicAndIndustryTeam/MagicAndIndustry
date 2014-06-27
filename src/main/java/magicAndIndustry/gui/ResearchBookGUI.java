package magicAndIndustry.gui;

import magicAndIndustry.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * GUI for all research books. Call constructor with bookType.
 * Not API compatible.
 */
@SideOnly(Side.CLIENT)
public class ResearchBookGUI extends GuiScreen
{
	/** The current texture */
	private ResourceLocation texture;
	private ResourceLocation textureLeft, textureRight;
	
	/** The dimensions of the book background texture. */
	public static final int textureX = 220, textureY = 180;
	
	private EntityPlayer player;
	
	public ResearchBookGUI(EntityPlayer player)
	{
		texture = new ResourceLocation(Utils.ModID, "/gui/bigBookScience.png");
		this.player = player;
		//player.getItemInUse().stackTagCompound.setBoolean("Open", true);
		textureRight = new ResourceLocation(Utils.ModID, "/gui/strech_bookRight.png");
		textureLeft = new ResourceLocation(Utils.ModID,  "/gui/strech_bookLeft.png");
	}
	
	@Override
	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
		
		GL11.glColor4f(1F, 1F, 1F, 1F);
		//mc.renderEngine.bindTexture(texture);
		mc.renderEngine.bindTexture(textureRight);
		int localWidth = width / 2;
		byte localHeight = 8; // from screen???
		drawTexturedModalRect(localWidth, localHeight, 0, 0, 225, 199);
		
		
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.renderEngine.bindTexture(textureLeft);
		localWidth -= 255; // right width?
		drawTexturedModalRect(localWidth, 8, 256 - 225, 0, 225, 214);
		
		/*
		int posX = (width - textureX) / 2, posY = (height - textureY) / 2;
		
		// Texture temp set to max size
		drawTexturedModalRect(posX, posY, 0, 0, 256, 166);
		*/
		fontRendererObj.drawStringWithShadow("This is a booooook!!!!!", 44, 32, 0);
		
		super.drawScreen(x, y, f);
	}
	
	@Override
	public void onGuiClosed()
	{
		//player.getItemInUse().stackTagCompound = null;
	}
	
	// Sure?
	@Override
	public boolean doesGuiPauseGame() { return false; }
}
