package magicAndIndustry.rendering;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class RenderHandler
{
	public static RenderBlocks renderBlocks = new RenderBlocks();
	
	public static void RenderCube(double x1, double y1, double z1, double x2, double y2, double z2, Block brock, IIcon overrideTexture, int meta)
	{
		GL11.glPushMatrix();
		Tessellator tess = Tessellator.instance;
		
		GL11.glColor4f(1, 1, 1, 1);
		
		renderBlocks.setRenderBounds(x1, y1, z1, x2, y2, z2);
		
		tess.startDrawingQuads();
		
		// Side 0:
		IIcon useTexture = overrideTexture == null ? brock.getIcon(0, meta) : overrideTexture;
		tess.setNormal(0F,  -1F, 0F);
		renderBlocks.renderFaceYNeg(brock, 0, 0, 0, useTexture);
	}
	
	public static void renderFaceStrip(RenderBlocks render, double height, ForgeDirection side, Block brock, int meta)
	{
		IIcon useTexture = null; // = block.getStripTexture
		//render.setRenderBounds(p_147782_1_, p_147782_3_, p_147782_5_, p_147782_7_, p_147782_9_, p_147782_11_);
	}
	
	public static void renderFaceStrip(RenderBlocks render, double x1, double y1, double z1, double x2, double y2, double z2, Block brock, int meta)
	{
		IIcon useTexture = null;
		
		render.setRenderBounds(x1, y1, z1, x2, y2, z2);
		
		render.renderFaceYNeg(brock, 0, 0, 0, useTexture);
	}
	
	public static void renderCube(double x1, double y1, double z1, double x2, double y2, double z2, Block block, IIcon overrideTexture) {
		renderCube(x1, y1, z1, x2, y2, z2, block, overrideTexture, 0);
	}
	
	public static void renderCube(double x1, double y1, double z1, double x2, double y2, double z2, Block block, IIcon overrideTexture, int meta) 
	{
		GL11.glPushMatrix();
		Tessellator tess = Tessellator.instance;
		tess.setBrightness(1);
		GL11.glEnable(GL11.GL_LIGHTING);

		GL11.glColor4f(1, 1, 1, 1);
		//GL11.glColor4f(16777215, 16777215, 16777215, 1);

		renderBlocks.setRenderBounds(x1, y1, z1, x2, y2, z2);
		
		tess.startDrawingQuads();

		IIcon useTexture = overrideTexture != null? overrideTexture : block.getIcon(0, meta);
		tess.setNormal(0.0F, -1.0F, 0.0F);
		renderBlocks.renderFaceYNeg(block, 0, 0, 0, useTexture);

		useTexture = overrideTexture != null? overrideTexture : block.getIcon(1, meta);
		tess.setNormal(0.0F, 1.0F, 0.0F);
		renderBlocks.renderFaceYPos(block, 0, 0, 0, useTexture);

		useTexture = overrideTexture != null? overrideTexture : block.getIcon(2, meta);
		tess.setNormal(0.0F, 0.0F, -1.0F);
		renderBlocks.renderFaceZNeg(block, 0, 0, 0, useTexture);

		useTexture = overrideTexture != null? overrideTexture : block.getIcon(3, meta);
		tess.setNormal(0.0F, 0.0F, 1.0F);
		renderBlocks.renderFaceZPos(block, 0, 0, 0, useTexture);

		useTexture = overrideTexture != null? overrideTexture : block.getIcon(4, meta);
		tess.setNormal(-1.0F, 0.0F, 0.0F);
		renderBlocks.renderFaceXNeg(block, 0, 0, 0, useTexture);

		useTexture = overrideTexture != null? overrideTexture : block.getIcon(5, meta);
		tess.setNormal(1.0F, 0.0F, 0.0F);
		renderBlocks.renderFaceXPos(block, 0, 0, 0, useTexture);
		tess.draw();

		GL11.glPopMatrix();
	}
}
