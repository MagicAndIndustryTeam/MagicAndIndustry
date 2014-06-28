package magicAndIndustry.machines.structure;

import magicAndIndustry.blocks.StructureBlock;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.tileEntity.StructureTileEntity;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class StructureBlockRequirement extends StructureRequirementBase
{	
	@Override
	public boolean isMatch(MachineTier tier, World world, int x, int y, int z, int coreX, int coreY, int coreZ)
	{
		Block brock = world.getBlock(x, y, z);
		// Uses the tier level check here.
		if (brock != null && brock instanceof StructureBlock)
		{ 
			StructureBlock sbrock = (StructureBlock)brock;
			
			if (!sbrock.tier.isStrongEnoughFor(tier)) return false;
			
			StructureTileEntity ent = (StructureTileEntity)world.getTileEntity(x, y, z);
			if (ent != null && ent.hasCore())// Structures cannot overlap.
			{
				return ent.coreX == coreX && ent.coreY == coreY && ent.coreZ == coreZ;
			}
			return true;
		}
		return false;
	}

	@Override
	public String toString()
	{
		return "Check for block of the same machine tier";
	}

}
