package magicAndIndustry.blocks;

import magicAndIndustry.Textures;
import magicAndIndustry.Utils;
import magicAndIndustry.machines.MachineTier;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SafeStructure extends Block implements IWrenchable
{
	@SideOnly(Side.CLIENT)
	private IIcon icon;
	private MachineTier tier;
	
	public SafeStructure(MachineTier tier)
	{
		super(Material.rock); this.tier = tier;
		setBlockName(tier.name + "_structure_safe");
		textureName = this.tier.getFaceTexture(); // Extra resistant :D
		setHardness(tier.standardHardness); setResistance(tier.standardResistance + 5F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	// TODO add connected textures here too.
	/*
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		icon = reg.registerIcon(textureName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return icon;
	}
	*/
	@Override
	public void OnWrenched(EntityPlayer player, World world, int x, int y, int z, int meta, int side) 
	{
		// Swap the metadata.
		if (!world.isRemote)
			world.setBlockMetadataWithNotify(x, y, z, meta == 0 ? 1 : 0, 2);
	}
}
