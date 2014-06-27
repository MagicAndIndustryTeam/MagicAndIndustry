package magicAndIndustry.items;

import magicAndIndustry.api.IStructureUpgradeItem;
import magicAndIndustry.machines.StructureUpgrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Class of basic StructureUpgrades from MAI. Returns a shortened upgrade
 */
public class BasicStructureUpgradeItem extends Item implements IStructureUpgradeItem
{
	
	//public
	
	@Override
	public String getStructureUpgradeID()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StructureUpgrade getUpgrade(ItemStack stack, EntityPlayer player, World world, int x, int y, int z)
	{
		
		return null;
	}

}
