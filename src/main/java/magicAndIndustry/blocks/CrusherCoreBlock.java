package magicAndIndustry.blocks;

import magicAndIndustry.Textures;
import magicAndIndustry.Utils;
import magicAndIndustry.machines.MachineTier;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CrusherCoreBlock extends MachineCoreBlock 
{
	/**
	 * Woooooooorrrrrrrkkiiiiiiiiiinnnnggg...
	 */
	private boolean isWorking;
	
	public CrusherCoreBlock(MachineTier tier, boolean working)
	{
		super(Material.rock, tier, "crusher");
		isWorking = working;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		String prefix = Textures.block(tier.name + "_" + machineID);
		textureFront = reg.registerIcon(isWorking ? prefix + "_lit" : prefix + "_front");
		blockIcon = reg.registerIcon(tier.getFaceTexture());
		textureBottom = reg.registerIcon(tier.getAltTexture());
		textureSide = reg.registerIcon(tier.getStripedTexture());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side == 0) return blockIcon;
		if (side == 1) return textureBottom;
		if (side == Utils.sideFromMeta(meta)) return textureFront;
		return textureSide;
	}
	
	public void onDisplayTick()
	{
		// Particle effects????
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return null;
	}

}
