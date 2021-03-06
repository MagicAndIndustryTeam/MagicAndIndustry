package magicAndIndustry.machines.structure;

import magicAndIndustry.utils.RelativeFaceCoords;

/**
 * Positional structure requirement class.
 * Defines a requirement of a block relative to the core.
 */
public class PReq
{
	/** The requirement rule for the block */
	public StructureRequirementBase requirement;
	
	/** Relative coordinates of block checked. */
	public RelativeFaceCoords rel;
	
	public PReq(StructureRequirementBase structure, int behind, int side, int below)
	{
		requirement = structure; 
		rel = new RelativeFaceCoords(behind, side, below);
	}
	
	public String toString()
	{
		return requirement.toString() +  ": targeting " + rel.behind + " behind, " + rel.height + " below, " + rel.side + " side.";
	}
}
