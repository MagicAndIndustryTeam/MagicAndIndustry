package magicAndIndustry.gui;

import magicAndIndustry.tileEntity.FurnaceCoreEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FurnaceContainer extends Container
{
	private FurnaceCoreEntity furnace;
	private int lastFuelBurnTime;
	private int lastItemCookTime;
	private int lastMaxCookTime;
	private int lastMaxFuelTime;
	
	public FurnaceContainer(InventoryPlayer player, FurnaceCoreEntity furnaceEntity)
	{
		furnace = furnaceEntity;
		// Coords obtained from vanilla furnace texture.
		// 0 = fuel, 1 = input, 2 = output
		addSlotToContainer(new Slot(furnace, 0, 56, 53));
		addSlotToContainer(new Slot(furnace, 1, 56, 17));
		// "Output" style slot.
		// It has place item protection (maybe) and achievement stuff.
		addSlotToContainer(new SlotFurnace(player.player, furnace, 2, 116, 35));
		
		// Add inventory and hotbar :/
		// This will be a function later.
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
				addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
		}
		for (int i = 0; i< 9; i++)
			addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
	}
	
	// Vanilla furnace doesn't override it, might have to for shift click.
	//@Override
	///public boolean mergeItemStack(ItemStack stack, int start, int end, boolean backwards) { return true; }
	
	@Override
	// Synchronizes values between the client and server.
	public void addCraftingToCrafters(ICrafting craft)
	{
		super.addCraftingToCrafters(craft);
		craft.sendProgressBarUpdate(this, 0, furnace.fuelBurnTime);
		craft.sendProgressBarUpdate(this, 1, furnace.itemCookTime);
		craft.sendProgressBarUpdate(this, 2, furnace.maxCookTime);
		craft.sendProgressBarUpdate(this, 3, furnace.maxFuelTime);
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for (int i = 0; i < crafters.size(); i++)
		{
			ICrafting craft = (ICrafting)crafters.get(i);
			
			if (lastFuelBurnTime != furnace.fuelBurnTime)
				craft.sendProgressBarUpdate(this,  0, furnace.fuelBurnTime);
			if (lastItemCookTime != furnace.itemCookTime)
				craft.sendProgressBarUpdate(this, 1, furnace.itemCookTime);
			if (lastMaxCookTime != furnace.maxCookTime)
				craft.sendProgressBarUpdate(this, 2, furnace.maxCookTime);
			if (lastMaxFuelTime != furnace.maxFuelTime)
				craft.sendProgressBarUpdate(this, 3, furnace.maxFuelTime);
		}
		lastFuelBurnTime = furnace.fuelBurnTime;
		lastItemCookTime = furnace.itemCookTime;
		lastMaxCookTime = furnace.maxCookTime;
		lastMaxFuelTime = furnace.maxFuelTime;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int index, int value)
	{
		switch (index)
		{
			case 0:
				furnace.fuelBurnTime = value; break;
			case 1:
				furnace.itemCookTime = value; break;
			case 2:
				furnace.maxCookTime = value; break;
			case 3:
				furnace.maxFuelTime = value; break;
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNum)
	{
		ItemStack stack = null;
		Slot slot = (Slot)inventorySlots.get(slotNum);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack inSlot = slot.getStack();
			stack = inSlot.copy();
			
			// If it's the output slot
			if (slotNum == 2)
			{
				if (!mergeItemStack(inSlot, 3, 39, true))
					return null;
				
				slot.onSlotChange(inSlot, stack);
			}
			// I think this means if it's an item in player inventory
			else if (slotNum != 0 && slotNum != 1)
			{
				// If it can be smelted
				if (FurnaceRecipes.smelting().getSmeltingResult(inSlot) != null)
				{
					// Not 1--% sure on the parameters
					if (!mergeItemStack(inSlot, 1, 1, false)) return null;
				}
				// If it's a fuel
				else if (TileEntityFurnace.isItemFuel(inSlot))
				{
					if (!mergeItemStack(inSlot, 0, 2, false)) return null;
				}
				// Inventory???
				else if (slotNum >= 3 && slotNum < 30)
				{
					// Push an item from inv to hotbar!!!!!
					if (!this.mergeItemStack(inSlot, 30, 39, false)) return null;
				}
				else if (slotNum >= 30 && slotNum < 39 && !mergeItemStack(inSlot, 3, 30, false))
					return null;
			}
			else if (!mergeItemStack(inSlot, 3, 39, false))
				return null;
			
			if (inSlot.stackSize == 0)
				slot.putStack(null);
			
			else slot.onSlotChanged();
			
			if (inSlot.stackSize == stack.stackSize)
				return null;
			
			slot.onPickupFromSlot(player, inSlot);
		}
		
		return stack;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		return furnace.isUseableByPlayer(player);
	}
}
