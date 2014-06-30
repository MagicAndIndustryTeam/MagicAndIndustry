package magicAndIndustry.rendering;

import java.util.ArrayList;

import magicAndIndustry.BlockPosition;
import magicAndIndustry.blocks.StructureBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class StructureBlockRenderer implements ISimpleBlockRenderingHandler
{
	private int renderID;
	
	public StructureBlockRenderer()
	{
		renderID = RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) 
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) 
	{
		// Some code from MinefactoryReloaded, 
		// https://github.com/powercrystals/MineFactoryReloaded/blob/master/src/powercrystals/minefactoryreloaded/render/block/FactoryGlassRenderer.java
		
		// Some code from OpenBlocks and OpenLib,
		// [openblocks on github]
		// [open blocks core on github]
		
		// Thank you powercrystals and the openblocks team for figuring this out and being open source!
		
		// [MFR src]
		if (renderer.hasOverrideBlockTexture())
		{ 
			// [MFR] usually: block is being broken
			renderer.renderFaceYNeg(block, x, y, z, null);
			renderer.renderFaceYPos(block, x, y, z, null);
			renderer.renderFaceZNeg(block, x, y, z, null);
			renderer.renderFaceZPos(block, x, y, z, null);
			renderer.renderFaceXNeg(block, x, y, z, null);
			renderer.renderFaceXPos(block, x, y, z, null);
			return true;
		}
		
		// Get the StructureBlock from the argument
		StructureBlock struct = (StructureBlock)block;
		// Get the block's metadata: 1=structure, 2=striped.
		int meta = world.getBlockMetadata(x, y, z);
		// Keep the world height for range checks
		int worldHeight = world.getHeight();
		// Using an instance is sooooooooo much cooler than static variables
		Tessellator tess = Tessellator.instance;
		
		// [MFR] Standard lighting procedures
		tess.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
		int color = block.getRenderColor(meta);
		float red = (color >> 16 & 255) / 255F;
		float green = (color >> 8 & 255) / 255F;
		float blue = (color & 255) / 255F;
		if (EntityRenderer.anaglyphEnable)
		{
			red = ((red * 30F) + (green * 59F) + (blue * 11F));
			green = ((red * 30F) + (green * 70F)) / 100F;
			blue = ((red * 30F) + (blue * 70F)) / 100F;
		}
		
		// Okay, now check all six faces and render.
		BlockPosition original = new BlockPosition(x, y, z);
		for (ForgeDirection currSide : ForgeDirection.VALID_DIRECTIONS)
		{
			BlockPosition neighbor = original.move(currSide);

			if (renderer.renderAllFaces || neighbor.isValid(world) 
					|| block.shouldSideBeRendered(world, neighbor.x, neighbor.y, neighbor.z, currSide.ordinal()))
			{
				// Render the default stuff
				// TODO set texture
				tess.setColorOpaque_F(red, green, blue);
				tess.addVertexWithUV(x, y, z, x+1, y+1);
				
				Block neighBlock = world.getBlock(neighbor.x, neighbor.y, neighbor.z);
				if (!(neighBlock instanceof IConnectedTexture)) continue; // TODO draw texture
				IConnectedTexture conTextBlock = (IConnectedTexture)neighBlock;
				
				IIcon regular = struct.getBaseTexture(currSide.ordinal(), meta), 
						connected = struct.getWallsTexture();
				
				ArrayList<ForgeDirection> neighborChecks = new ArrayList<ForgeDirection>(4);
				for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
					if (dir != currSide && dir != currSide.getOpposite()) neighborChecks.add(dir);
				
				for (ForgeDirection neighborDir : neighborChecks)
				{
					// They have the same textures on that side
					if (!struct.getTextureID(neighborDir.ordinal(), meta).equals(conTextBlock.getTextureID(neighborDir.ordinal(), 
							world.getBlockMetadata(neighbor.x, neighbor.y, neighbor.z))))
					{
						double startX, startY, endX, endY;
						
						// Set the combined texture
						// Render that

					}
					// else: they have the same textures, do nothing.
				}
				// TODO corners
			}
		}
		
		
		// Always returns true
		return true;
	}
	
	private void drawBlock() { }

	@Override
	public boolean shouldRender3DInInventory(int modelId) { return true; }

	@Override
	public int getRenderId() { return renderID; }
}
