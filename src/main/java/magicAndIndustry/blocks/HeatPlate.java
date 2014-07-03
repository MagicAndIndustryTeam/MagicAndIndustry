package magicAndIndustry.blocks;

import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.utils.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HeatPlate extends Block
{
	@SideOnly(Side.CLIENT)
	protected IIcon top, bottom;
	
	public MachineTier tier;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		top = reg.registerIcon(tier.getAltTexture());
		blockIcon = reg.registerIcon(Textures.block(tier.name, "heatplate"));
		bottom = reg.registerIcon(tier.getFaceTexture());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int whichSide, int metadata)
	{
		if (whichSide == 1) return top;
		else if (whichSide == ForgeDirection.DOWN.ordinal()) return bottom;
		else return blockIcon;
	}
	
	protected HeatPlate(MachineTier tier)
	{
		super(Material.rock);
		this.tier = tier;
		setBlockName(tier.name + "_heatplate");
		setHardness(tier.standardHardness);
		setResistance(tier.standardResistance);
	}
}
