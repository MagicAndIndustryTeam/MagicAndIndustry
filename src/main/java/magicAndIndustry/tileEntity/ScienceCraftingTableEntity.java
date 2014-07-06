package magicAndIndustry.tileEntity;

import magicAndIndustry.items.ResearchBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class ScienceCraftingTableEntity extends TileEntity implements IInventory
{
	/**
	 * 0-8: crafting grid.
	 * 9: book.
	 * 10: output.
	 */
	private ItemStack[] items;
	
	public ScienceCraftingTableEntity()
	{
		items = new ItemStack[getSizeInventory()];
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		
	}

	@Override // It goes one higher.
	public int getSizeInventory() { return 11; }

	@Override
	public ItemStack getStackInSlot(int slot) { return items[slot]; }

	@Override
	public ItemStack decrStackSize(int slot, int count) 
	{
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item) {
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
	public int getInventoryStackLimit() { return 64; }

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() { }

	@Override
	public void closeInventory() { }

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) 
	{
		// Book slot: put book in slot.
		if (slot == 9) return stack.getItem() instanceof ResearchBook;
		// Output: you can't use it.
		if (slot == 10) return false;
		// Crafting: go ahead.
		return true;
	}

}
