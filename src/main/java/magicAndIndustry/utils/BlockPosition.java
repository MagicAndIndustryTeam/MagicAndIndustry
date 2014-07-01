package magicAndIndustry.utils;

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
		x += dir.offsetX; y += dir.offsetY; x += dir.offsetZ;
		return this;
	}
	
	public static BlockPosition fromOffset(BlockPosition offset, int startX, int startY, int startZ)
	{
		return new BlockPosition(offset.x + startX, offset.y + startY, offset.z + startZ);
	}
	
	public static BlockPosition getOffset(int startX, int startY, int startZ, int remoteX, int remoteY, int remoteZ)
	{
		//return new BlockPosition(startX - remoteX, startY - remoteY, startZ - remoteZ);
		return new BlockPosition(remoteX - startX, remoteY - startY, remoteZ - startZ);
	}
	
	@Override
	public String toString()
	{
		return x + ", " + y + ", " + z;
	}
}
