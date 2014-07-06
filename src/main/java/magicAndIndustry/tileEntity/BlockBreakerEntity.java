package magicAndIndustry.tileEntity;

import magicAndIndustry.machines.MachineTier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class BlockBreakerEntity extends TileEntity implements IInventory
{
	public MachineTier tier;
	private int counter = 0;
	private ItemStack[] items;
	
	public BlockBreakerEntity(MachineTier tier)
	{
		this.tier = tier;
		items = new ItemStack[9];
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		
	}

	@Override
	public int getSizeInventory() { return 9; }

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return items[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String getInventoryName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() { return 64; }

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
