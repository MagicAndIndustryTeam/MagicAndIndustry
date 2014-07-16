package magicAndIndustry.machines.upgrade;

import magicAndIndustry.machines.StructureUpgrade;
import magicAndIndustry.tileEntity.StructureEntity;
import magicAndIndustry.tileEntity.base.StructureUpgradeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TankStructureUpgrade extends StructureUpgrade
{	
	public static final String ID = "MAI:Tank";
	
	public TankStructureUpgrade()
	{
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getBlockOverlay()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StructureUpgradeEntity getTileEntity(ItemStack usedItem) 
	{
		return new TankUpgradeEntity();
	}

	@Override
	public String getID() { return ID; }

}
