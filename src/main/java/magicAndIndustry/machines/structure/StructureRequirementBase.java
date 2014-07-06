package magicAndIndustry.machines.structure;

import magicAndIndustry.machines.MachineTier;
import net.minecraft.world.World;

/**
 * Base class for structure requirements in machine structures.
 * A {@link MachineStructure} is a collection of Structure Requirements (with relative x, y, z).
 * The machine check makes sure the blocks at those coords pass through isMatch.
 * @see {@link BlockRequirement}, {@link StructureBlockRequirement}
 */
public abstract class StructureRequirementBase 
{
	/**
	 * Called by machines on each of their structure's requirements to confirm this block still valid in their structure.
	 * @param tier The tier of machine in question.
	 * @param world The world the block is
	 * @param x block x coord
	 * @param y block y coord
	 * @param z block z coord
	 * @return Whether the block at this position is compatable with a machine of this tier with the type of requirement.
	 */
	public abstract boolean isMatch(MachineTier tier, World world, int x, int y, int z, int coreX, int coreY, int coreZ);
	
	/**
	 * TODO NOT IMPLEMENTED
	 * A player-readable string (which is translated) to tell them what's wrong with their structure.
	 */
	public abstract String toString();
}
