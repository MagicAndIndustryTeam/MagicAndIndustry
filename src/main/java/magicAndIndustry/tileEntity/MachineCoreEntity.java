package magicAndIndustry.tileEntity;

import java.util.ArrayList;

import magicAndIndustry.Utils;
import magicAndIndustry.blocks.StructureBlock;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.machines.StructureUpgrade;
import magicAndIndustry.machines.structure.BlockPosition;
import magicAndIndustry.machines.structure.MachineStructure;
import magicAndIndustry.machines.structure.MachineStructureRegistrar;
import magicAndIndustry.machines.structure.PReq;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class MachineCoreEntity extends TileEntity 
{
	/** If this machine's structure is valid. */
	public boolean structureComplete;
	
	/** All connected structure upgrade tile entities' upgrades are passed into the core which calls events with them. */
	public StructureUpgrade[] upgrades;
	
	/** Saved coords of structure blocks to reset if the structure is broken. */
	public BlockPosition[] structureBlocks;
	
	/** The Tier of the machine entity */
	public MachineTier tier;
	
	/** Countdown between machine checks. */
	private int checkCountdown;
	
	/** Max time between checks. Note that most checks happen on block neighbor changes. */
	private int CHECK_MAX = 20 * 7;
	
	/**
	 * MachineCoreEntity constructor- pass in the given Tier and an ID.
	 * @param theTier
	 * @param machineStructures
	 */
	public MachineCoreEntity(MachineTier theTier)
	{
		super();
		tier = theTier;
	}
	
	public MachineCoreEntity()
	{
		
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setString("Tier", tier.name);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		tier = MachineTier.get(tag.getString("name"));
	}
	
	@Override
	public void updateEntity()
	{
		//if (checkCountdown >= CHECK_MAX)
		//{
			//checkCountdown = 0;
			
			// Refresh the core structure.
			//updateStructure();
		//}
		//else checkCountdown++;
	}
	
	public void updateStructure()
	{
		Utils.print("Entered update structure.");
		// 15 for an initial capacity should be good enough.
		ArrayList<BlockPosition> foundStructures = new ArrayList<BlockPosition>(15);
		
		// Get the direction
		ForgeDirection rotation = ForgeDirection.getOrientation(Utils.backFromMeta(worldObj.getBlockMetadata(xCoord, yCoord, zCoord)));
		Utils.print("Found rotation: " + rotation.name());
		// Get numbers to offset x and z
		int modX = rotation.offsetX, modZ = rotation.offsetZ, currX, currY, currZ;
		boolean isXAxis;
		// Set the zeros to work with multiplication
		// Using an else if here as the machine will face either x or z.
		if (modX == 0) { modX = 1; isXAxis = false; }
		else /*if (modZ == 0)*/ { modZ = 1; isXAxis = true; }
		
		// Debug
		Utils.print("Found forgeDirection " + rotation.name() + ", modX = %d, modZ = %d\n", modX, modZ);
		
		// Go through all possible structures
		for (MachineStructure struct : MachineStructureRegistrar.getStructuresForMachineID(getMachineID()))
		{
			//Utils.print("Checking " + struct.toString());
			boolean checkPassed = true;
			// Loop through the requirements
			for (PReq req : struct.Requirements)
			{
				/*
				currX = xCoord + (req.relBehind == 0 ? 0 : modX + req.relBehind);
				currY = yCoord + req.relHeight;
				currZ = zCoord + (req.relSide == 0 ? req.relSide : modZ + req.relSide);
				*/
				//Utils.print("Relative coords: behind = %d, height = %d, %d.", req.relBehind, req.relHeight, req.relSide);
				
				currX = xCoord + (modX * (isXAxis? req.relBehind : req.relSide));
				currY = yCoord + req.relHeight;
				currZ = zCoord + (modZ * (isXAxis? req.relSide : req.relBehind));
				
				Utils.print("Found %d, %d, %d for req " + req.Requirement.toString(), currX, currY, currZ);
				
				if (req.Requirement.isMatch(tier, worldObj, currX, currY, currZ))
				{
					if (worldObj.getBlock(currX, currY, currZ) instanceof StructureBlock)
					{
						TileEntity ent = worldObj.getTileEntity(currX, currY, currZ);
						if (ent != null && ent instanceof StructureTileEntity)
						{
							// WARNING: saving block positions as nonrelative coordinates!!!
							foundStructures.add(new BlockPosition(currX, currY, currZ));
						}
					}
				}
				else { checkPassed = false; break; }
			}
			if (checkPassed)
			{
				structureComplete = true;
				ArrayList<StructureUpgrade> upgrades = new ArrayList<StructureUpgrade>(10);
				for (BlockPosition pos : foundStructures)
				{
					TileEntity ent = worldObj.getTileEntity(pos.x, pos.y, pos.z);
					
					// Structure Upgrade: save its upgrade.
					if (ent instanceof StructureUpgradeEntity) upgrades.add(((StructureUpgradeEntity)ent).upgrade);
					
					// Machine Structure (also upgrades): set the core coords.
					if (ent instanceof StructureTileEntity) 
					{
						((StructureTileEntity)ent).setCoreValues(pos.x, pos.y, pos.z);
						worldObj.setBlockMetadataWithNotify(pos.x, pos.y, pos.z, 1, 2);
					}
				}
				for (BlockPosition structBlock : struct.relativeStriped)
				{
					worldObj.setBlockMetadataWithNotify(structBlock.x, structBlock.y, structBlock.z, 1, 2);
				}
				Utils.print("Valid structure!"); 
				return;
			}
			else
			{
				foundStructures.clear(); 
				for (BlockPosition structBlock : struct.relativeStriped)
				{
					worldObj.setBlockMetadataWithNotify(structBlock.x, structBlock.y, structBlock.z, 0, 2);
				}
				
			}
		}
		// We looped through all the configurations and they didn't work
		structureComplete = false;
	}
	
	public abstract String getMachineID();
}
