package magicAndIndustry.blocks;

import magicAndIndustry.api.IStructureAware;
import magicAndIndustry.api.IStructureUpgradeItem;
import magicAndIndustry.blocks.base.IWrenchable;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.tileEntity.StructureEntity;
import magicAndIndustry.tileEntity.base.StructureUpgradeEntity;
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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class StructureBlock extends BlockContainer implements IWrenchable, IStructureAware
{
	@SideOnly(Side.CLIENT)
	public IIcon striped;
	
	/** The type of block - iron, steel, etc. */
	public final MachineTier tier;
	
	public StructureBlock(MachineTier theTier)
	{
		// Standard tier construction.
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
		// Structure Upgrade renderers are handled seperately.
		if (side > 1 && meta == 2) return striped;
		return blockIcon;
	}

	@Override
	public void OnWrenched(EntityPlayer player, World world, int x, int y, int z, int meta, int side) 
	{
		if (world.isRemote) return;
		
		// Pass wrenched on to tile entity
		TileEntity struct = world.getTileEntity(x, y, z);
		if (struct != null && struct instanceof StructureUpgradeEntity)
				((StructureUpgradeEntity)struct).onWrenched(player, side);
		
		else if (struct instanceof StructureEntity)
		{
			StructureEntity strutE = (StructureEntity)struct;
			player.addChatMessage(new ChatComponentText("Core X=" + strutE.coreX + ", Y=" + strutE.coreY + ", Z=" + strutE.coreZ + ", hasCore=" + strutE.hasCore()));
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		// Call block break in structure upgrade entities.
		TileEntity struct = world.getTileEntity(x, y, z);
		if (struct != null && struct instanceof StructureUpgradeEntity)
			((StructureUpgradeEntity)struct).onBlockBroken();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz)
	{
		// Metadata for client: 0=none, 1=has core, 2=striped
		// TODO this check isn't reflected in the world.
		if (world.isRemote) return world.getBlockMetadata(x, y, z) != 0;
		
		TileEntity te = world.getTileEntity(x, y, z);
		
		// Send block activated to structure upgrades.
		if (te instanceof StructureUpgradeEntity)
			return ((StructureUpgradeEntity)te).onBlockActivated(world, player, side, hitx, hity, hitz);
		
		// Handle upgrade clicks on the block
		if (te instanceof StructureEntity)
		{
			StructureEntity structEnt = (StructureEntity)te;
			ItemStack held = player.getHeldItem();
			
			if (held != null && held.getItem() instanceof IStructureUpgradeItem)
			{
				// Get the structure upgrade for the entity
				StructureUpgradeEntity entity = ((IStructureUpgradeItem)held.getItem()).getUpgradeEntity(held, player, world, x, y, z);
				if (entity != null)
				{
					// This is untested and could require placing a new block.
					world.removeTileEntity(x, y, z);
					world.setTileEntity(x, y, z, entity);
				}
				return true;
			}
			// Additional check for core
			else if (((StructureEntity) te).hasCore())
			{
				// Right click on core from blocks.
				Block brock = world.getBlock(structEnt.coreX, structEnt.coreY, structEnt.coreZ);
				if (brock != null) 
					return brock.onBlockActivated(world, structEnt.coreX, structEnt.coreY, structEnt.coreZ, player, side, hitx, hity, hitz);
				
				return false; // brock is null
			}
			// Structure upgrades have no other item code, and wrenching should pass through separately.
		}
		
		// If there's no tile entity, which shouldn't happen.
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new StructureEntity();
	}
	
	// Machine structure handling

	@Override
	public void onStructureCreated(World world, int x, int y, int z, int coreX, int coreY, int coreZ) 
	{
		TileEntity te = world.getTileEntity(x, y, z); if (te == null) return;
		
		// Notify clients with 1: has core
		world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		
		// Set structure core ref stuff
		if (te instanceof StructureEntity)
			((StructureEntity)te).setCoreValues(coreX, coreY, coreZ);
		
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
		
		if (te != null && te instanceof StructureEntity)
			((StructureEntity)te).setCoreValues(0, 0, 0);
	}

}
