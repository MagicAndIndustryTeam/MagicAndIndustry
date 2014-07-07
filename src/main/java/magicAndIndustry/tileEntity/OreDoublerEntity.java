package magicAndIndustry.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class OreDoublerEntity extends TileEntity implements IInventory
{
	/*
	 * 0: ore
	 * 1: pick
	 * 2: hammer
	 * 
	 */
	private ItemStack[] items;
	
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
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		
	}

	@Override
	public int getSizeInventory() { return 3; }

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
	public String getInventoryName() 
	{
		return "container.oreDoubler";
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		// It only holds tools and ores after all.
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		return true;
	}

	@Override
	public void openInventory() { }

	@Override
	public void closeInventory() { }

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		// TODO Auto-generated method stub
		return false;
	}

}
