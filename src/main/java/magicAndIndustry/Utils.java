package magicAndIndustry;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
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
}
