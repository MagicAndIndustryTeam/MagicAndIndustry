package magicAndIndustry.machines.event;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

/**
 * Called by machine cores that output items to all of their structures.
 */
public class ItemOutputEvent
{
	/**
	 * The itemstacks that the machine is outputting.
	 * Take from them to help handle this event.
	 */
	public ArrayList<ItemStack> items;
	
	/**
	 * Returns true if someone has taken the item (used by machine cores).
	 */
	public boolean isHandled() { return items.isEmpty(); }

}
