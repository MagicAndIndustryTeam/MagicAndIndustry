package magicAndIndustry.machines.upgrade;

import magicAndIndustry.tileEntity.base.StructureUpgradeEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TestUpgradeEntity extends StructureUpgradeEntity 
{
	public TestUpgradeEntity()
	{
		
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
	}

	@Override
	public ItemStack getUpgradeStack(EntityPlayer player, boolean wrenched) 
	{
		return null;
	}
	
	@Override
	public boolean onBlockActivated(World world, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		return true;
	}

}
