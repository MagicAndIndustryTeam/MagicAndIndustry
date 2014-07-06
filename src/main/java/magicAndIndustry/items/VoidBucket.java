package magicAndIndustry.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VoidBucket extends ItemBucket
{
	public VoidBucket()
	{
		super(Blocks.air);
		setUnlocalizedName("void_bucket");
		setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		ItemStack stack2 = stack.copy();
		// This is waaaaay easier.
		super.onItemRightClick(stack, world, player);
		return stack2;
	}
}
