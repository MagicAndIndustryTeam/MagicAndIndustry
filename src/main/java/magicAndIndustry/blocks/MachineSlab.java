package magicAndIndustry.blocks;

import java.util.Random;

import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.utils.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachineSlab extends BlockSlab 
{
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;
	
	private final MachineTier tier;

	public MachineSlab(boolean full, MachineTier theTier) 
	{
		super(full, Material.rock); tier = theTier;
		setBlockName(tier.name + "_slab" + (full ? "_full" : ""));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		//blockIcon = reg.registerIcon(tier.getAltTexture());
		if (tier.name == "steel") blockIcon = reg.registerIcon(Textures.block("ironAnvilSide"));
		else if (tier.name == "iron") blockIcon = reg.registerIcon(MachineTier.iron.getFaceTexture());
		sideIcon = reg.registerIcon(Textures.block(tier.name, "slab"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side < 2) return blockIcon;
		return sideIcon;
	}
	
	public Item getItemDropped(int meta, Random rand, int other)
	{
		return Item.getItemFromBlock(tier.name == "iron" ? BlockRegistrar.ironSlab : BlockRegistrar.cookedSlab);
	}
	
	public ItemStack createStackedBlock(int meta)
	{
		return new ItemStack(getItemDropped(meta, null, 0), 1, 0);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote && side < 2 && !field_150004_a && !isSideSolid(world, x, y, z, ForgeDirection.getOrientation(side)))
		{
			ItemStack heldItem = player.getHeldItem();
			if (heldItem != null)
			{
				MachineSlab slab = (MachineSlab)world.getBlock(x, y, z);
				
				if (slab != null && !slab.field_150004_a)
				{
					Item dropped = getItemDropped(world.getBlockMetadata(x, y, z), world.rand, 0);
					if (heldItem.getItem() == dropped)
					{
						world.setBlock(x, y, z, BlockRegistrar.machineSlabForType(tier.name));
						if (!player.capabilities.isCreativeMode) heldItem.stackSize--;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return this.field_150004_a;
	}
	
	@Override
	public boolean getUseNeighborBrightness()
	{
		return !this.field_150004_a;
	}

	@Override
	public String func_150002_b(int var1) 
	{
		return getUnlocalizedName() + "_full";
	}

	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		return new ItemStack(getItemDropped(world.getBlockMetadata(x, y, z), null, 0));
	}
	
	
}
