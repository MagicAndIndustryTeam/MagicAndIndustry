package magicAndIndustry.blocks;

import magicAndIndustry.utils.Hardness;
import magicAndIndustry.utils.Resistance;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class OreDoublerBlock extends Block 
{
	public OreDoublerBlock()
	{
		super(Material.piston);
		// TODO name
		setBlockName("ore_doubler");
		setHardness(Hardness.TECH);
		setResistance(Resistance.STONE);
	}
	
	
}
