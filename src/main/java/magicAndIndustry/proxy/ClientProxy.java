package magicAndIndustry.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.rendering.EngineeringTableRenderer;
import magicAndIndustry.tileEntity.EngineeringTableEntity;

/**
 * This class's code is executed clientside.
 */
public class ClientProxy extends Proxy
{
	@Override
	public void preInit()
	{
		// Get a render ID
		MagicAndIndustry.renderID = RenderingRegistry.getNextAvailableRenderId();
		
		//EngineeringTable
		TileEntitySpecialRenderer render = new EngineeringTableRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(EngineeringTableEntity.class, render);
		
		// Structure block
		//ClientRegistry.bindTileEntitySpecialRenderer(StructureTileEntity.class, new StructureTileEntityRenderer());
	}
}
