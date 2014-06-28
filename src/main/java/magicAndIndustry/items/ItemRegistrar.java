package magicAndIndustry.items;

import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.Textures;
import magicAndIndustry.Utils;
import magicAndIndustry.blocks.BlockRegistrar;
import magicAndIndustry.tools.RecursivePick;
import magicAndIndustry.tools.Wrench;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistrar 
{
	public static void RegisterDemItems() 
	{
		// Books
		bookOScience = doBook("o_science"); // hehe, OScience.
		bookEngineering = doBook("engineering");
		bookChemistry = doBook("chemistry");
		bookBiology = doBook("biology");
		bookPhysics = doBook("physics");
		//bookMagic = doBook("Magic");
		
		// Ingots
		ingotSteel = doIngot("steel");
		ingotMeteorIron = doIngot("meteoric_iron");
		ingotAluminum = doIngot("aluminum");
		ingotTitanium = doIngot("titanium");
		//ingotCopper = doIngot("Copper");
		//ingotTin = doIngot("Tin");
		//ingotBronze = doIngot("Bronze");
		
		// Dusts?
		piglets = doItem("piglets", true, true);
		
		// Tools
		wrench = doItem(new Wrench());
		recursivePick = doItem(new RecursivePick());
		weldingTorch = doItem(new WeldingTorch());
		
		bagOfWinds = doItem(new BagOfWinds());
		emptyBag = doItem("empty_bag", true, false);
		voidBucket = doItem(new VoidBucket());
		// Shovels
		steelShovel = doTool(Utils.steelMaterial, "steel_shovel");
		meteorShovel = doTool(Utils.meteoricIronMaterial, "meteor_shovel");
		// Pickaxes
		steelPickaxe = doTool(Utils.steelMaterial, "steel_pickaxe");
		meteorPickaxe = doTool(Utils.meteoricIronMaterial, "meteor_pickaxe");
		// Axes
		steelAxe = doTool(Utils.steelMaterial, "steel_axe");
		meteorAxe = doTool(Utils.meteoricIronMaterial, "meteor_axe");
		
		// Weapons
		steelSword = doTool(Utils.steelMaterial, "steel_sword");
		meteorSword = doTool(Utils.meteoricIronMaterial, "meteor_sword");
		// Special
		longRangeSword = doItem(new LongRangeSword());
		
		// Food
		giflte = doFood("fish_gefilte", 6, 8F, false, false);
		raisin = doFood("raisin", 1, 1F, true, false);
		raisinBunch = doFood("raisin_bunch", 3, 3F, true, false);
		
		// Blocks
		engineeringTable = doItem(new EngineeringTableItem(), false, false); //doItem(new DEFAULT_EngineeringTableItem());
		
		//
		// Engineering
		//
		ironScrew = doItem(new IronScrew());
		
		//
		// Others/fortune blocks
		//
		BlockRegistrar.oreRaisin.setDrop(raisin);
	}
	
	public static Item bookEngineering, bookPhysics, bookChemistry, bookBiology, bookOScience, bookMagic;
	public static Item ingotMeteorIron, ingotSteel, ingotPigIron, piglets;
	public static Item ingotAluminum, ingotTitanium; //ingotCopper, ingotTin, ingotBronze, ingotTitanium;
	public static Item wrench, weldingTorch; 
	public static Item meteorSword, meteorShovel, meteorPickaxe, meteorAxe, steelShovel, steelPickaxe, steelAxe, steelSword;
	public static Item giflte, raisin, raisinBunch;
	public static Item recursivePick, longRangeSword;
	public static Item bagOfWinds, emptyBag, voidBucket;
	public static Item engineeringTable;
	public static Item tape, ironScrew, copperWire;
	
	private static Item doItem(String name, boolean doText, boolean doDict)
	{
		Item it = new Item().setUnlocalizedName(name);
		return doItem(it, doText, doDict);
	}
	
	private static Item doItem(Item item, String name, boolean texture, boolean oredict)
	{
		item.setUnlocalizedName(name);
		return doItem(item, texture, oredict); 
	}
	
	private static Item doItem(Item item, String name) { return doItem(item, name, true, false); }
	
	private static Item doItem(Item item, boolean doTexture, boolean doOreDict)
	{
		item.setCreativeTab(MagicAndIndustry.tab);
		if (doTexture)
			item.setTextureName(Textures.item(item)); 
		// Using .substring(5) here allows for /give player magicandindustry:itemname instead of magicandindustry:item.itemname
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
		return item;
	}

	private static Item doItem(Item item) { return doItem(item, true, false); }
	
	private static Item doItem(String name) { return doItem(name, true, false); }
	
	private static Item doIngot(String name)
	{
		Item item = new Item();
		item.setUnlocalizedName(name + "_ingot");
		item.setTextureName(Textures.item("ingot", name));
		// TODO oredict.
		return doItem(item, false, false);
	}
	
	private static Item doTool(ToolMaterial material, String name) 
	{
		// That's right, convenience.
		if (name.endsWith("sword")) return doItem(new ItemSword(material), name);
		if (name.endsWith("shovel")) return doItem(new ItemSpade(material), name);
		if (name.endsWith("pickaxe")) return doItem(new PickaxeWithConstructor(material), name);
		if (name.endsWith("axe")) return doItem(new AxeWithConstructor(material), name);
		return doItem(name);
	}
	
	private static Item doFood(String name, int hunger, float saturation, boolean alwaysHungerin, boolean wolves)
	{
		ItemFood food = new ItemFood(hunger, saturation, wolves);
		if (alwaysHungerin) food.setAlwaysEdible();
		return doItem(food, name, true, false);
		
	}
	
	private static Item doBook(String name)
	{
		return doItem(new ResearchBook(name), true, false);
	}
}
