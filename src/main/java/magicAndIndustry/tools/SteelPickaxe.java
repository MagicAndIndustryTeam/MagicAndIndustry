package magicAndIndustry.tools;

import magicAndIndustry.Utils;
import net.minecraft.item.ItemPickaxe;

public class SteelPickaxe extends ItemPickaxe
{
	public SteelPickaxe()
	{
		super(Utils.steelMaterial);
		setUnlocalizedName("steel_pickaxe");
	}
}
