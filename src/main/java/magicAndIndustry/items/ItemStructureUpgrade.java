package magicAndIndustry.items;

import magicAndIndustry.machines.StructureUpgradeBase;
import net.minecraft.item.Item;

/*
 * There is only one item for structure upgrades,
 * the API and stuff will register StructureUpgradeHandlers
 * and use their powers through NBT data and events.
 */
public class ItemStructureUpgrade extends Item
{
	public StructureUpgradeBase Upgrade;
	
	public ItemStructureUpgrade()
	{
		
	}
}
