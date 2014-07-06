package magicAndIndustry.weapons;

import magicAndIndustry.utils.Utils;
import net.minecraft.item.ItemSword;

public class SteelSword extends ItemSword
{
	public SteelSword()
	{
		super(Utils.steelMaterial);
		setUnlocalizedName("steelSword");
	}
}
