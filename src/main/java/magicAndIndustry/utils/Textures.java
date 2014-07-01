package magicAndIndustry.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Textures
{
	private static String id = Utils.ModID + ':';
	public static String item(String name)
	{
		if(name.indexOf(':') != -1) return name;
		return id + name;
		//return id /*+ "/textures/itemss/"*/ + name; // + ".png";
	}
	
	public static String item(String name, String postfix)
	{
		return item(name + "_" + postfix);
	}
	
	public static String item(Item item)
	{
		return item(item.getUnlocalizedName().substring(5));
	}
	
	public static String block(String name)
	{
		if (name.indexOf(':') != -1) return name;
		return id + name;
		//return id /*+ "/textures/blocks/"*/ + name; // + ".png";
	}
	
	public static String block(String name, String postfix)
	{
		return block(name + "_" + postfix);
	}
	
	public static String block(Block brock)
	{
		return block(brock.getUnlocalizedName().substring(5));
	}
	
	public static String gui(String name)
	{
		return id + "/gui/" + name + ".png";
	}
}
