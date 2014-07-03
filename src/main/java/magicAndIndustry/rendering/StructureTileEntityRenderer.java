package magicAndIndustry.rendering;

import magicAndIndustry.blocks.StructureBlock;
import magicAndIndustry.tileEntity.StructureEntity;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class StructureTileEntityRenderer extends TileEntitySpecialRenderer
{
	private RenderBlocks renderBlocks = new RenderBlocks();
	public static boolean hasGoneYet = false;
	
	// Started out 0.475, 0.501
	private static final double smaller = 0.438D, larger = 0.501D;
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float whoKnows) 
	{
		/*
		bindTexture(TextureMap.locationBlocksTexture);
		StructureTileEntity structure = (StructureTileEntity)entity;
		StructureBlock brock = (StructureBlock)entity.getWorldObj().getBlock(entity.xCoord, entity.yCoord, entity.zCoord);
		
		if (brock == null || structure == null)
		{
			return;
		}
		
		// This is like a thing you gotta do
		GL11.glPushMatrix();
		// I dunno
		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
		
		//GL11.glDisable(GL11.GL_LIGHTING);
		
		// okay let's see what the heck is going on here
		boolean structEast = structure.isNeighbor(ForgeDirection.EAST);
		boolean structWest = structure.isNeighbor(ForgeDirection.WEST);
		boolean structNorth = structure.isNeighbor(ForgeDirection.NORTH);
		boolean sructSouth = structure.isNeighbor(ForgeDirection.SOUTH);
		boolean structUp = structure.isNeighbor(ForgeDirection.UP);
		boolean structDown = structure.isNeighbor(ForgeDirection.DOWN);
		
		if (!structEast) {
			if (!structNorth) RenderHandler.renderCube(smaller, -larger, -larger, larger, larger, -smaller, brock, brock.multi_side);
			if (!sructSouth) RenderHandler.renderCube(smaller, -larger, smaller, larger, larger, larger, brock, brock.multi_side);
			if (!structDown) RenderHandler.renderCube(smaller, -larger, -larger, larger, -smaller, larger, brock, brock.multi_top);
			if (!structUp) RenderHandler.renderCube(smaller, smaller, -larger, larger, larger, larger, brock, brock.multi_top);
		}
		if (!structWest) {
			if (!structNorth) RenderHandler.renderCube(-larger, -larger, -larger, -smaller, larger, -smaller, brock, brock.multi_side);
			if (!sructSouth) RenderHandler.renderCube(-larger, -larger, smaller, -smaller, larger, larger, brock, brock.multi_side);
			if (!structDown) RenderHandler.renderCube(-larger, -larger, -larger, -smaller, -smaller, larger, brock, brock.multi_top);
			if (!structUp) RenderHandler.renderCube(-larger, smaller, -larger, -smaller, larger, larger, brock, brock.multi_top);

		}
		if (!structNorth) {
			if (!structUp) RenderHandler.renderCube(-larger, smaller, -larger, larger, larger, -smaller, brock, brock.multi_top);
			if (!structDown) RenderHandler.renderCube(-larger, -larger, -larger, larger, -smaller, -smaller, brock, brock.multi_top);
		}

		if (!sructSouth) {
			if (!structUp) RenderHandler.renderCube(-larger, smaller, smaller, larger, larger, larger, brock, brock.multi_top);
			if (!structDown) RenderHandler.renderCube(-larger, -larger, smaller, larger, -smaller, larger, brock, brock.multi_top);
		}
		
		//GL11.glEnable(GL11.GL_LIGHTING);
		
		hasGoneYet = true;
		
		GL11.glPopMatrix();
		*/
		
	}
}
