package magicAndIndustry.blocks;

import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.Textures;
import magicAndIndustry.Utils;
import magicAndIndustry.api.IStructureUpgradeItem;
import magicAndIndustry.gui.GuiHandler;
import magicAndIndustry.items.ItemRegistrar;
import magicAndIndustry.tileEntity.EngineeringTableEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EngineeringTable extends DoubleBlockContainer 
{
	public EngineeringTable()
	{
		super("engineering_table", ItemRegistrar.engineeringTable, Material.rock);
		setBlockTextureName("engineering_table");
		setHardness(5F); setResistance(30F);
		maxY = 0.935F; // maxZ = 2F;
	}

	@Override
	public int getRenderType()
	{
		return MagicAndIndustry.renderID;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false; 
	}
	
	// AFAIK this just disables shadows
	//@Override
	//public boolean renderAsNormalBlock() { return false; }
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return ItemRegistrar.engineeringTable;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz)
	{
		if (world.isRemote) return true;
			
		
		if (!DoubleBlock.isMain(world.getBlockMetadata(x, y, z)))
		{
			// Get neighbor coords.
		}
		EngineeringTableEntity ent = (EngineeringTableEntity)world.getTileEntity(x, y, z);
		
		if (ent != null) player.openGui(MagicAndIndustry.instance, GuiHandler.ENGINEERING_TABLE, world, x, y, z);
		return true;
	}
	
	@Override
	public TileEntity getTileEntity(World world) 
	{
		return new EngineeringTableEntity();
	}
}
