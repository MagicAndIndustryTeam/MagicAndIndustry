package magicAndIndustry.items;

import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.api.IStructureUpgradeItem;
import magicAndIndustry.tileEntity.StructureUpgradeEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Class of basic StructureUpgrades from MAI. Returns a shortened upgrade
 */
public class BasicStructureUpgradeItem extends Item implements IStructureUpgradeItem
{
	public Class<? extends StructureUpgradeEntity> structureClass;
	
	public BasicStructureUpgradeItem(String name, String texture, int maxStack, Class<? extends StructureUpgradeEntity> theClass)
	{
		setUnlocalizedName(name);
		iconString = texture;
		maxStackSize = maxStack;
		structureClass = theClass;
	}

	@Override
	public StructureUpgradeEntity getUpgradeEntity(ItemStack stack, EntityPlayer player, World world, int x, int y, int z) 
	{
		try 
		{
			return structureClass.newInstance();
		} 
		catch (Exception e) 
		{
			MagicAndIndustry.logger.error("Error creating a " + structureClass.getName() + ": " + e.toString());
		}
		// finally
		return null;
	}
}
