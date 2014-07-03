package magicAndIndustry.machines.upgrade;

import magicAndIndustry.machines.StructureUpgrade;
import magicAndIndustry.tileEntity.StructureEntity;
import magicAndIndustry.tileEntity.StructureUpgradeEntity;
import magicAndIndustry.tileEntity.TankUpgradeEntity;
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
	/** Large, armored, weaponized military vehicle for storing Forge Fluids. */
	public FluidTank tank;
	
	public TankStructureUpgrade()
	{
		// Start up the tank, vroom vroom
		tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 4);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tags)
	{
		// Check if anyone was sitting in the tank when it was saved
		if (tags.hasKey("FluidID") && tags.hasKey("Amount"))
			tank.setFluid(new FluidStack(tags.getInteger("FluidID"), tags.getInteger("Amount")));
		else tank.setFluid(null);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		// You turn the tank upside down and shake it to make the 
		// fluid pour out of the hatch on top.
		FluidStack contents = tank.getFluid();
		
		if (contents != null)
		{
			tag.setInteger("FluidID", contents.fluidID);
			tag.setInteger("Amount", contents.amount);
		}
	}

	@Override
	public ItemStack GetItemStack(boolean brok)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StructureUpgradeEntity getTileEntity()
	{
		return new TankUpgradeEntity();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getBlockOverlay()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
