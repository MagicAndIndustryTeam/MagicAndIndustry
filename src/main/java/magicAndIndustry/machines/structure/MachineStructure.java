package magicAndIndustry.machines.structure;

import java.util.HashMap;

import magicAndIndustry.RelativeFaceCoords;
import magicAndIndustry.Utils;
import magicAndIndustry.blocks.BlockRegistrar;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class MachineStructure 
{
	/**
	 * The different requirements for each block in the structure
	 */
	public PReq[] Requirements;
	/**
	 * The relative block coords of all structure blocks which should be striped when the machine is set up.
	 */
	public RelativeFaceCoords[] relativeStriped;
	
	/**
	 * Constructor for the registrar. Call .setStripes after to specify striped structures.
	 */
	public MachineStructure(PReq... requirements)
	{
		Requirements = requirements;
	}
	public MachineStructure setStripes(RelativeFaceCoords... stripedBlocks)
	{
		relativeStriped = stripedBlocks;
		return this;
	}
	
	private MachineStructure(int requirements)
	{
		Requirements = new PReq[requirements];
	}
}
