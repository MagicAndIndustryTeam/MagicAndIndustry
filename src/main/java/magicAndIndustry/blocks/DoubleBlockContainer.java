package magicAndIndustry.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class DoubleBlockContainer extends BlockContainer
{
	private Item itemDropped;
	public DoubleBlockContainer(String name, Item dropped, Material mat)
	{
		super(mat); setBlockName(name); itemDropped = dropped;
	}
	
    public void onNeighborBlockChange(World world, int x, int y, int z, Block brock)
    {
        int meta = world.getBlockMetadata(x, y, z);
        int dir = DoubleBlock.getDirection(meta);

        if (!DoubleBlock.isMain(meta))
        {
            if (!world.getBlock(x - BlockBed.field_149981_a[dir][0], y, z - BlockBed.field_149981_a[dir][1]).getUnlocalizedName().equals(getUnlocalizedName()))
            	world.setBlockToAir(x, y, z);
            else
            {
            	//maxX = 2 * BlockBed.field_149981_a[dir][0];
            	//maxZ = 2 * BlockBed.field_149981_a[dir][1];
            }
        }
        else if (!world.getBlock(x + BlockBed.field_149981_a[dir][0], y, z + BlockBed.field_149981_a[dir][1]).getUnlocalizedName().equals(getUnlocalizedName()))
        {
            world.setBlockToAir(x, y, z);
            if (!world.isRemote) this.dropBlockAsItem(world, x, y, z, meta, 0);
        }
        else
        {
        	
        }
    }
    
    public Item getItemDropped(int meta, Random rand, int num)
    {
    	return DoubleBlock.isMain(meta) ? Item.getItemById(0) : itemDropped;
    }
    
    public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player)
    {
        if (player.capabilities.isCreativeMode && DoubleBlock.isMain(meta))
        {
            int i1 = DoubleBlock.getDirection(meta);
            x -= BlockBed.field_149981_a[i1][0];
            z -= BlockBed.field_149981_a[i1][1];

            if (world.getBlock(x, y, z) == this)
            {
                world.setBlockToAir(x, y, z);
            }
        }
    }

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return DoubleBlock.isMain(meta) ? null : getTileEntity(world);
	}
    
    public abstract TileEntity getTileEntity(World world);
}
