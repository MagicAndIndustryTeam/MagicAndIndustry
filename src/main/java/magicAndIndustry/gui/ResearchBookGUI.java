package magicAndIndustry.gui;

import magicAndIndustry.utils.Utils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
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
	//private ResourceLocation textureLeft, textureRight;
	
	/** The dimensions of the book background texture. */
	//public static final int textureX = 220, textureY = 180;
	private static final int bookHeight = 165;
	
	/** Dimensions of page textures. */
	private static final int PAGE_HEIGHT = 208, PAGE_WIDTH = 144;
	
	private static final int leftOffset = 112; // 256 - page width
	
	private ResourceLocation textureLeft, textureRight;
	
	private EntityPlayer player;
	
	public ResearchBookGUI(EntityPlayer player)
	{
		textureLeft = new ResourceLocation(Utils.ModID, "gui/book_left.png");
		textureRight = new ResourceLocation(Utils.ModID, "gui/book_right.png");
		texture = new ResourceLocation(Utils.ModID, "gui/big_gui.png"); // "gui/book_physics.png"); // "/gui/bigBookScience.png");
		this.player = player;
		//player.getItemInUse().stackTagCompound.setBoolean("Open", true);
		//textureRight = new ResourceLocation(Utils.ModID, "/gui/strech_bookRight.png");
		//textureLeft = new ResourceLocation(Utils.ModID,  "/gui/strech_bookLeft.png");
	}
	
	@Override
	protected void keyTyped(char value, int key)
	{
		// E closes gui
		if (key == Keyboard.KEY_E)
		{
            mc.displayGuiScreen((GuiScreen)null);
            mc.setIngameFocus();
		}
		super.keyTyped(value, key);
	}
	
	@Override
	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
		
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		//
		// Two-page drawing code from SlimeKnights/Mantle's manual drawing code (used in Slimeknights/Tinker's Construct).
		// Thank you SlimeKnights!
		//
		
		mc.renderEngine.bindTexture(textureRight);
		int localWidth = width / 2;
		int localHeight = (height - PAGE_HEIGHT) / 2;
		
		drawTexturedModalRect(localWidth, localHeight, 0, 0, PAGE_WIDTH, PAGE_HEIGHT);
		
		mc.renderEngine.bindTexture(textureLeft);
		localWidth -= PAGE_WIDTH;
		drawTexturedModalRect(localWidth, localHeight, leftOffset, 0, PAGE_WIDTH, PAGE_HEIGHT);
		
		
		fontRendererObj.drawString("Engineering Book", localWidth + 16, localHeight + 5, 0, false);
		localHeight += 7;
		
		for (int i = 1; i < 9; i++)
		{
			localHeight += 11;
			fontRendererObj.drawString("Chapter " + i, localWidth + 16, localHeight, 0, false);
		}
		
		localHeight--;
		for (int j = 1; j < 5; j++)
	 	{
			localHeight += 10;
			fontRendererObj.drawString("Section " + j, localWidth + 16 + 6, localHeight, 0, false);
		}
		
		for (int k = 0; k < 6; k++)
		{
			localHeight += 10;
			fontRendererObj.drawString("Article", localWidth + 16 + 6 + 6, localHeight, 0, false);
		}
		
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
