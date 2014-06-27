package magicAndIndustry.tileEntity;

import java.util.ArrayList;

import magicAndIndustry.RelativeFaceCoords;
import magicAndIndustry.Utils;
import magicAndIndustry.api.IStructureAware;
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
import net.minecraftforge.common.util.ForgeDirection;

public abstract class MachineCoreEntity extends TileEntity 
{	
	/** All connected structure upgrade tile entities' upgrades are passed into the core which calls events with them. */
	public StructureUpgrade[] upgrades;
	
	/** Saved coords of structure blocks to reset if the structure is broken. */
	public RelativeFaceCoords[] structureBlocks;
	
	/** The Tier of the machine entity */
	public MachineTier tier;
	
	/** The ID of the machine structure */
	public String structureID;
	
	/** Countdown between machine checks. */
	protected int checkCountdown;
	
	/** Max time between checks. Note that most checks happen on block neighbor changes. */
	public static final int CHECK_MAX = 20 * 2;
	
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
		if (structureComplete())
			tag.setString("Structure", structureID);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		tier = MachineTier.get(tag.getString("name"));
		if (tag.hasKey("Structure"))
			structureID = tag.getString("Structure");
	}
	
	@Override
	public void updateEntity()
	{
		if (checkCountdown >= CHECK_MAX)
		{
			checkCountdown = 0;
			
			// Refresh the core structure.
			updateStructure();
		}
		else checkCountdown++;
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
			for (PReq req : struct.requirements)
			{
				if (req == null) Utils.print("TRIED TO GET A NULL REQ!!!!!!");
				/*
				currX = xCoord + (req.relBehind == 0 ? 0 : modX + req.relBehind);
				currY = yCoord + req.relHeight;
				currZ = zCoord + (req.relSide == 0 ? req.relSide : modZ + req.relSide);
				*/
				Utils.print("Relative coords: behind = %d, height = %d, %d.", req.rel.behind, req.rel.height, req.rel.side);
				
				currX = xCoord + (modX * (isXAxis? req.rel.behind : req.rel.side));
				currY = yCoord + req.rel.height;
				currZ = zCoord + (modZ * (isXAxis? req.rel.side : req.rel.behind));
				
				Utils.print("Found %d, %d, %d for req " + req.requirement.toString(), currX, currY, currZ);
				
				if (req.requirement.isMatch(tier, worldObj, currX, currY, currZ))
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
				else 
				{ 
					checkPassed = false; 
					Utils.print("Check failed at %d, %d, %d: expecting " + req.toString(), currX, currY, currZ);
					break; 
				}
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
						//aaaaaaaworldObj.setBlockMetadataWithNotify(pos.x, pos.y, pos.z, 1, 2);
					}
				}
				for (RelativeFaceCoords structCoords : struct.relativeStriped)
				{
					BlockPosition structBlock = structCoords.getPosition(rotation, xCoord, yCoord, zCoord);
					if (worldObj.getBlock(structBlock.x, structBlock.y, structBlock.z) instanceof StructureBlock)
						worldObj.setBlockMetadataWithNotify(structBlock.x, structBlock.y, structBlock.z, 1, 2);
				}
				Utils.print("Valid structure!"); 
				return;
			}
			else
			{
				// Sure, this is temp, it's not gonna have everything in it
				foundStructures.clear();
				
				// I was very tired when I wrote this
				if (structureBlocks != null)
				for (RelativeFaceCoords main : structureBlocks)
				{
					resetStructure(main, rotation);
				}
				if (struct.relativeStriped != null)
				for (RelativeFaceCoords structCoords : struct.relativeStriped)
				{
					resetStructure(structCoords, rotation);
				}
				
			}
		}
		// We looped through all the configurations and they didn't work
		//structureComplete = false;
	}
	
	public void updateStructure2()
	{	
		boolean itWorked = true;
		//String tempStruct;
		
		// Get block's rotational information as a ForgeDirection
		ForgeDirection rotation = ForgeDirection.getOrientation(Utils.sideFromMeta(worldObj.getBlockMetadata(xCoord, yCoord, zCoord)));
		
		//
		// Step one: validate all blocks
		//
		
		// Get the MachineStructures from the MachineStructureRegistrar
		// But if the machine is already set up it should only check the desired structure.
		for (MachineStructure struct : (structureComplete() ? MachineStructureRegistrar.getStructuresForMachineID(getMachineID()) : MachineStructureRegistrar.getStructuresForMachineID(getMachineID())))
		{
			//tempStruct = struct.ID;
			for (PReq req : struct.requirements)
			{
				// Get the rotated block coordinates.
				BlockPosition pos = req.rel.getPosition(rotation, xCoord, yCoord, zCoord);
				
				if (!req.requirement.isMatch(tier, worldObj, pos.x, pos.y, pos.z))
				{
					itWorked = false; break;
				}
			}
			
			// We've gone through all the blocks. They all match up!
			if (itWorked)
			{
				// If the structure is new only
				// This does not erase the old one
				// We hope for a break between the two
				if (struct.ID != structureID)
				{
					// Save what structure we have.
					structureID = struct.ID;

					// Tell all of the blocks to join us!
					for (PReq req : struct.requirements)
					{
						BlockPosition pos = req.rel.getPosition(rotation, xCoord, yCoord, zCoord);
						Block brock = worldObj.getBlock(pos.x, pos.y, pos.z);

						if (brock != null && brock instanceof IStructureAware)
							((IStructureAware)brock).onStructureCreated(worldObj, pos.x, pos.y, pos.z, xCoord, yCoord, zCoord);
					}

					// Tell all of the structure blocks to stripe it up!
					for (RelativeFaceCoords relPos : struct.relativeStriped)
					{
						BlockPosition pos = relPos.getPosition(rotation, xCoord, yCoord, zCoord);
						Block brock = worldObj.getBlock(pos.x, pos.y, pos.z);
						
						// If it's a structure block tell it to stripe up
						if (brock != null && brock instanceof StructureBlock)
							worldObj.setBlockMetadataWithNotify(pos.x, pos.y, pos.z, 1, 2);
					}
				}
				return;
			}
			// If not, reset the loop and try again.
			else { itWorked = true; continue; }
		}
		
		//
		// None of the structures worked!
		//
		
		// If we had a structure before, we need to clear it
		if (structureComplete())
		{
			for (PReq req : MachineStructureRegistrar.getMachineStructureByID(structureID).requirements)
			{
				BlockPosition pos = req.rel.getPosition(rotation, xCoord, yCoord, zCoord);
				Block brock = worldObj.getBlock(pos.x, pos.y, pos.z);
				
				// This will also un-stripe all structure blocks.
				if (brock instanceof IStructureAware)
					((IStructureAware)brock).onStructureBroken(worldObj, pos.x, pos.y, pos.z, xCoord, yCoord, zCoord);
			}
			// We don't have a structure.
			structureID = null;
		}
		
	}
	
	/**
	 * If the machine has a valid structure.
	 */
	public boolean structureComplete() { return structureID != null && structureID.length() != 0; }
	
	private void resetStructure(RelativeFaceCoords rfc, ForgeDirection dir)
	{
		BlockPosition pos = rfc.getPosition(dir, xCoord, yCoord, zCoord);
		Block brock = worldObj.getBlock(pos.x, pos.y, pos.z);
		if (brock != null && brock instanceof StructureBlock) 
			((StructureBlock)brock).resetStructure(worldObj, pos.x, pos.y, pos.z);
	}
	
	public abstract String getMachineID();
}
