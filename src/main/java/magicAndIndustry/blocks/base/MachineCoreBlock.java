package magicAndIndustry.blocks.base;

import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.tileEntity.base.MachineCoreEntity;
import magicAndIndustry.utils.Textures;
import magicAndIndustry.utils.Utils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class MachineCoreBlock extends BlockContainer implements IWrenchable
{
	public final MachineTier tier;
	
	public String machineID;
	
	@SideOnly(Side.CLIENT)
	public IIcon textureFront, textureBottom, textureSide, textureBack;
	
	public MachineCoreBlock(Material mat, MachineTier theTier, String machineID)
	{
		super(mat); this.machineID = machineID;
		this.tier = theTier;
		setHardness(theTier.standardHardness); 
		setResistance(theTier.standardResistance);
		setBlockName(theTier.name + "_" + machineID);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		String prefix = Textures.block(tier.name + "_" + machineID);
		
		textureFront = reg.registerIcon(prefix + "_front");
		blockIcon = reg.registerIcon(tier.getFaceTexture());
		textureBottom = reg.registerIcon(tier.getAltTexture());
		textureSide = reg.registerIcon(tier.getStripedTexture());
		textureBack = reg.registerIcon(prefix + "_back");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (Utils.backFromMeta(meta) == side) return textureBack;
		if (Utils.sideFromMeta(meta) == side) return textureFront;
		if (side == ForgeDirection.DOWN.ordinal()) return textureBottom;
		if (side == ForgeDirection.UP.ordinal()) return blockIcon;
		return textureSide;
	}

	@Override
	public void OnWrenched(EntityPlayer player, World world, int x, int y, int z, int meta, int side)
	{
		// Don't let sneak wrench, clients, or already built machines get in da way.
		if (player.isSneaking() || world.isRemote) return;
		
		Utils.print("Began machine core wrench structure.");
		
		TileEntity ent = world.getTileEntity(x, y, z);
		if (ent != null && ent instanceof MachineCoreEntity)
		{
			MachineCoreEntity macEnt = (MachineCoreEntity)ent;
			// Wrenching only sets structure when it's not set.
			if (macEnt != null && !macEnt.structureComplete())
				macEnt.updateStructure();
		}
		
		// Adds to the previous log.
		//else MagicAndIndustry.logger.error("MachineCoreBlock at %1$i, %2$i, %3$i was unable to load its machine - ID " + machineID + " on wrench event from " + player.getDisplayName(), x, y, z);
	}
}
