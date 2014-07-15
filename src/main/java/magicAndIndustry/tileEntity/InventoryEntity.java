package magicAndIndustry.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public abstract class InventoryEntity extends TileEntity implements IInventory 
{
	protected ItemStack[] items;
	protected String inventoryName;
	
	public abstract String getDefaultName();
	
	public InventoryEntity()
	{
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagList itemsList = tag.getTagList("items", 10);
		items = new ItemStack[getSizeInventory()];
		
		for (int i = 0; i < itemsList.tagCount(); i++)
		{
			NBTTagCompound item = itemsList.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			
			if (slot >= 0 && slot < items.length)
				items[slot] = ItemStack.loadItemStackFromNBT(item);
		}
		
		if (tag.hasKey("CustomName"))
			inventoryName = tag.getString("CustomName");
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < items.length; i++)
		{
			if (items[i] != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte)i);
				items[i].writeToNBT(item);
				list.appendTag(item);
			}
		}
		tag.setTag("Items", list);
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return items[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) 
	{
		ItemStack item = getStackInSlot(slot);
		if (item != null)
		{
			if (item.stackSize <= count)
				setInventorySlotContents(slot, null);
			else
			{
				ItemStack newer = ItemStack.copyItemStack(item);
				newer.stackSize -= count;
				
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
		items[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInventoryName() 
	{ 
		if (hasCustomInventoryName())
			return inventoryName;
		return getDefaultName();
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return inventoryName.length() != 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		return player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 16;
	}

	@Override
	public void openInventory() { }

	@Override
	public void closeInventory() { }

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) { return true; }

}
