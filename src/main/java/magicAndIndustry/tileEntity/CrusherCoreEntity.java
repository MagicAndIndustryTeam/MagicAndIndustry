package magicAndIndustry.tileEntity;

import magicAndIndustry.machines.MachineTier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class CrusherCoreEntity extends MachineCoreEntity implements IInventory
{
	private ItemStack[] items;
	private static final int SLOT_FUEL = 0, SLOT_INPUT = 1, SLOT_OUTPUT = 2;
	public int maxCrushTime;
	
	public CrusherCoreEntity(MachineTier tier)
	{
		super(tier);
		items = new ItemStack[getSizeInventory()];
	}
	
	@Override
	public String getMachineID() 
	{
		return "crusher";
	}

	@Override
	public int getSizeInventory() { return 3; }

	@Override
	public ItemStack getStackInSlot(int slot) { return items[slot]; }

	@Override
	public ItemStack decrStackSize(int slot, int size) 
	{
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
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
	public boolean isUseableByPlayer(EntityPlayer var1) {
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
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		// TODO Auto-generated method stub
		return false;
	}
}
