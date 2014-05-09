package magicAndIndustry.Blocks;

import magicAndIndustry.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class CobbleFurnaceCore extends MachineCore
{
	public IIcon[] sides = new IIcon[6];
	
	public void registerBlockicons(IIconRegister reg)
	{
		// Note that we need to make sure this is a rotating block.
		for (int i = 0; i < 6; i++)
		{
			if (i == 2) sides[i] = reg.registerIcon(Utils.ModID + ":smelter_core_front");
			else        sides[i] = reg.registerIcon(Utils.ModID + ":smelter_core_side");
		}
	}
	
	public IIcon getIcon(int side, int meta)
	{
		if (side > 5) return null;
		return sides[side];
	}
	
	protected CobbleFurnaceCore(Material m) { super(m); }
}
