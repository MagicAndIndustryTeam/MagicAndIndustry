package magicAndIndustry.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class IndustrialGlass extends Block 
{
	public IndustrialGlass()
	{
		super(Material.rock);
		setBlockName("industrial_glass");
		setHardness(4F); setResistance(40F);
		stepSound = soundTypeGlass;
	}
	
	@Override
	public boolean isOpaqueCube() { return false; }
	
	@Override
	public boolean renderAsNormalBlock() { return false; }
}
