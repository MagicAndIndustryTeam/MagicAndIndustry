package magicAndIndustry.machines.structure;

import magicAndIndustry.blocks.StructureBlock;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.tileEntity.StructureTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class StructureBlockRequirement extends StructureRequirementBase
{	
	@Override
	public boolean isMatch(MachineTier tier, World world, int x, int y, int z)
	{
		StructureBlock brock = (StructureBlock)world.getBlock(x, y, z);
		// Uses the tier level check here.
		if (brock != null && brock.tier.isStrongEnoughFor(tier))
		{
			StructureTileEntity ent = (StructureTileEntity)world.getTileEntity(x, y, z);
			if (ent != null) return !ent.hasCore(); // Structures cannot overlap.
		}
		return false;
	}

	@Override
	public String toString()
	{
		return "Check for block of the same machine tier";
	}

}
