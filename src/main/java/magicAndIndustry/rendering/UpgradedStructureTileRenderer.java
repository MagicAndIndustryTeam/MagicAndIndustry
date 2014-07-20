package magicAndIndustry.rendering;

import magicAndIndustry.tileEntity.base.StructureUpgradeEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class UpgradedStructureTileRenderer extends TileEntitySpecialRenderer
{
	private static ResourceLocation texture = new ResourceLocation("");
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		StructureUpgradeEntity structureEntity = (StructureUpgradeEntity)tile;
		
		GL11.glPushMatrix();
		
			// TODO custom render code
			//IIcon icon = structureEntity.getBlockOverlay();
			bindTexture(texture);
			
			
			//RenderUtils.renderBlocks.renderBlockUsingTexture(tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.xCoord), tile.xCoord, tile.yCoord, tile.zCoord, icon);
		
		GL11.glPopMatrix();
	}
}
