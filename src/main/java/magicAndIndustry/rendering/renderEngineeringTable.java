package magicAndIndustry.rendering;


import org.lwjgl.opengl.GL11;

import magicAndIndustry.models.ModelEngineeringTable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class renderEngineeringTable extends TileEntitySpecialRenderer
{
	
	public static final ResourceLocation texture = new ResourceLocation(magicAndIndustry.Utils.ModID + ";" + "textures/models/EngineeringTable.png");
	
	private ModelEngineeringTable model;
	
	public renderEngineeringTable() 
	{
		this.model = new ModelEngineeringTable();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		GL11.glPushMatrix();
			GL11.glTranslatef((float) + 0.5F,(float) y + 1.5F, (float) z + 0.5F);
			GL11.glRotatef(180, 0F, 0F, 2F);
			
			this.bindTexture(texture);
			
			GL11.glPushMatrix();
				this.model.renderModel(1F);
			GL11.glPopMatrix();		
		
		GL11.glPopMatrix();	
	}

}
