package magicAndIndustry.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@SuppressWarnings({"unchecked", "rawtypes"}) // addInformation
public class IronScrew extends Item
{
	public IronScrew()
	{
		super();
		setUnlocalizedName("screw_iron");
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List items, boolean boo)
	{
		items.add("SCREW IRON!");
	}
}
