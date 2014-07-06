package magicAndIndustry.tileEntity;

import magicAndIndustry.items.ResearchBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class EngineeringTableEntity extends TileEntity implements IInventory
{
	/** The storage for the prototypes. */
	private ItemStack[] tinkerZone;
	/** 0-4: supplies, 5:book, 6:output */
	private ItemStack[] supplies;
	
	public static final int TINKER_WIDTH = 7, TINKER_HEIGHT = 4;
	
	public EngineeringTableEntity()
	{
		tinkerZone = new ItemStack[TINKER_WIDTH * TINKER_HEIGHT];
		supplies = new ItemStack[7];
	}

	@Override
	public int getSizeInventory() { return 33; } //7 * 4 + 5

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		if (slot < 28) 
		{
			return tinkerZone[slot];
		}
		return supplies[slot-28];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) 
	{
		ItemStack item = getStackInSlot(slot);
		if (item != null)
		{
			if (item.stackSize <= count)
				setInventorySlotContents(slot, null);
			
			else // Count is less than amount
			{
				ItemStack newer = ItemStack.copyItemStack(item); newer.stackSize -= count;
				setInventorySlotContents(slot, newer);
				item = item.splitStack(count);
			}
		}
		return item;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) 
	{
		ItemStack item = getStackInSlot(slot);
		setInventorySlotContents(slot, item);
		return item;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) 
	{
		if (slot < 28) tinkerZone[slot] = stack;
		else supplies[slot-28] = stack;
		
		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() { return 64; }

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) 
	{
		// Easter egg: you can always interact with the engineering table
		// so many people will abuse this by right clicking before a minecart ride.
		return true;
	}

	@Override
	public void openInventory() { }

	@Override
	public void closeInventory() { }

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) 
	{
		// Book
		if (slot == 33) return stack.getItem() instanceof ResearchBook;
		
		// Supplies
		if (slot > 28) { return true; }
		
		// main
		return true;
	}
	public void setPrototypingCraft()
	{
		
	}
}
