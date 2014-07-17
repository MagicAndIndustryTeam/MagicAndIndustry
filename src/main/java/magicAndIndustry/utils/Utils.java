package magicAndIndustry.utils;

import java.util.Random;

import net.minecraft.block.BlockPistonBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Utils 
{
	/** The ID of the mod. Forge recommends all lowercase IDs. */
	public static final String ModID = "magicandindustry";
	
	/** Whee, storing versions as strings! */
	public static final String Version = "0.0.4";
	
	// Toolmaterials: This code can be moved if Utils gets too full.
	
	// Here are Minecraft's ToolMaterials:
	// Efficiency is a multiplier of how fast it digs
	// Wood: harvest=0,  durable=???,efficiency=2, damage=???, ench=:|
	// Stone: harvest=1, durable=???,efficiency=4, damage=???, ench=:|
	// Iron: harvest=2, durable=250, efficiency=6.0, damage=2.0, ench=14
	// Diamond: harvest=3, dur=1521, efficiency=8.0, damage=3.0, ench=10
	// Gold: harvest=0, durable= 32, efficiency=12., damage=0.0, ench=22
	
	public static ToolMaterial steelMaterial = EnumHelper.addToolMaterial("materialMAISteel",              3, 1024, 8.0F, 2.5F, 14);
	public static ToolMaterial pigIronMatieral = EnumHelper.addToolMaterial("materialMAIPigIron",          2,   64, 5.0F, 1.5F,  8);
	public static ToolMaterial meteoricIronMaterial = EnumHelper.addToolMaterial("materialMAIMeteoricIron",2,  786, 6.0F, 2.5F, 20);
	public static ToolMaterial copperMaterial = EnumHelper.addToolMaterial("materialMAICopper",            2,  190, 4.0F, 1.5F,  8);
	public static ToolMaterial magicMaterial = EnumHelper.addToolMaterial("magicalMaterial",               3, 1220, 7.0F, 2.5F, 17);
	// titanium, tungsten
	
	/**
	 * Returns which ForgeDirection the meta refers to!!!
	 * @param meta The metadata of the block
	 * @return The ForgeDirection corresponding to the front.
	 */
	public static int sideFromMeta(int meta)
	{
		/*
		 * 0 -> 2
		 * 1 -> 5
		 * 2 -> 3
		 * 3 -> 4
		 */
		
		switch (meta)
		{
			case 0: return 3;
			case 1: return 4;
			case 2: return 2;
			case 3: return 5;
			// 4: return 1, 5: return 0?
			default: return 0;
		}
		/*
		if (meta == 1) return 5;
		if (meta == 0) return 2;
		return meta + 1;*/
	}
	
	public static int backFromMeta(int meta)
	{
		return ForgeDirection.OPPOSITES[sideFromMeta(meta)];
	}
	
	public static int metaFromPlayer(EntityLivingBase placer)
	{
		return MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
	}
	
	/**
	 * Uses piston code to place a block that can face up/down.
	 * @param x X coord of block
	 * @param y Y coord of block
	 * @param z Z coord of block
	 * @param placer Placer of block
	 * @return metadata of block. Uses 3 bits.
	 */
	public static int superMetaFromPlayer(int x, int y, int z, EntityLivingBase placer)
	{
		return BlockPistonBase.determineOrientation(placer.worldObj, x, y, z, placer);
	}
	
	@SideOnly(Side.CLIENT)
	public static String Translate(String input)
	{
		return StatCollector.translateToLocal(input);
	}

	public static void print(String string)
	{
		System.out.println(string);
	}
	
	public static void print(String string, Object... args)
	{
		System.out.printf(string, args); System.out.println();
	}
	
	public static void dropItem(ItemStack stack, World world, int x, int y, int z)
	{
		dropItem(stack, world, null, x, y, z, ForgeDirection.UNKNOWN, new Random());
	}
	
	public static void dropItem(ItemStack stack, World world, EntityPlayer player, int startX, int startY, int startZ, ForgeDirection side, Random rand)
	{
		// Create random offsets 0 ~< offset ~< 1
		float x = rand.nextFloat() * 0.8F + 0.1F,
				y = rand.nextFloat() * 0.8F + 0.1F, 
				z = rand.nextFloat() * 0.8F + 0.1F;

		// Create entity item.
		// If side != forgeDirection.unknowon, they'll be closer to the side.
		EntityItem entity = new EntityItem(world, 
				startX + x + (0.2D * side.offsetX),
				startY + y + (0.2D * side.offsetY), 
				startZ + z + (0.2D * side.offsetZ), stack);

		// Copy tag compound from the stack
		if (stack.hasTagCompound())
			entity.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());

		// set motion
		float offset = 0.05F;
		entity.motionX = rand.nextGaussian() * offset;
		entity.motionY = rand.nextGaussian() * offset + 0.2F;
		entity.motionZ = rand.nextGaussian() * offset;

		// Add to player if specified
		if (player != null) player.capturedDrops.add(entity);
		
		// Spawn the entity - CALLING IT WORLD.SPAWNENTITY MAKES NO SENSE YOU HAVE TO SAY IN WORLD BECAUSE JAVA DAMMIT
		// SO USEFUL I WOULDN'T HAVE THOUGHT AN INSTANCE WORLD.SPAWNENTITY WOULD SPAWN IT IN THE WORLD, THANK YOU FOR
		// SPECIFYING THAT THIS ENTITY SPAWNED IN YOUR SPAWN METHOD IS IN FACT SPAWNED IN THIS VERY WORLD INSTANCE!!!!
		world.spawnEntityInWorld(entity);
	}
}
