package magicAndIndustry.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.rendering.EngineeringTableRenderer;
import magicAndIndustry.rendering.ScienceCraftingTableEntityRenderer;
import magicAndIndustry.tileEntity.EngineeringTableEntity;
import magicAndIndustry.tileEntity.ScienceCraftingTableEntity;

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
		
		// Research Tables
		ClientRegistry.bindTileEntitySpecialRenderer(EngineeringTableEntity.class, new EngineeringTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(ScienceCraftingTableEntity.class, new ScienceCraftingTableEntityRenderer());
		
		// Structure block
		//ClientRegistry.bindTileEntitySpecialRenderer(StructureTileEntity.class, new StructureTileEntityRenderer());
	}
}
