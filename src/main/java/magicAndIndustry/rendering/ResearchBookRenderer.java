package magicAndIndustry.rendering;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ResearchBookRenderer implements IItemRenderer
{	
	
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return type != ItemRenderType.ENTITY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		
	}
}
