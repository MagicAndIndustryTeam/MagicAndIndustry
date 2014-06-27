package magicAndIndustry.machines.structure;

import java.util.HashMap;

import magicAndIndustry.blocks.BlockRegistrar;
import net.minecraft.init.Blocks;

/**
 * Register possible machine structure configurations for your machines here.
 * Note that the internal database is created during Magic And Industry's preInit event.
 */
public class MachineStructureRegistrar
{	
	private static BlockRequirement needAir = new BlockRequirement(Blocks.air),
		cobbleStruct = new BlockRequirement(BlockRegistrar.cobbleStructure),
		ironBars = new BlockRequirement(Blocks.iron_bars),
		stonePlate = new BlockRequirement(BlockRegistrar.stoneHeatPlate),
		stoneSlab = new BlockRequirement(Blocks.stone_slab);
	
	private static HashMap<String, MachineStructure[]> idStructureMap;

	/**
	 * Gets the registered possible machine structures for a machine.
	 * @param machineID The ID of the machine
	 * @return
	 */
	public static MachineStructure[] getStructuresForMachineID(String machineID)
	{
		return idStructureMap.get(machineID);
	}	

	/**
	 * Registers machine structure(s) for a machine with a specific ID.
	 * If the ID already exists, the new structure(s) are added to its collection.
	 * @param machineID The ID of the machine.
	 * @param structures A collection of possible machine structures for its core.
	 */
	public static void registerMachineConfiguration(String machineID, MachineStructure... structures)
	{
		// If we already have structures, add more
		if (idStructureMap.containsKey(machineID))
		{
			// Get the entry, make a new array
			MachineStructure[] entry = idStructureMap.get(machineID);
			MachineStructure[] newEntry = new MachineStructure[entry.length + structures.length];

			// Copy stuff over
			int i=0;
			for (; i < entry.length; i++)
				newEntry[i] = entry[i];
			for (int j = 0; j < structures.length; j++, i++)
				newEntry[i] = structures[j];

			// Update the map.
			idStructureMap.remove("machineID");
			idStructureMap.put(machineID, newEntry);
		}
		else idStructureMap.put(machineID, structures);
	}
	public static void assimilate()
	{
		idStructureMap = new HashMap<String, MachineStructure[]>();
		/*
		cobbleFurnace = new MachineStructure(
				// BEHIND | SIDE | Level
				new PReq(needAir,      1,  0, 0),

				new PReq(stonePlate,   1,  0, -1), new PReq(stonePlate,   1, -1, -1), new PReq(stonePlate,   1,  1, -1),

				new PReq(ironBars,     1, -1, 0),                                   new PReq(ironBars,     1,  1, 0),

				new PReq(cobbleStruct, 2,  0,  0), new PReq(cobbleStruct, 2, -1,  0), new PReq(cobbleStruct, 2,  1,  0), 
				new PReq(cobbleStruct, 2, -1, -1), new PReq(cobbleStruct, 2,  0, -1), new PReq(cobbleStruct, 2,  1, -1),

				new PReq(cobbleStruct, 0,  1,  0),                                    new PReq(cobbleStruct, 0, -1,  0),
				new PReq(cobbleStruct, 0,  1, -1), new PReq(cobbleStruct, 0, -1, -1), new PReq(cobbleStruct, 0,  0, -1),

				new PReq(stoneSlab, 0, 0, 1), new PReq(stoneSlab, 0, -1, 1), new PReq(stoneSlab, 0,  1, 1),
				new PReq(stoneSlab, 1, 0, 1), new PReq(stoneSlab, 1, -1, 1), new PReq(stoneSlab, 1,  1, 1),
				new PReq(stoneSlab, 2, 0, 1), new PReq(stoneSlab, 2, -1, 1), new PReq(stoneSlab, 2,  1, 1)
				);

		ironFurnace = MachineStructure.Transform(cobbleFurnace, 
				new ReqTransform(stonePlate, new BlockRequirement(BlockRegistrar.ironHeatPlate)),
				new ReqTransform(cobbleStruct, new BlockRequirement(BlockRegistrar.ironStructure)),
				new ReqTransform(stoneSlab, new BlockRequirement(BlockRegistrar.cookedSlab)));

		steelFurnace = MachineStructure.Transform(cobbleFurnace, 
				new ReqTransform(stonePlate, new BlockRequirement(BlockRegistrar.steelHeatPlate)),
				new ReqTransform(cobbleStruct, new BlockRequirement(BlockRegistrar.steelStructure)),
				new ReqTransform(stoneSlab, new BlockRequirement(BlockRegistrar.ironSlab))); */
	}		
}
