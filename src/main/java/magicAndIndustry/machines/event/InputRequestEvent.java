package magicAndIndustry.machines.event;

import net.minecraft.item.ItemStack;

/**
 * Called by machines that take inputs when they empty and periodically while empty.
 * @see SendItem
 */
public class InputRequestEvent
{
	private ItemStack inputStack;
	
	public void GiveItemStack(ItemStack stack)
	{
		inputStack = stack;
	}
	
	public boolean hasStack() { return inputStack != null; }
	
	InputRequestEvent()
	{
		inputStack = null;
	}
}
