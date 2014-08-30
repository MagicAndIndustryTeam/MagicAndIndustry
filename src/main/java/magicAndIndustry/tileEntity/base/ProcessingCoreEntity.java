package magicAndIndustry.tileEntity.base;

import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.machines.event.PowerUsageEvent;
import magicAndIndustry.machines.event.ProcessingEvent;
import magicAndIndustry.utils.NBTUtils;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Base class for machines with processing operations.
 */
public abstract class ProcessingCoreEntity extends MachineCoreEntity  implements IInventory // powered
{
	// Variables: fuel - saved internally
	private ItemStack[] input;
	private ItemStack[] outputs;
	
	// TODO use a battery.
	/**
	 * The power stored in the machine. Will be a battery object.
	 */
	public int power;
	/**
	 * How far along with processing (i.e. number of "process cycles") the machine is.
	 */
	public int currentProcessingTime;
	/**
	 * Used by structure upgrades to speed up or slow down the machine. <br/>
	 * Starts at 10 - 10 tick per process cycle - and can be increased or decreased.
	 * Once this many ticks have passed, tickCounter is reset and a process cycle is done.
	 */
	private byte tickProcessingTime;
	/**
	 * Used to count the ticks until the next processing cycle.
	 */
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
	 * setMachineState can be implemented and should be used for things like changing block types
	 * or changing light levels (as the vanilla furnace does).
	 * @return Whether to implement custom handling for machines turning on and off.
	 */
	public abstract boolean hasAltBlock();
	
	/**
	 * If hasAltBlock() returns true, this method will be called instead of a metadata change.
	 * 
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
		
		// load items
		// TODO determine items by machine configuration or structure upgrades.
		// The lengths of these are determined automatically and later refined by structure upgrades
		input = NBTUtils.readItemCollectionTag(tag.getTagList("Inputs",  10));
		outputs = NBTUtils.readItemCollectionTag(tag.getTagList("Outputs", 10));
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setInteger("Power", power);
		
		// Save items
		tag.setTag("Inputs", NBTUtils.writeItemCollectionTag(input));
		tag.setTag("Outputs", NBTUtils.writeItemCollectionTag(outputs));
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
		// 1. Processing handled serverside.
		/////////////////////////////////
		if (worldObj.isRemote) return;
		
		// Only process twice a second.
		// TODO affect tick counter with timers.
		// Dunno why vanilla doesn't do this
		if (tickCounter < tickProcessingTime)
		{
			tickCounter++; return;
		}
		
		/////////////////////////////////////////////////////////////
		// 2. Get the processing requirements from the structure upgrades.
		/////////////////////////////////////////////////////////////
		ProcessingEvent processingEvent = new ProcessingEvent();
		
		for (StructureUpgradeEntity upgrade : upgrades)
			if (upgrade.handlesProcessing())
				upgrade.handleProcessing(processingEvent);
		
		////////////////////////////////////////////////////
		// 3. Subtract power and update the processing time.
		////////////////////////////////////////////////////
		power -= processingEvent.getEnergyCost();
		tickProcessingTime = processingEvent.getProceesingTime();
		
		/////////////////////////////////////////////////////////////
		// 4. If more power is needed, draw it from external sources.
		/////////////////////////////////////////////////////////////
		// TODO fill up the battery if the system is in a lull
		// or when it's like < 75% or configurably or something.
		// Also, request the battery's max power. 
		// And don't go into debt over a process.
		if (power < 0) 
		{
			PowerUsageEvent powerEvent = new PowerUsageEvent(0 - power);
			for (StructureUpgradeEntity upgrade : upgrades)
				if (upgrade.handlesPowerUsage())
				{
					upgrade.handlePowerUsageRequest(powerEvent);
					
					if (powerEvent.isComplete())
					{
						power += powerEvent.getCurrentEnergy();
						break;
					}
				}
		} // if required < 0
		
		////////////////////////////////////
		// 3. Add steps to crafting process.
		////////////////////////////////////
		currentProcessingTime++; // Increase number of ticks, tick length is seperate.
		
		//////////////////////////////////////////////////////
		// 4. If crafting process is complete, craft the item.
		//////////////////////////////////////////////////////
		//if (currentProcessingTime > )
		
	}
}
