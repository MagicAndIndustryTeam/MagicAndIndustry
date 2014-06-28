package magicAndIndustry.machines.structure;

import magicAndIndustry.Utils;
import magicAndIndustry.blocks.BlockRegistrar;
import magicAndIndustry.blocks.MachineSlab;
import magicAndIndustry.machines.MachineTier;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class MachineSlabRequirement extends StructureRequirementBase 
{
	@Override
	public boolean isMatch(MachineTier tier, World world, int x, int y, int z, int coreX, int coreY, int coreZ) 
	{
		Block brock = world.getBlock(x, y, z);
		// TODO machine slab fixing
		if (tier.name.equals("cobble")) return Block.isEqualTo(brock, Blocks.stone_slab);
		if (tier.name.equals("iron")) return Block.isEqualTo(brock, BlockRegistrar.cookedSlab);
		return Block.isEqualTo(brock, BlockRegistrar.ironSlab);
	}

	@Override
	public String toString() 
	{
		return "Check for a machine slab of the proper tier";
	}

}
