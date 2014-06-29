package magicAndIndustry.rendering;

import magicAndIndustry.blocks.StructureBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class StructureBlockRenderer implements ISimpleBlockRenderingHandler
{
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
		
		
		
		
		// Always returns true
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) { return true; }

	@Override
	public int getRenderId() { return 0; }
}
