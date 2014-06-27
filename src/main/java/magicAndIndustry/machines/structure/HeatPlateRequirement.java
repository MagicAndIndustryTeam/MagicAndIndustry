package magicAndIndustry.machines.structure;

import magicAndIndustry.blocks.HeatPlate;
import magicAndIndustry.machines.MachineTier;
import net.minecraft.world.World;

public class HeatPlateRequirement extends StructureRequirementBase 
{
	public HeatPlateRequirement()
	{
		
	}

	@Override
	public boolean isMatch(MachineTier tier, World world, int x, int y, int z) 
	{
		HeatPlate plate = (HeatPlate)world.getBlock(x, y, z);
		if (plate != null && plate.tier.isStrongEnoughFor(tier)) return true;
		return false;
	}

	@Override
	public String toString() 
	{
		return "Searching for a heat plate";
	}

}
