package magicAndIndustry.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MAITab extends CreativeTabs
{

	public MAITab() 
	{
		super("magicAndIndustryTab");
	}

	@Override
	public Item getTabIconItem() 
	{
		return ItemRegistrar.wrench;
	}

}
