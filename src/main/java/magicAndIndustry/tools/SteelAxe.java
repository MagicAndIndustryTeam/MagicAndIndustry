package magicAndIndustry.tools;

import magicAndIndustry.utils.Utils;
import net.minecraft.item.ItemAxe;

public class SteelAxe extends ItemAxe
{
	public SteelAxe()
	{
		super(Utils.steelMaterial);
		setUnlocalizedName("steel_axe");
	}
}
