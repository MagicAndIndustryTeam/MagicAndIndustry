package magicAndIndustry.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTUtils 
{
	/**
	 * Creates an NBTTagCompoundList from an ItemStack array for saving to NBT.
	 * @param items
	 * @return
	 */
	public static NBTTagList getItemCollectionTag(ItemStack[] items)
	{
		// Create a list and copy items over to it.
		NBTTagList turn = new NBTTagList();
		for (byte i = 0; i < items.length; i++)
		{
			if (items[i] == null) continue;
			// Create a tag for the item with the slot.
			NBTTagCompound itemTag = new NBTTagCompound();
			itemTag.setByte("Slot", i);
			// Write the item to the tag and append it.
			items[i].writeToNBT(itemTag);
			turn.appendTag(itemTag);
		}
		return turn;
	}
	
	/**
	 * Reads an array of items from an NBTTagList.
	 * @param items
	 * @param length The length of the returned array
	 * @return
	 */
	public static ItemStack[] readItemCollectionTag(NBTTagList items, int length)
	{
		// Create the return array as the length of the items in the list.
		ItemStack[] turn = new ItemStack[length];
		
		// Iterate through the list and copy the items over
		for (byte i = 0; i < items.tagCount(); i++)
		{
			// Get the item tag and slot.
			NBTTagCompound item = items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			
			// If the slot is valid - prevent item dupes/etc through NBT edit
			if (slot >= 0 && slot < turn.length)
				turn[slot] = ItemStack.loadItemStackFromNBT(item);
		}
		return turn;
	}

}
