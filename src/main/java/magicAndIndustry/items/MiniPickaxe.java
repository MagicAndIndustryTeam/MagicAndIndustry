package magicAndIndustry.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MiniPickaxe extends ItemPickaxe
{
	private static int maxDamage = 1024;
	
	public MiniPickaxe()
	{
		super(ToolMaterial.IRON);
		setHarvestLevel("pickaxe", 0);
		setUnlocalizedName("miniPickaxe");
		setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		ItemStack stack = new ItemStack(item);
		stack.setTagCompound(new NBTTagCompound());
		stack.stackTagCompound.setInteger("Uses", 0);
		list.add(stack);
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
