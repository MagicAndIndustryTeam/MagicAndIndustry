package magicAndIndustry.tileEntity;

import magicAndIndustry.machines.upgrade.TankStructureUpgrade;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TankUpgradeEntity extends StructureUpgradeEntity implements IFluidHandler
{
	public TankUpgradeEntity()
	{
		
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		TankStructureUpgrade tankUpgrade = (TankStructureUpgrade)upgrade;
		int amount = tankUpgrade.tank.fill(resource, doFill);
		
		if (amount > 0 && doFill)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
		return amount;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		FluidStack amount = ((TankStructureUpgrade)upgrade).tank.drain(maxDrain, doDrain);
		if (amount != null && doDrain)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
		return amount;
	}

	// Tinker's Construct didn't implement these either!
	// P.S. thank you MDiyo you are awesome
	
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		FluidStack fluid = null;
		FluidTank tank = ((TankStructureUpgrade)upgrade).tank;
		
		if (tank.getFluid() != null)
			fluid = tank.getFluid().copy();
		
		return new FluidTankInfo[] { new FluidTankInfo(fluid, tank.getCapacity()) };
	}
	
}
