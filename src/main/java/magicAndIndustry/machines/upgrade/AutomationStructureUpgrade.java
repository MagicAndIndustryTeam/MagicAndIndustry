package magicAndIndustry.machines.upgrade;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import magicAndIndustry.machines.StructureUpgrade;
import magicAndIndustry.tileEntity.StructureTileEntity;
import magicAndIndustry.tileEntity.StructureUpgradeEntity;

public class AutomationStructureUpgrade extends StructureUpgrade
{
	public AutomationStructureUpgrade()
	{
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tags)
	{

	}

	@Override
	public ItemStack GetItemStack(boolean isBreaking)
	{
		return null;
	}

	@Override
	public StructureUpgradeEntity getTileEntity()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IIcon getBlockOverlay()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
