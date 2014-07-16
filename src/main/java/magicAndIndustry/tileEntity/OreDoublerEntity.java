package magicAndIndustry.tileEntity;

import magicAndIndustry.items.MiniHammer;
import magicAndIndustry.items.MiniPickaxe;
import magicAndIndustry.tileEntity.base.InventoryEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public class OreDoublerEntity extends InventoryEntity
{
	/*
	 * 0: ore
	 * 1: pick
	 * 2: hammer
	 * 
	 */
	// TODO pick and hammer could have different slots
	private static final int SLOT_ORE = 0, SLOT_PICK = 1, SLOT_HAMMER = 2;
	
	// TODO block data
	// Coord-mapped collection of 
	//		generated cracks,
	//		extra gems
	
	public int durabilityRemaining;
	
	public OreDoublerEntity()
	{
		items = new ItemStack[getSizeInventory()];
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
	}

	@Override
	public int getSizeInventory() { return 3; }

	@Override
	public int getInventoryStackLimit() 
	{
		// It only holds tools and ores after all.
		return 1;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) 
	{
		switch (slot)
		{
			// Ore slot
			case 0:
				String name = OreDictionary.getOreName(Item.getIdFromItem(stack.getItem()));
				if (name != "Unknown") // because null is for boring people
				{
					// check our dictionary of ores etc
					return true;
				}
				// I guess check for vanilla ores
				return false;
				
			// Tool slots
			case 1:
			case 2:
				Item item = stack.getItem();
				return item instanceof MiniPickaxe || item instanceof MiniHammer;
				
			// TODO other slots maybe
			default: return false;
		}
	}

	@Override
	public String getDefaultName() { return "container.oreDoubler"; }

}
