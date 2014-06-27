package magicAndIndustry;

import magicAndIndustry.machines.structure.BlockPosition;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Used by machine structures to specify blocks in a machine structure, given the ForgeDirection the machine is facing.
 * <br/>
 * Behind: blocks behind the machine. If it faces NORTH, these are to the SOUTH.
 * Height: blocks on top of the machien. If this is positive, they are ABOVE.
 * Side: Blocks to the left/right. If these are positive, they are to the RIGHT of the direction I think.
 */
public class RelativeFaceCoords 
{
	public int behind, height, side;
	
	public RelativeFaceCoords(int relBehind, int relAbove, int relSide)
	{
		behind = relBehind; height = relAbove; side = relSide;
	}
	
	public BlockPosition getPosition(ForgeDirection rotation, int startX, int startY, int startZ)
	{
		int modX = rotation.offsetX, modZ = rotation.offsetZ;
		boolean isXAxis;
		if (modX == 0) { modX = 1; isXAxis = false; }
		else { modZ = 1; isXAxis = true; }
		
		return new BlockPosition(startX + (modX * (isXAxis? behind : side)),
			startY + height, startZ + (modZ * (isXAxis? side : behind)));
	}
}
