package magicAndIndustry.rendering;

import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IConnectedTexture
{
	public float getTextureWidth();

	// TODO add blockaccess
	public IIcon getBaseTexture(int side, int meta);

	public IIcon getWallsTexture();
}
