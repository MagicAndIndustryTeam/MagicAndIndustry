package magicAndIndustry.machines.upgrade;

import magicAndIndustry.tileEntity.base.StructureUpgradeEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TankUpgradeEntity extends StructureUpgradeEntity implements IFluidHandler
{
	// Large, armored, weaponized military vehicle for storing Forge fluids.
	private FluidTank tank;
	
	public TankUpgradeEntity()
	{
		super(TankStructureUpgrade.ID);
		// Start up the tank, vroom vroom
		tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 4);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		// Check if anyone was sitting in the tank.
		if (tag.hasKey("FluidID") && tag.hasKey("Amount"))
			tank.setFluid(new FluidStack(tag.getInteger("FluidID"), tag.getInteger("Amount")));
		else tank.setFluid(null);
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		// You turn the tank upside down and shake it
		// to make the fluid pour out of the top hatch.
		
		FluidStack contents = tank.getFluid();
		
		if (contents != null)
		{
			tag.setInteger("FluidID", contents.fluidID);
			tag.setInteger("Amount", contents.amount);
		}
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		int amount = tank.fill(resource, doFill);
		
		if (amount > 0 && doFill)
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		
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
		FluidStack amount = tank.drain(maxDrain, doDrain);
		if (amount != null && doDrain)
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		
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
		
		if (tank.getFluid() != null)
			fluid = tank.getFluid().copy();
		
		return new FluidTankInfo[] { new FluidTankInfo(fluid, tank.getCapacity()) };
	}
	
}
