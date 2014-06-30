package magicAndIndustry;

import magicAndIndustry.rendering.IConnectedTexture;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class TextureSide
{
	public ForgeDirection blockSide;
	
	public ForgeDirection stripSide;
	
	public ForgeDirection cornerSide;
	
	public TextureSide(ForgeDirection blockside, ForgeDirection stripside)
	{
		blockSide = blockside; stripSide = stripside;
		cornerSide = ForgeDirection.UNKNOWN;
	}
	
	public TextureSide(ForgeDirection blockside, ForgeDirection stripside, ForgeDirection cornerside)
	{
		blockSide = blockside; stripSide = stripside;
		cornerSide = cornerside;
	}
	
	public boolean isCorner() { return cornerSide == ForgeDirection.UNKNOWN; }
	
	public static class Sides
	{
		public static final TextureSide[] allSides = new TextureSide[]
				{
					
				};
	}
	
	public static boolean shouldRender(TextureSide side, IConnectedTexture originalBlock, IBlockAccess world, int x, int y, int z)
	{
		return true;
	}
}
