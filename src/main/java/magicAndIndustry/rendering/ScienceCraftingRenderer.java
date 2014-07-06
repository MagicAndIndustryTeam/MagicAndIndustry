package magicAndIndustry.rendering;

import org.lwjgl.opengl.GL11;

import magicAndIndustry.rendering.models.ScienceCraftingTableModel;
import magicAndIndustry.utils.Utils;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class ScienceCraftingRenderer extends TileEntitySpecialRenderer 
{
	private static final ResourceLocation texture = new ResourceLocation(Utils.ModID, "textures/model/scienceCraftingTable.png");
	
	private ScienceCraftingTableModel model;
	
	public ScienceCraftingRenderer()
	{
		model = new ScienceCraftingTableModel();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity ent, double x, double y, double z, float f) 
	{
		GL11.glPushMatrix();
		
		// Do some stuff
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glRotatef(180, 0F, 0F, 1F);
		
		// Get the texture
		bindTexture(texture);
		
		// Prepare to render
		GL11.glPushMatrix();
		
		// TODO get rotation from metadata, check for book.
		
			// 1/16F -> pixel scaling.
			// 1F == 1 block, 1/16 = pixels
			model.renderModel(0.0625F);
			
		GL11.glPopMatrix();
		
	GL11.glPopMatrix();		
	}

}
