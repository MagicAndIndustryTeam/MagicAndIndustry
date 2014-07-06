package magicAndIndustry.blocks;

import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.gui.GuiHandler;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.tileEntity.BlockBreakerEntity;
import magicAndIndustry.utils.Textures;
import magicAndIndustry.utils.Utils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBreaker extends BlockContainer
{
	public MachineTier tier;
	
	@SideOnly(Side.CLIENT)
	private IIcon frontIcon, sideIcon, bottomIcon;
	
	public BlockBreaker(MachineTier tier)
	{
		super(Material.rock);
		setHardness(tier.standardHardness);
		setResistance(tier.standardResistance);
		setStepSound(soundTypePiston);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		// Block: tier face
		blockIcon = reg.registerIcon(tier.getFaceTexture());
		// Bottom: tier alt
		bottomIcon = reg.registerIcon(tier.getAltTexture());
		// Front: block front
		frontIcon = reg.registerIcon(Textures.block(tier.name, "breaker"));
		// Side: tier striped
		sideIcon = reg.registerIcon(tier.getStripedTexture());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		// Top: tier standard
		if (side == 0) return blockIcon;
		// Bottom: tier alt
		if (side == 1) return bottomIcon;
		// Front: front icon
		if (Utils.sideFromMeta(meta) == side) return frontIcon;
		// Side: stripe icon
		return sideIcon;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new BlockBreakerEntity(tier);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz)
	{
		if (!world.isRemote) 
		{
			BlockBreakerEntity bbent = (BlockBreakerEntity)world.getTileEntity(x, y, z);
			if (bbent != null)
				player.openGui(MagicAndIndustry.instance, GuiHandler.BLOCK_BREAKER, world, x, y, z);
		}
		
		return true;
	}
}
