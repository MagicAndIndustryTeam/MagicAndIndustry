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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBreaker extends BlockContainer implements IWrenchable
{
	public MachineTier tier;
	
	@SideOnly(Side.CLIENT)
	private IIcon backIcon, sideIcon;
	
	public BlockBreaker(MachineTier tier)
	{
		super(Material.rock);
		setHardness(tier.standardHardness);
		setResistance(tier.standardResistance);
		setStepSound(soundTypePiston);
		setBlockName(tier.name + "_block_breaker");
		this.tier = tier;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		// Block: tier face
		blockIcon = reg.registerIcon(Textures.block(tier.name, "breaker"));
		// Back: tier front
		backIcon = reg.registerIcon(tier.getFaceTexture());
		// Side: tier striped
		sideIcon = reg.registerIcon(Textures.block(tier.name, "side"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		// meta == proper sideyness
		if (side == meta) return blockIcon;
		
		// Back of breaker
		if (side == ForgeDirection.OPPOSITES[meta])
			return backIcon;
		
		return sideIcon;
		
		/*
		// Top: tier standard
		if (side == 0) return blockIcon;
		// Bottom: tier alt
		if (side == 1) return bottomIcon;
		// Front: front icon
		if (Utils.sideFromMeta(meta) == side) return frontIcon;
		// Side: stripe icon
		return sideIcon;
		*/
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack item)
	{
		world.setBlockMetadataWithNotify(x, y, z, Utils.superMetaFromPlayer(x, y, z, placer), 2);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return null; // new BlockBreakerEntity(tier);
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

	@Override
	public void OnWrenched(EntityPlayer player, World world, int x, int y, int z, int meta, int side) 
	{
		if (world.isRemote)
			player.addChatMessage(new ChatComponentText("Block side: " + side + ", meta: " + meta + ", assumed front: " 
					+ Utils.superSideFromMeta(meta) + ", player place: " + Utils.superMetaFromPlayer(x, y, z, player)));
	}
}
