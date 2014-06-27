package magicAndIndustry.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class DoubleBlockItem extends Item 
{
	private Block placedBlock;
	
	public DoubleBlockItem(String name, Block block)
	{
		setUnlocalizedName(name);
		placedBlock = block;
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote) return true;
		if (side != 1) return false;
		
		++y;
		int dir = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		System.out.println("Got dir: " + dir);
		byte addX = 0, addZ = 0;

		if (dir == 0) addZ = 1;
		else if (dir == 1) addX = -1;
		else if (dir == 2) addZ = -1;
		else if (dir == 3) addX = 1;
		
		System.out.println("got stuff, addX = " + addX + ", addZ = " + addZ);

		if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x + addX, y, z + addZ, side, stack) &&
			world.isAirBlock(x, y, z) && world.isAirBlock(x + addX, y, z + addZ) 
			&& World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && World.doesBlockHaveSolidTopSurface(world, x + addX, y - 1, z + addZ))
		{
			System.out.println("player can edit!");
			if (world.setBlock(x, y, z, placedBlock, dir, 3)) System.out.println("Set the block!");

			if (world.getBlock(x, y, z) == placedBlock)
				world.setBlock(x + addX, y, z + addZ, placedBlock, dir + 8, 3);

			--stack.stackSize; return true;
		}
		return false;
		
	}
}
