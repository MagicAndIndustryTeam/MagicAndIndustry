package magicAndIndustry.tileEntity.base;

import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.machines.event.PowerRequestEvent;
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
		power = tag.getInteger("Power");
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
		
		/////////////////////////////////
		// Processing handled serverside.
		/////////////////////////////////
		if (worldObj.isRemote) return;
		
		// Only process twice a second.
		// TODO affect tick counter with timers.
		// Dunno why vanilla doesn't do this
		if (tickCounter != 10)
		{
			tickCounter++; return;
		}
		
		/////////////////////////////////////////////////////////////
		// 1. Calculate and subtract energy costs for this operation.
		/////////////////////////////////////////////////////////////
		ProcessingEvent processingEvent = new ProcessingEvent();
		
		for (StructureUpgradeEntity upgrade : upgrades)
			if (upgrade.handlesProcessing())
				upgrade.handleProcessing(processingEvent);
		
		// TODO use battery with checks.
		power -= processingEvent.getEnergyCost();
		
		// 2. If more power is needed, draw it from external sources.
		
		int required = power;
		PowerRequestEvent powerEvent = new PowerRequestEvent(0-required);
		if (required < 0)
		{
			for (StructureUpgradeEntity upgrade : upgrades)
				if (upgrade.handlesPowerUsage())
				{
					upgrade.handlePowerRequest(powerEvent);
					
					if (powerEvent.isComplete())
					{
						power += powerEvent.getCurrentEnergy();
						break;
					}
				}
					
		}
	}
}
