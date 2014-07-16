package magicAndIndustry.machines.structure;

import net.minecraft.item.ItemStack;

public class CraftingCompleteEvent extends MachineEvent 
{
	private ItemStack theStack;
	
	public void acceptItem()
	{
		
	}
	
	public ItemStack getStack()
	{
		return theStack;
	}
}
