package magicAndIndustry.machines.upgrade;

import magicAndIndustry.tileEntity.base.StructureUpgradeEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class AutomationStructureEntity extends StructureUpgradeEntity implements IInventory
{
	/** The items in the inventory. */
	private ItemStack[] items;
	/** Whether to input items to the machine, or get the machine's output. */
	private boolean input;
	/** The number of ticks until the next operation. */
	private int updateCount;
	/** Number of items in storage. */
	private static final int MAX_ITEMS = 5;
	/** How many ticks to wait between trying to suck/push from other chests. */
	private static final int EJECT_UPDATE_LENGTH = 20 * 3;
	
	public AutomationStructureEntity()
	{
		items = new ItemStack[MAX_ITEMS];
		input = true;
	}
	
	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote && !input)
		{
			if (updateCount > EJECT_UPDATE_LENGTH) updateCount++;
			
			else // We are ready.
			{
				// Reset the count
				updateCount = 0;
				
				IInventory targetInv = null;
				
				// We are ready to export.
				for (int i = 0; i < MAX_ITEMS; i++)
				{
					if (items[i] == null || items[i].stackSize == 0) continue;
					
					//if (targetInv == null) targetInv = getLocalInv();
				}
			}
		}
	}
	
	//private IInventory getLocalInv()
	//{
		/*
		for (int i = 2; i <= 6; i++)
		{
			ForgeDirection dir = ForgeDirection.getOrientation(i);
			System.out.println(dir);
			TileEntity ent = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord, zCoord + dir.offsetY);
			
			if (ent != null)
			{
				int j, slot;
				if (ent instanceof ISidedInventory)
				{
					ISidedInventory sidedEnt = (ISidedInventory)ent;
					for (j = 0; i < sidedEnt.getSizeInventory(); i++)
					{
						
					}
				}
				else if (ent instanceof IInventory)
				{
					IInventory invEnt = (IInventory)ent;
				}
			}
		}*/
	//}
	
	@Override
	public int getSizeInventory()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int var1)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2)
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
	public int getInventoryStackLimit()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1)
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
	public boolean isItemValidForSlot(int var1, ItemStack var2)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
