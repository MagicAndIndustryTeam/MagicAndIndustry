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
	public PReq[] requirements;
	/**
	 * The relative block coords of all structure blocks which should be striped when the machine is set up.
	 */
	public RelativeFaceCoords[] relativeStriped;
	
	/**
	 * An ID of the machine structure. Used by machine cores with multiple structures to tell which one they have.
	 */
	public String ID;
	
	/**
	 * Constructor for the registrar. Call .setStripes after to specify striped structures.
	 */
	public MachineStructure(String ID, PReq... requirements)
	{
		this.ID = ID;
		this.requirements = requirements;
	}
	public MachineStructure setStripes(RelativeFaceCoords... stripedBlocks)
	{
		relativeStriped = stripedBlocks;
		return this;
	}
}
