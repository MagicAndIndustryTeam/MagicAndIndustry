package magicAndIndustry.machines.upgrade;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import magicAndIndustry.machines.StructureUpgrade;
import magicAndIndustry.tileEntity.StructureEntity;
import magicAndIndustry.tileEntity.StructureTileEntity;
import magicAndIndustry.tileEntity.base.StructureUpgradeEntity;

public class AutomationStructureUpgrade extends StructureUpgrade
{
	public static final String ID = "MAI:Automation";
	
	public AutomationStructureUpgrade()
	{
		
	}
	
	@Override
	public IIcon getBlockOverlay()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StructureUpgradeEntity getTileEntity(ItemStack usedItem) 
	{
		return null;
	}

	@Override
	public String getID() { return ID; }
}
