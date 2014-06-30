package magicAndIndustry;

import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPosition 
{
	public int x, y, z;
	
	public BlockPosition(int inX, int inY, int inZ)
	{
		x = inX; y = inY; z = inZ;
	}
	
	public BlockPosition move(ForgeDirection dir)
	{
		x += dir.offsetX; y += dir.offsetY; z += dir.offsetZ;
		return this;
	}
	
	public boolean isValid(IBlockAccess world)
	{
		return y >= 0 && y <= world.getHeight(); 
	}
	
	public boolean isValid() { return y >= 0; }
}
