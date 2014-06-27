package magicAndIndustry.machines.structure;

/**
 * Positional structure requirement class.
 * Defines a requirement of a block relative to the core.
 */
public class PReq
{
	/** The requirement rule for the block */
	public StructureRequirementBase Requirement;
	/** The number of blocks behind (+) the core to check. */
	public int relBehind;
	/** The number of blocks to the left(+) or right(-) to check. */
	public int relSide ;
	/** The number of blocks above (+) or below (-) the core to check. */
	public int relHeight;
	
	public PReq(StructureRequirementBase structure, int behind, int side, int below)
	{
		Requirement = structure; relBehind = behind; relSide = side; relHeight = below;
	}
	
	public String toString()
	{
		return Requirement.toString() +  ": targeting " + relBehind + " behind, " + relHeight + " below, " + relSide + " side.";
	}
}
