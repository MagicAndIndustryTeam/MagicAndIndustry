package magicAndIndustry.blocks;

import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.api.IStructureAware;
import magicAndIndustry.api.IStructureUpgradeItem;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.machines.StructureUpgrade;
import magicAndIndustry.tileEntity.MachineCoreEntity;
import magicAndIndustry.tileEntity.StructureTileEntity;
import magicAndIndustry.tileEntity.StructureUpgradeEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class StructureBlock extends BlockContainer implements IWrenchable, IStructureAware
{
	@SideOnly(Side.CLIENT)
	public IIcon striped; //, surrounded;
	
	/** The type of block - iron, steel, etc. */
	public final MachineTier tier;
	
	public StructureBlock(MachineTier theTier)
	{
		super(Material.rock);
		tier = theTier;
		setBlockName(tier.name + "_structure");
		setHardness(tier.standardHardness);
		setResistance(tier.standardResistance);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		blockIcon = reg.registerIcon(tier.getFaceTexture()); 
		striped = reg.registerIcon(tier.getStripedTexture());
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		//if (meta == 2) return surrounded;
		// TODO check for upgrades!! FUN
		if ((side == ForgeDirection.EAST.ordinal() 
				|| side == ForgeDirection.WEST.ordinal()
				|| side == ForgeDirection.NORTH.ordinal()
				|| side == ForgeDirection.SOUTH.ordinal()) 
				&& meta == 1)
			return striped;
		return blockIcon;
	}
	
	// TODO check for upgrades
	
	@Override
	// TODO use tile entity code
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
	{
		/*
		// Is this needed?????????
		if (world.isRemote) return;
		
		// This is gonna be reset every block change???
		//world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		
		// Let's perform a lot of null checks!1!!11!!!1
		TileEntity ent = world.getTileEntity(x, y, z);
		if (ent != null && ent instanceof StructureTileEntity)
		{
			StructureTileEntity struct = (StructureTileEntity)ent;
			if (struct != null && struct.hasCore())
			{
				// WARNING: referencing core x,y,z not as relative positions!
				TileEntity coreEnt = world.getTileEntity(struct.coreX, struct.coreY, struct.coreZ);
				if (coreEnt != null && coreEnt instanceof MachineCoreEntity)
				{
					MachineCoreEntity machineCore = (MachineCoreEntity)coreEnt;
					if (machineCore != null && machineCore.structureComplete())
						machineCore.updateStructure();
					// TODO structure
				}
			}
		}
		*/
	}

	@Override
	public void OnWrenched(EntityPlayer player, World world, int x, int y, int z, int meta, int side) 
	{
		if (world.isRemote) return;
		TileEntity struct = world.getTileEntity(x, y, z);
		if (struct != null && struct instanceof StructureUpgradeEntity)
				((StructureUpgradeEntity)struct).onWrenched(player, side);
		if (struct != null && struct instanceof StructureTileEntity)
		{
			StructureTileEntity strutE = (StructureTileEntity)struct;
			player.addChatMessage(new ChatComponentText("X=" + strutE.coreX + ", Y=" + strutE.coreY + ", Z=" + strutE.coreZ + ", hasCore=" + strutE.hasCore()));
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		TileEntity struct = world.getTileEntity(x, y, z);
		if (struct != null && struct instanceof StructureUpgradeEntity)
			((StructureUpgradeEntity)struct).onBlockBroken();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz)
	{
		if (world.isRemote) return true;
		
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof StructureUpgradeEntity)
		{
			return ((StructureUpgradeEntity)te).onBlockActivated(player, side, hitx, hity, hitz);
		}
		if (te instanceof StructureTileEntity)
		{
			ItemStack held = player.getHeldItem();
			if (held != null && held.getItem() instanceof IStructureUpgradeItem)
			{
				String id = ((IStructureUpgradeItem)held.getItem()).getStructureUpgradeID();
				
				if (id == null)
				{
					// If this is us I'm so sorry.
					MagicAndIndustry.logger.error("Item " + held.toString() + " does not have a valid IStructureUpgrade implementation! It is bugged, contact its mod author.");
					return true;
				}
				Class<? extends StructureUpgrade> upgradeClass = StructureUpgrade.getUpgradeClassByID(id);
				if (upgradeClass == null)
				{
					// If this is us we'll look soo silly.
					MagicAndIndustry.logger.error("Item " + held.toString() + "'s IStructureUpgrade's upgrade ID - " + id + " - is an unregistered StructureUpgrade! It is bugged, contact its mod author.");
					return true;
				}
				StructureUpgrade upgrade = null;
				try
				{
					upgrade = (StructureUpgrade)upgradeClass.newInstance();
					upgrade.constructFromItemStack(player, player.getHeldItem());
				}
				catch (Exception ex)
				{
					MagicAndIndustry.logger.error("Unable to create " + (upgrade == null ? id : upgrade.toString()) + "from " + player.getGameProfile().getName() + "'s " + player.getHeldItem().toString() + ".");
					return true;
				}
				if (upgrade != null)
				{
					world.removeTileEntity(x, y, z);
					world.setTileEntity(x, y, z, upgrade.getTileEntity());
				}
				return true;
			}
			// Structure upgrades have no other item code, and wrenching should pass through separately.
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) 
	{
		return new StructureTileEntity();
	}

	public void resetStructure(World world, int x, int y, int z) 
	{
		world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		StructureTileEntity ste = (StructureTileEntity)world.getTileEntity(x, y, z);
		if (ste != null) ste.setCoreValues(0, 0, 0);
	}

	@Override
	public void onStructureCreated(World world, int x, int y, int z, int coreX, int coreY, int coreZ) 
	{
		TileEntity te = world.getTileEntity(x, y, z); if (te == null) return;
		
		// Set structure core ref stuff
		if (te instanceof StructureTileEntity)
			((StructureTileEntity)te).setCoreValues(coreX, coreY, coreZ);
		
		if (te instanceof StructureUpgradeEntity)
		{
			// TODO add upgrade here????????
			//MachineCoreEntity core = (MachineCoreEntity)world.getTileEntity(coreX, coreY, coreZ);
		}
			
	}

	@Override
	public void onStructureBroken(World world, int x, int y, int z, int coreX, int coreY, int coreZ) 
	{
		world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof StructureTileEntity)
			((StructureTileEntity)te).setCoreValues(0, 0, 0);
		// Remove upgrade here? Should be automatic
	}

}
