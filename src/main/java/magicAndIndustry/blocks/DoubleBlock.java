package magicAndIndustry.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DoubleBlock extends BlockDirectional
{
	private Item droppedItem;
	
	public DoubleBlock(Material mat, String unlocalizedName, Item dropped)
	{
		super(mat); setBlockName(unlocalizedName); droppedItem = dropped;
	}
	
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z, int meta)
	{
		return droppedItem;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block brock)
	{
		int meta = world.getBlockMetadata(x, y, z);
		int dir = getDirection(meta);

		if (isMain(meta))
		{
			if (world.getBlock(x - BlockBed.field_149981_a[dir][0], y, z - BlockBed.field_149981_a[dir][1]) != this)
			{
				world.setBlockToAir(x, y, z);
			}
		}
		else if (world.getBlock(x + BlockBed.field_149981_a[dir][0], y, z + BlockBed.field_149981_a[dir][1]) != this)
		{
			world.setBlockToAir(x, y, z);

			if (!world.isRemote)
			{
				this.dropBlockAsItem(world, x, y, z, meta, 0);
			}
		}
	}
	
	@Override
	public int getMobilityFlag() { return 1; }
	
	@Override
	public Item getItemDropped(int meta, Random rand, int arg3)
    {
        /**
         * Returns whether or not this bed block is the head of the bed.
         */
        return isMain(meta) ? Item.getItemById(0) : Items.bed;
    }
	
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float f, int other)
	{
		if (!isMain(meta))
			super.dropBlockAsItemWithChance(world, x, y, z, meta, f, 0);
	}
	
	public static boolean isMain(int meta)
	{
		return (meta & 8) == 0;
	}
}
