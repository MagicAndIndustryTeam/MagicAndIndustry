package magicAndIndustry.blocks;

import java.util.Random;

import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.Textures;
import magicAndIndustry.Utils;
import magicAndIndustry.gui.GuiHandler;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.tileEntity.FurnaceCoreEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SuppressWarnings("unused")
public class FurnaceCoreBlock extends MachineCoreBlock
{
	private final Random rand = new Random();
	private final boolean isBurningFurnace;
	private static boolean updatingFurnace = false;
	
	public FurnaceCoreBlock(MachineTier tier, boolean burning)
	{
		super(Material.rock, tier, "furnace");
		isBurningFurnace = burning;
		// Both blocks have same block name
		setBlockName(tier.name + "_furnace");
		if (burning) setLightLevel(0.875F);
	}
	
	// Item Overrides for burning furnace compatibility
	
	@Override
	// TODO compatibility
	public Item getItemDropped(int arg, Random rand, int otherArg)
	{
		return Item.getItemFromBlock(BlockRegistrar.furnaceForType(tier.name));
	}
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return Item.getItemFromBlock(BlockRegistrar.furnaceForType(tier.name));
	}
	
	@Override
	// TODO compatibility
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitx, float hity, float hitz)
	{
		// onBlockActivated does not arrive if the player is sneaking
		if (world.isRemote) return true;
		
		System.out.println("Getting furnace core...");
		FurnaceCoreEntity furnace = (FurnaceCoreEntity)world.getTileEntity(x, y, z);
		if (furnace != null)
			player.openGui(MagicAndIndustry.instance, GuiHandler.FURNACE, world, x, y, z);
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack)
	{
		world.setBlockMetadataWithNotify(x, y, z, Utils.metaFromPlayer(placer), 2);
	}
	
	/*
	@Override
	public IIcon getIcon(int side, int meta)
	{
		// Standard front-facing metadata
		if (side == Utils.sideFromMeta(meta)) return front;
		if (side == 1) return top;
		if (side == 0) return bottom;
		return textureSide;
	}*/
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		super.registerBlockIcons(reg);
		
		if (isBurningFurnace)
			super.textureFront = reg.registerIcon(Textures.block(tier.name + "_furnace_lit"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		// Get the metadata from the block and convert it into a forgedirection
		int metaSide = Utils.sideFromMeta(world.getBlockMetadata(x, y, z));
		
		// If the block's isOnFire thing has been called
		if (isBurningFurnace)
		{
			// I was thinking of calling a check of the tile entity but I guess it'd be fine
			
			float startX = (float)x + 0.4F;
			// Y distributed to be on the heat plate
			float startY = (float)y + (rand.nextFloat() * 6.0F / 16.0F) - 0.2F;
			float startZ = (float)z + 0.4F;
			
			// Usually moves the particles in the block, but in this case
			// we're rendering in the center of the furnace!
			
			float variance = rand.nextFloat() * 0.6F - 0.3F;
			// In this case the block difference is random too,
			// the particles should come from random spots on the furnace.
			float blockDifference = 1.0F+ (variance / 2F);
			
			// World.spawnParticle(x, y, z, velX, velY, velZ)
			
			// Move the variables: one coord should be adjusted based on
			// the direction, the other one should be slid on the side somewhere.
			if (metaSide == ForgeDirection.SOUTH.ordinal())
			{
				startX += variance; startZ -= blockDifference;
			}
			else if (metaSide == ForgeDirection.NORTH.ordinal())
			{
				startX += variance; startZ += blockDifference;
			}
			else if (metaSide == ForgeDirection.EAST.ordinal())
			{
				startX -= blockDifference; startZ += variance;
			}
			else if (metaSide == ForgeDirection.WEST.ordinal())
			{
				startX += blockDifference; startZ += variance;
			}
			
			// Make lots of particles
			for (int i = 0; i < 10; i++)
			{
				// Spawn one of each particle.
				// TODO make this more dramatic.
				// Also we'll want a light-giving-off block in the middle probably.
				for (int j = 0; i < 2; i++)
					world.spawnParticle("largesmoke", startX + rand.nextFloat() * 6.5F / 16F, startY, startZ+ rand.nextFloat() * 6.5F / 16F, 0, 0.1, 0);
				world.spawnParticle("flame", startX+ rand.nextFloat() * 6.5F / 16F, startY, startZ+ rand.nextFloat() * 6.5F / 16F, 0, 0.05, 0);
			}
			
			// This is how we get the vanilla particles as well
			//Blocks.furnace.randomDisplayTick(world, x, y, z, rand);
		}
	}

	/*
	@Override
	// TODO dynamify entity
	public TileEntity createNewTileEntity(World var1, int meta) 
	{
		return new FurnaceCoreEntity(furnaceType);
	}
	*/

	//@Override
	// TODO dynamify entity
	public void OnWrenched2(EntityPlayer player, World world, int x, int y, int z, int meta, int side) 
	{
		// Code is not kept here.
		if (!world.isRemote)
		{
			//FurnaceCoreEntity furnaceEntity = (FurnaceCoreEntity)world.getTileEntity(x, y, z);
		
			//if (furnaceEntity != null)
				//furnaceEntity.updateStructure();
		}
	}
	
	// TODO dynamify!
	public static void setFurnaceState(boolean weStartedTheFire, MachineTier tier, World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		TileEntity furnace = world.getTileEntity(x, y, z);
		
		updatingFurnace = true;
		if (weStartedTheFire)
			world.setBlock(x, y, z, BlockRegistrar.litFurnaceForType(tier.name));
		else world.setBlock(x, y, z, BlockRegistrar.furnaceForType(tier.name));
		updatingFurnace = false;
		
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
		
		if (furnace != null)
		{
			furnace.validate();
			world.setTileEntity(x, y, z, furnace);
		}
	}
	
	// Comparator override: should mostly be handled by rs ports/etc anyway.
	
	public boolean hasComparatorInputOverride()
	{
		return true;
	}
	
	public int getComparatorInputOverride(World world, int x, int y, int z, int meta)
	{
		return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(x, y, z));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return null;
	}
}
