package magicAndIndustry.machines.structure;

import magicAndIndustry.machines.MachineTier;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BlockRequirement extends StructureRequirementBase
{
	/** The block being checked for. */
	public Block brock;
	/** The block's metadata (if needed) or -1 */
	public int meta;
	
	/**
	 * Create a requirement for a block with metadata.
	 * @param brock The block instance to check for.
	 * @param meta The block metadata needed.
	 */
	public BlockRequirement(Block brock, int meta)
	{
		super();
		this.brock = brock; this.meta = meta;
	}
	
	/**
	 * Create a requirement for a block (with any metadata).
	 * @param brock The block instance to check for.
	 */
	public BlockRequirement(Block brock)
	{
		this(brock, -1);
	}

	@Override
	/**
	 * Checks to confirm that the block (and optionally metadata) is the same.
	 */
	public boolean isMatch(MachineTier tier, World world, int x, int y, int z) 
	{
		Block brock2 = world.getBlock(x, y, z);
		
		boolean isBlock = Block.isEqualTo(brock2, brock);
		if (meta != -1)
			return isBlock && world.getBlockMetadata(x, y, z) == meta;
		
		return isBlock;
	}
	
	public String toString()
	{
		return "Block Requirement: " + brock.getUnlocalizedName() + (meta == -1 ? " (any metadata)." : " (metadata " + meta + ").");
	}
}
