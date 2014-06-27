package magicAndIndustry.weapons;

import magicAndIndustry.Utils;
import net.minecraft.item.ItemSword;

public class SteelSword extends ItemSword
{
	public SteelSword()
	{
		super(Utils.steelMaterial);
		setUnlocalizedName("steelSword");
	}
}
