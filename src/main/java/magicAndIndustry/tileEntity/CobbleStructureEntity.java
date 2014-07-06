package magicAndIndustry.tileEntity;

import magicAndIndustry.blocks.BlockRegistrar;
import net.minecraft.tileentity.TileEntity;

public class CobbleStructureEntity extends TileEntity
{
	public int furnaceX, furnaceY, furnaceZ;
	
	private boolean furnaceFinalized = false;
	
	public void AcceptFurnacePlacement(int x, int y, int z)
	{
		if (worldObj.getBlock(x, y, z).getUnlocalizedName() 
			== BlockRegistrar.cobbleFurnace.getUnlocalizedName())
		{
			furnaceX = x; furnaceY = y; furnaceZ = z;
		}
	}
	
	public void FinalizeFurnacePlacement(int x, int y, int z)
	{
		if (furnaceX == x && furnaceY == y && furnaceZ == z
			&& !furnaceFinalized 
			&& this.worldObj.getBlock(x, y, z).getUnlocalizedName() == 
			BlockRegistrar.cobbleFurnace.getUnlocalizedName())
		{
			furnaceFinalized = true;
		}
	}
	
	// 
}
