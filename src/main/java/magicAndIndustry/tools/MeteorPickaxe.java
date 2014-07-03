package magicAndIndustry.tools;

import magicAndIndustry.utils.Utils;
import net.minecraft.item.ItemPickaxe;

public class MeteorPickaxe extends ItemPickaxe 
{
	public MeteorPickaxe()
	{
		super(Utils.meteoricIronMaterial);
		setUnlocalizedName("meteor_pickaxe");
	}
}
