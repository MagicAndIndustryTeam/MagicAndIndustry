package magicAndIndustry.blocks;

import magicAndIndustry.tileEntity.DiscoBlockEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class DiscoBlockBlock extends BlockContainer
{	
	public DiscoBlockBlock()
	{
		super(Material.rock);
		setHardness(2.0F);
		setResistance(30F);
		setBlockName("disco_block");
	}
	
	/*
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitx, float hity, float hitz)
	{
		if (player.isSneaking()) return false;
		DiscoBlockEntity entity = (DiscoBlockEntity)world.getTileEntity(x, y, z);
		
		if (entity == null) return false;
		return true;
	}
	*/
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new DiscoBlockEntity();
	}
	
	@Override
	public boolean hasTileEntity()
	{
		return true;
	}
}
