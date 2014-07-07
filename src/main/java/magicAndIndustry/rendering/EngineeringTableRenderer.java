package magicAndIndustry.rendering;

import org.lwjgl.opengl.GL11;

import magicAndIndustry.rendering.models.EngineeringTableModel;
import magicAndIndustry.utils.Utils;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class EngineeringTableRenderer extends TileEntitySpecialRenderer 
{
	private static final ResourceLocation texture = new ResourceLocation(Utils.ModID + ":textures/model/engineeringTable.png");
	
	private EngineeringTableModel model;
	
	public EngineeringTableRenderer()
	{
		model = new EngineeringTableModel();

	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float cloud) 
	{
		GL11.glPushMatrix();
		
			// Do some stuff
			GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
			
			 //int meta = tile.getWorldObj().getBlockMetadata((int)x, (int)y, (int)z);
             //GL11.glPushMatrix();
             //GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
             //GL11.glPopMatrix();
			
			// 0: old, point toward north, face west
			// 1: rotate sideways
			// 3: more toward east, pointing south
			GL11.glRotatef(180F, 0F, 0F, 1F);
			
			// Get the texture
			bindTexture(texture);
			
			// Prepare to render
			GL11.glPushMatrix();
			
				// 1/16F -> pixel scaling.
				// 1F == 1 block, 1/16 = pixels
				model.renderModel(0.0625F);
				
			GL11.glPopMatrix();
			
		GL11.glPopMatrix();
	}

}
