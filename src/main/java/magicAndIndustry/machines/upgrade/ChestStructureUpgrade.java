package magicAndIndustry.machines.upgrade;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import magicAndIndustry.machines.StructureUpgrade;
import magicAndIndustry.tileEntity.base.StructureUpgradeEntity;

public class ChestStructureUpgrade extends StructureUpgrade 
{
	@Override
	public StructureUpgradeEntity getTileEntity(ItemStack usedItem) 
	{
		return null;
	}

	@Override
	public IIcon getBlockOverlay() 
	{
		return null;
	}

}
