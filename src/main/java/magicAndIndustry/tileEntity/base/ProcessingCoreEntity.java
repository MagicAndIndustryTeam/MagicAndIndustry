package magicAndIndustry.tileEntity.base;

import magicAndIndustry.machines.MachineTier;
import net.minecraft.item.ItemStack;

/**
 * Base class for machines with processing operations.
 */
public abstract class ProcessingCoreEntity extends MachineCoreEntity /* does not implement iinventory*/ // implements powered
{
	// Variables: fuel - saved internally
	private ItemStack[] input;
	private ItemStack output;
	
	// TODO use a battery.
	public int power;
	
	public ProcessingCoreEntity()
	{
		
	}
	
	public ProcessingCoreEntity(MachineTier tier)
	{
		
	}
}
