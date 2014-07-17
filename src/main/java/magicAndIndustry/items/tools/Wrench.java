package magicAndIndustry.items.tools;

import magicAndIndustry.blocks.IWrenchable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Wrench extends ItemTool
{
	public Wrench() 
	{
		super(5, ToolMaterial.IRON, null);
		setUnlocalizedName("wrench");
		setNoRepair();
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		return true;
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, 
			int x, int y, int z, int side, float hitx, float hity, float hitz)
	{
		Block block = world.getBlock(x, y, z);
		if (block != null)
		{
			// TODO use buildcraft APIs or something
			if (block instanceof IWrenchable)
			{
				((IWrenchable)block).OnWrenched(player, world, x, y, z, world.getBlockMetadata(x, y, z), side);
				player.swingItem();
				return !world.isRemote;
			}

			if (block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side)))
			{
				player.swingItem(); 
				return !world.isRemote;
			}
		}
		return false;
	}
	
	@Override
	public boolean isFull3D()
	{
		// Render like a tool, facing outwards.
		return true;
	}
	
	@Override
	public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player)
	{
		// Allow OnWrenched events with sneaking.
		return true;
	}
}
