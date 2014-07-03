package magicAndIndustry.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WeldingTorch extends Item
{
	public WeldingTorch()
	{
		super();
		setUnlocalizedName("welding_torch");
		setFull3D();
	}
	
	//@Override
	public boolean onItemUse(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public boolean shouldRotateAroundWhenRendering()
	{
		return true;
	}
	
	/*
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 10;
	}
	*/
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.bow;
	}
}
