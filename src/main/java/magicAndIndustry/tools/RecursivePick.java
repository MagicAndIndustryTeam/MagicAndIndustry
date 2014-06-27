package magicAndIndustry.tools;

import magicAndIndustry.Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecursivePick extends ItemPickaxe 
{
	public RecursivePick()
	{
		// Replace with a specific material
		super(Utils.steelMaterial);
		setUnlocalizedName("recursive_pick");
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block brock, int x, int y, int z, EntityLivingBase entity)
	{
		if (!(entity instanceof EntityPlayer)) return false;
		
		EntityPlayer player = (EntityPlayer)entity;
		
		if (!player.isSneaking() && canHarvestBlock(brock, stack))
		{
			RecurseMine(world, brock.getUnlocalizedName(), player, stack, x, y, z, 0);
		}
		return true;
	}
	
	private void RecurseMine(World world, String blockName, EntityPlayer player, ItemStack stack, int x, int y, int z, int previousMined)
	{
		if (previousMined >= 5 || 
				stack.getItemDamage() >= stack.getMaxDamage() - 1) 
			return;

		Block brock = world.getBlock(x, y, z);
		
		if (brock == null || !brock.getUnlocalizedName().equals(blockName))
		{
			return;
		} 
		
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (brock.canHarvestBlock(player, metadata))
		{
			if (!player.capabilities.isCreativeMode)
			{
				if (brock.removedByPlayer(world, player, x, y, z))
				{
					brock.onBlockDestroyedByPlayer(world, x, y, z, metadata);
				}
				brock.harvestBlock(world, player, x, y, z, metadata);
				brock.onBlockHarvested(world, x, y, z, metadata, player);
			
				if (brock.getBlockHardness(world, x, y, z) > 0f)
					onBlockDestroyed(stack, world, brock, x, y, z, player);
				
				stack.damageItem(2, player);
			}
			else world.setBlock(x, y, z, Blocks.air);
			
			int prev = previousMined + 1;
			
			RecurseMine(world, blockName, player, stack, x + 1, y, z, prev); 
			RecurseMine(world, blockName, player, stack, x - 1, y, z, prev);
			
			RecurseMine(world, blockName, player, stack, x, y + 1, z, prev);
			RecurseMine(world, blockName, player, stack, x, y-1, z, prev);
			
			RecurseMine(world, blockName, player, stack, x, y, z+1, prev);
			RecurseMine(world, blockName, player, stack, x, y, z-1, prev);
		}
		
	}
}
