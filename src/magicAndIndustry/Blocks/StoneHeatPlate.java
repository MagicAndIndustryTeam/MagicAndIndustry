package magicAndIndustry.Blocks;

import magicAndIndustry.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;


public class StoneHeatPlate extends Block{
	
	public IIcon Top;
	public IIcon Side;
			
	protected StoneHeatPlate(Material m) 
	{
		super(m);
	}
	
	public void registerBlockicons(IIconRegister icon)
	{
		Top = icon.registerIcon(Utils.ModID + ":smelter_heatplate_top2");
		Side = icon.registerIcon(Utils.ModID + ":smelter_heatplate_side3");
	}
	
	
	public IIcon getIcon(int side, int meta)
	{
		if (side == 1) return Top;
		return Side;
	}

}

