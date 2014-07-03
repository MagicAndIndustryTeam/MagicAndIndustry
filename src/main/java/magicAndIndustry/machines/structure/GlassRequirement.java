package magicAndIndustry.machines.structure;

import magicAndIndustry.blocks.BlockRegistrar;
import magicAndIndustry.machines.MachineTier;
import net.minecraft.block.Block;
import net.minecraft.world.World;


public class GlassRequirement extends StructureRequirementBase
{
	
	@Override
	public boolean isMatch(MachineTier tier, World world, int x, int y, int z, int coreX, int coreY, int coreZ) 
	{
		return Block.isEqualTo(world.getBlock(x, y, z), BlockRegistrar.industrialGlass);
	}

	@Override
	public String toString() 
	{
		return "Requires an Industrial Glass Block. ";
	}
}
