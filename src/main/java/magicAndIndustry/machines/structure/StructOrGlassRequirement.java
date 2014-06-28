package magicAndIndustry.machines.structure;


import magicAndIndustry.blocks.IndustrialGlass;
import magicAndIndustry.blocks.StructureBlock;
import magicAndIndustry.machines.MachineTier;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Require a Structure Block of a specific tier or an Industrial Glass of a specific tier.
 */
public class StructOrGlassRequirement extends StructureRequirementBase 
{
	@Override
	public boolean isMatch(MachineTier tier, World world, int x, int y, int z, int coreX, int coreY, int coreZ) 
	{
		Block brock = world.getBlock(x, y, z);
		
		if (!(brock instanceof IndustrialGlass))
		{
			
		}
		
		return brock instanceof StructureBlock || brock instanceof IndustrialGlass;
	}

	@Override
	public String toString() 
	{
		return "Check for a structure block or Industrial Glass at that position.";
	}

}
