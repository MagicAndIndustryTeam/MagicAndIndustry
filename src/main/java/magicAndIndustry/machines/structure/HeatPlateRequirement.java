package magicAndIndustry.machines.structure;

import magicAndIndustry.Utils;
import magicAndIndustry.blocks.HeatPlate;
import magicAndIndustry.machines.MachineTier;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class HeatPlateRequirement extends StructureRequirementBase 
{
	public HeatPlateRequirement()
	{
		
	}

	@Override
	public boolean isMatch(MachineTier tier, World world, int x, int y, int z, int coreX, int coreY, int coreZ) 
	{
		Block brock = world.getBlock(x, y, z);
		if (brock instanceof HeatPlate) return ((HeatPlate)brock).tier.isStrongEnoughFor(tier);
		return false;
	}

	@Override
	public String toString() 
	{
		return "Searching for a heat plate";
	}

}
