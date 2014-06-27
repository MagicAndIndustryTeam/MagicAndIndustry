package magicAndIndustry.machines.structure;

import java.util.HashMap;

import magicAndIndustry.Utils;
import magicAndIndustry.blocks.BlockRegistrar;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class MachineStructure 
{
	public PReq[] Requirements;
	public MachineStructure(PReq... requirements)
	{
		Requirements = requirements;
	}
	
	private MachineStructure(int requirements)
	{
		Requirements = new PReq[requirements];
	}
}
