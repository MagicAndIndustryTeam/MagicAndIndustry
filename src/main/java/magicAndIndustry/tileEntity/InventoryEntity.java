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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		// TODO Auto-generated method stub

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
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		// TODO Auto-generated method stub
		return false;
	}

}
