package magicAndIndustry.gui;

import magicAndIndustry.tileEntity.EngineeringTableEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

public class EngineeringTableContainer extends Container 
{
	private EngineeringTableEntity tableEntity;
	// books?
	private int lastCraftProgress;
	private int lastMaxCraftTime;
	
	public EngineeringTableContainer(InventoryPlayer player, EngineeringTableEntity table)
	{
		tableEntity = table; 
		
		int i, j = 0;
		
		// I have no idea what I'm doing here.
		for (i = 0; i < 4; i++)
			for (j = 0; j < 7; j++)
				addSlotToContainer(new Slot(table, j + i * EngineeringTableEntity.TINKER_WIDTH, 5 + j*20, 18 + i*20));
		
		for (i = 0; i < 5; i++, j++)
			addSlotToContainer(new Slot(table, 28 + i, 8 + i * 18, 108));

		addSlotToContainer(new Slot(table, 29 + i, 116, 108));
		
		addSlotToContainer(new Slot(table, 33, 150, 76));
		
		// Inventory code.
		for (i = 0; i < 3; i++)
			for (j = 0; j < 9; j++)
				addSlotToContainer(new Slot(player,j+i*9+9, 8 + j*18, 140 + i * 18));
		for (i = 0; i< 9; i++)
			addSlotToContainer(new Slot(player, i, 8 + i * 18, 198));
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting craft)
	{
		super.addCraftingToCrafters(craft);
		craft.sendProgressBarUpdate(this, 0, lastCraftProgress);
		craft.sendProgressBarUpdate(this, 1, lastMaxCraftTime);
	}
	
	@Override
	public void updateProgressBar(int index, int value)
	{
		switch (index)
		{
			case 0: lastCraftProgress = value; break;
			case 1: lastMaxCraftTime = value; break;
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNum)
	{
		ItemStack stack = null;
		Slot slot = (Slot)inventorySlots.get(slotNum);
		
		if (slot != null && slot.getHasStack())
		{
			
		}
		return null;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		return tableEntity.isUseableByPlayer(player);
	}

}
