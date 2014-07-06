package magicAndIndustry.blocks;

import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.tileEntity.ScienceCraftingTableEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ScienceCraftingTable extends BlockContainer // implements IWrenchable?
{
	public ScienceCraftingTable()
	{
		super(Material.rock);
		setBlockName("science_crafting_table");
		setHardness(3); 
		setResistance(10);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new ScienceCraftingTableEntity();
	}
	
	@Override
	public int getRenderType()
	{
		return MagicAndIndustry.renderID;
	}
	
	@Override
	public boolean isOpaqueCube() { return false; }

}
