package magicAndIndustry.blocks;

import java.util.List;
import java.util.Random;

import magicAndIndustry.Utils;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.othersrc.TextColor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SafeStructure extends Block implements IWrenchable, IConnectedTexture
{
	@SideOnly(Side.CLIENT)
	private IIcon icon;
	private MachineTier tier;
	
	public SafeStructure(MachineTier tier)
	{
		super(Material.rock); this.tier = tier;
		setBlockName(tier.name + "_structure_safe");
		textureName = this.tier.getFaceTexture(); 
		// easier to break, extra resistant :D
		setHardness(tier.standardHardness - 0.5F); setResistance(tier.standardResistance + 5F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		Item it = new Item()
		{
			// I dunno what this fancy override java thing is but it seems cool
			@Override public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) 
			{ 
				list.add(TextColor.RED + "For building use only!");
				if (Utils.isSneakKey())
					list.add(TextColor.GREY + "Does not create a TileEntity. Wrench to toggle connected texture.");
				else list.add(TextColor.GREY +  Utils.getSneakKey() + " for info.");
			}
		};
		it = super.getItemDropped(meta, rand, fortune);
		return it;
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
