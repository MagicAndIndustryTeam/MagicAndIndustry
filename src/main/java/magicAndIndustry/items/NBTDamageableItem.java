package magicAndIndustry.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NBTDamageableItem extends Item 
{
	private int maxDamage;
	private Item damageItem;
	
	public NBTDamageableItem(int damageMax)
	{
		super();
		setMaxStackSize(1);
		setHasSubtypes(true);
		maxDamage = damageMax;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		ItemStack stack = new ItemStack(item);
		stack.setTagCompound(new NBTTagCompound());
		stack.stackTagCompound.setInteger("Uses", 0);
	}
	
	public void addInformation(ItemStack item, EntityPlayer player, List info, boolean bool)
	{
		if (item.hasTagCompound())
		{
			// TODO require glasses or something
			if (player.isSneaking())
				info.add("Uses: " + item.getTagCompound().getInteger("Uses") + "/" + maxDamage);
		}
	}
}
