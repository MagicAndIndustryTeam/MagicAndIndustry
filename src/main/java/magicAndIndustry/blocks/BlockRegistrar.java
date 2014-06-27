package magicAndIndustry.blocks;

import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.Textures;
import magicAndIndustry.Utils;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.tileEntity.AutomationStructureEntity;
import magicAndIndustry.tileEntity.BlockBreakerEntity;
import magicAndIndustry.tileEntity.DiscoBlockEntity;
import magicAndIndustry.tileEntity.EngineeringTableEntity;
import magicAndIndustry.tileEntity.FurnaceCoreEntity;
import magicAndIndustry.tileEntity.StructureTileEntity;
import magicAndIndustry.tileEntity.TankUpgradeEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRegistrar 
{
	public static void RegisterBlocksForKnocks()
	{
		//
		// Ores
		//
		oreRaisin = doFortunateOre(new FortuneOre("raisin", 3F, 20F, 0, 4, 6, 1));
		oreMeteoric = doIngotOre(new IngotOre("meteoric", 3F, 20F, 2));
		oreAluminum = doIngotOre(new IngotOre("aluminum", 2.5F, 20F, 2));
		
		//
		// Compressed Blocks
		//
		blockRaisin = doIngotBlock("raisin", 3F);
		blockRaisinBunch = doIngotBlock("raisin_bunch", 3F);
		blockMeteoric = doIngotBlock("meteoric", 5F);
		blockSteel = doIngotBlock("steel", 5F);
		blockPigIron = doIngotBlock("pig_iron", 5F);
		blockAluminum = doIngotBlock("aluminum", 5F);
		
		
		//
		// Machines
		//
		
		cobbleStructure = doBlock(new StructureBlock(MachineTier.cobble));
		stoneHeatPlate = doBlock(new HeatPlate(MachineTier.cobble), false, false);
		cobbleFurnaceCore = doBlock(new FurnaceCoreBlock(MachineTier.cobble, false).setStepSound(Block.soundTypePiston), false, false);
		cobbleFurnaceCoreLit = doBlock(new FurnaceCoreBlock(MachineTier.cobble, true).setStepSound(Block.soundTypePiston), false, false, false, "cobble_furnace_lit");
		
		// Iron tier
		ironStructure = doBlock(new StructureBlock(MachineTier.iron));
		ironHeatPlate = doBlock(new HeatPlate(MachineTier.iron), false, false);
		ironFurnaceCore = doBlock(new FurnaceCoreBlock(MachineTier.iron, false).setStepSound(Block.soundTypeAnvil), false, false);
		ironFurnaceCoreLit = doBlock(new FurnaceCoreBlock(MachineTier.iron, true).setStepSound(Block.soundTypeAnvil), false, false, false, "iron_furnace_lit");
		ironSlab = doBlock(new MachineSlab(false, MachineTier.iron), false, false);	ironSlab_full = doBlock(new MachineSlab(true, MachineTier.iron), false, false);
		cookedSlab = doBlock(new MachineSlab(false, MachineTier.steel), false, false); cookedSlab_full = doBlock(new MachineSlab(true, MachineTier.steel), false, false);

		// Steel tier
		steelStructure = doBlock(new StructureBlock(MachineTier.steel));
		steelHeatPlate = doBlock(new HeatPlate(MachineTier.steel), false, false);
		steelFurnaceCore = doBlock(new FurnaceCoreBlock(MachineTier.steel, false).setStepSound(Block.soundTypeAnvil), false, false);
		steelFurnaceCoreLit = doBlock(new FurnaceCoreBlock(MachineTier.steel, true).setStepSound(Block.soundTypeAnvil), false, false, false, "steel_furnace_lit");
		//steelSlab = doBlock(new MachineSlab(false, "steel"));
		
		// Others
		industrialGlass = doBlock(new IndustrialGlass());
		cobbleStructureSafe = doBlock(new SafeStructure(MachineTier.cobble), false, false, false);
		ironStructureSafe = doBlock(new SafeStructure(MachineTier.iron), false, false, false);
		steelStructureSafe = doBlock(new SafeStructure(MachineTier.steel), false, false, false);
		
		//
		// Research Main Things
		//
		engineeringTable = doBlock(new EngineeringTable()); //doBlock(new DEFAULT_EngineeringTable());
		
		
		//
		// Randoms
		//
		discoBlock = doBlock(new DiscoBlockBlock());
		
		
		// 
		// Register Tile Entities
		//
		GameRegistry.registerTileEntity(StructureTileEntity.class, "MAIstructure");
		GameRegistry.registerTileEntity(TankUpgradeEntity.class, "MAItankUpgradey");
		GameRegistry.registerTileEntity(AutomationStructureEntity.class, "MAIautomationStructure");
		GameRegistry.registerTileEntity(EngineeringTableEntity.class, "MAIengineeringTable");
		GameRegistry.registerTileEntity(FurnaceCoreEntity.class, "MAIfurnace");
		GameRegistry.registerTileEntity(BlockBreakerEntity.class, "MAIBlockBreaker");
		GameRegistry.registerTileEntity(DiscoBlockEntity.class, "MAIdiscoBlock");
	}
	
	private static Block doBlock(Block brock, boolean oneTextureToRuleThemAll, boolean oreDict, boolean creativeTab, String registerName)
	{
		if (creativeTab)
			brock.setCreativeTab(MagicAndIndustry.tab);
		if (oneTextureToRuleThemAll)
			brock.setBlockTextureName(Textures.block(brock));
		
		// Using .substring(5) allows for /give player magicandindustry:blockname instead of magicandindustry:tile.blockname
		GameRegistry.registerBlock(brock, registerName);
		return brock;
	}
	
	private static Block doBlock(Block brock, boolean texture, boolean oreDict, boolean creative)
	{
		return doBlock(brock, texture, oreDict, creative, brock.getUnlocalizedName().substring(5));
	}
	
	private static Block doBlock(Block brock, boolean texture, boolean ordict)
	{
		return doBlock(brock, texture, ordict, true, brock.getUnlocalizedName().substring(5));
	}
	
	private static Block doBlock(Block brock)
	{
		return doBlock(brock, true, false, true, brock.getUnlocalizedName().substring(5));
	}
	
	private static IngotOre doIngotOre(IngotOre ore)
	{
		return (IngotOre)doBlock(ore, false, true); //, true, ore.getUnlocalizedName().substring(5));
	}

	private static FortuneOre doFortunateOre(FortuneOre ore)
	{
		return (FortuneOre)doBlock(ore, false, true); //, true, ore.getUnlocalizedName().substring(5));
	}
	
	private static Block doIngotBlock(String name, float hardness)
	{
		Block brock = new Block_Exposing_Constructor(Material.rock);
		brock.setBlockName(name + "_block");
		// Keep textures organized.
		brock.setBlockTextureName(Textures.block("block", name));
		brock.setHardness(hardness); brock.setResistance(30);
		return doBlock(brock, false, true);
	}
	
	public static Block furnaceForType(String type)
	{
		if (type.equals("cobble")) return cobbleFurnaceCore;
		if (type.equals("steel")) return steelFurnaceCore;
		if (type.equals("iron")) return ironFurnaceCore;
		return cobbleFurnaceCore;
	}
	
	public static Block litFurnaceForType(String type) 
	{
		if (type.equals("cobble")) return cobbleFurnaceCoreLit;
		if (type.equals("steel")) return steelFurnaceCoreLit;
		if (type.equals("iron")) return ironFurnaceCoreLit;
		return cobbleFurnaceCoreLit;
	}
	
	public static Block machineSlabForType(String type)
	{
		if (type.equals("steel")) return cookedSlab_full;
		return ironSlab_full;
	}
	public static Block cobbleStructure, cobbleFurnaceCore, cobbleFurnaceCoreLit, stoneHeatPlate;
	public static Block ironStructure, ironFurnaceCore, ironFurnaceCoreLit, ironHeatPlate;
	public static Block steelStructure, steelFurnaceCore, steelFurnaceCoreLit, steelHeatPlate;
	public static Block ironSlab, cookedSlab, ironSlab_full, cookedSlab_full;
	public static Block cobbleStructureSafe, ironStructureSafe, steelStructureSafe;
	public static IngotOre oreMeteoric, oreAluminum;
	public static FortuneOre oreRaisin;
	public static Block blockRaisin, blockSteel, blockRaisinBunch, blockPigIron, blockMeteoric;
	public static Block blockAluminum;
	public static Block industrialGlass;
	public static Block engineeringTable;
	public static Block discoBlock, superFurnace;
}