package magicAndIndustry.tileEntity.base;

import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.machines.event.ProcessingEvent;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Base class for machines with processing operations.
 */
public abstract class ProcessingCoreEntity extends MachineCoreEntity  implements IInventory // powered
{
	// Variables: fuel - saved internally
	private ItemStack[] input;
	private ItemStack output;
	
	// TODO use a battery.
	public int power;
	public int currentProcessingTime;
	private byte tickCounter = 0;
	
	/**
	 * Gets the maximum processing time that the machine needs to process.
	 * This is called twice a second (every 10 ticks on the 0th tick) and
	 * the core talks to the upgrades to modify a number (starting at 10)
	 * subtracted from it.
	 * @return The maximum amount of processing the machine should do, with
	 * 10 being subtracted twice a second under normal operation.
	 */
	public abstract int getMaxProcessingTime();
	
	/**
	 * If this returns true, setMachineState is called instead of a metadata change.
	 * The metadata change will set the 3rd bit to true while processing.
	 * setMachineState can be implemented and should be used 
	 * @return
	 */
	public abstract boolean hasAltBlock();
	
	/**
	 * If 
	 * @param active
	 * @param tier
	 */
	public abstract void setMachineState(boolean active);
	
	public ProcessingCoreEntity()
	{
		
	}
	
	public ProcessingCoreEntity(MachineTier tier)
	{
		super(tier);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		
	}
	
	public void updateEntity()
	{
		// Machine core structure checks, etc.
		super.updateEntity();
		
		///////////////////////////////////////
		// 0. Make sure the structure is valid.
		///////////////////////////////////////
		
		// Every update don't do anything if the structure isn't complete.
		if (!structureComplete())
		{
			// Handling for a block update
			if (currentProcessingTime > 0 && hasAltBlock())
			{
				// Call subclass's code to change the block
				setMachineState(false);
				// The block has been updated.
				markDirty();
			}
			currentProcessingTime = 0;
			return;
		}
		
		// Only process twice a second.
		// Dunno why vanilla doesn't do this
		if (tickCounter != 10)
		{
			tickCounter++; return;
		}
		
		/////////////////////////////////////////////////////////////
		// 1. Calculate and subtract energy costs for this operation.
		/////////////////////////////////////////////////////////////
		int currentCost = 10;
		for (StructureUpgradeEntity upgrade : upgrades)
		{
			if (upgrade.handlesProcessing())
			{
				
			}
		}
	}
}
