package magicAndIndustry.rendering;

import magicAndIndustry.TextureSide;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IConnectedTexture
{
	public float getTextureWidth();

	// TODO add blockaccess
	public IIcon getBaseTexture(int side, int meta);

	public IIcon getWallsTexture();
	
	public boolean shouldConnect(ForgeDirection side, TextureSide textSide, int meta);
	
	/**
	 * Used to determine if two blocks should connect: if two blocks have the same TextureID they will mesh.
	 */
	public String getTextureID(int side, int meta);
}
