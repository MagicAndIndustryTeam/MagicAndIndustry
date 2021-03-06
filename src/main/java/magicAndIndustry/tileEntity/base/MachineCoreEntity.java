package magicAndIndustry.tileEntity.base;

import java.util.ArrayList;
import java.util.Iterator;

import magicAndIndustry.api.IStructureAware;
import magicAndIndustry.blocks.StructureBlock;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.machines.structure.MachineStructure;
import magicAndIndustry.machines.structure.MachineStructureRegistrar;
import magicAndIndustry.machines.structure.PReq;
import magicAndIndustry.utils.BlockPosition;
import magicAndIndustry.utils.RelativeFaceCoords;
import magicAndIndustry.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class MachineCoreEntity extends TileEntity 
{	
	/** All connected structure upgrade tile entities' upgrades are passed into the core which calls events with them. */
	public StructureUpgradeEntity[] upgrades;
	
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
	
	/**
	 * Checks for the structure completeness.
	 */
	@Override
	public void updateEntity()
	{
		if (structureComplete() && checkCountdown >= CHECK_MAX)
		{
			checkCountdown = 0;
			
			// Refresh the core structure.
			updateStructure();
		}
		else checkCountdown++;
	}
	
	/**
	 * Updates the structure. Override this with a super call to hook into the event.
	 */
	public void updateStructure()
	{
		boolean itWorked = true;
		
		// Get block's rotational information as a ForgeDirection
		ForgeDirection rotation = ForgeDirection.getOrientation(Utils.backFromMeta(worldObj.getBlockMetadata(xCoord, yCoord, zCoord)));
		
		//
		// Step one: validate all blocks
		//
		
		// Get the MachineStructures from the MachineStructureRegistrar
		// But if the machine is already set up it should only check the desired structure.
		for (MachineStructure struct : (structureComplete() ? MachineStructureRegistrar.getStructuresForMachineID(getMachineID()) : MachineStructureRegistrar.getStructuresForMachineID(getMachineID())))
		{
			// Check each block in requirements
			for (PReq req : struct.requirements)
			{
				// Get the rotated block coordinates.
				BlockPosition pos = req.rel.getPosition(rotation, xCoord, yCoord, zCoord);

				// Check the requirement.
				if (!req.requirement.isMatch(tier, worldObj, pos.x, pos.y, pos.z, xCoord, yCoord, zCoord))
				{
					// If it didn't work, stop checking.
					itWorked = false; break;
				}
				// If it did work keep calm and carry on
			}
			
			// We've gone through all the blocks. They all match up!
			if (itWorked)
			{
				// **If the structure is new only**
				// Which implies the blocks have changed between checks
				if (struct.ID != structureID)
				{
					//
					// This is only called when structures are changed/first created.
					//
					
					// Save what structure we have.
					structureID = struct.ID;
					
					// Make an arraylist to save all teh structures
					ArrayList<StructureUpgradeEntity> newEntities = new ArrayList<StructureUpgradeEntity>();

					// Tell all of the blocks to join us!
					for (PReq req : struct.requirements)
					{
						// Get the blocks that the structure has checked.
						BlockPosition pos = req.rel.getPosition(rotation, xCoord, yCoord, zCoord);
						Block brock = worldObj.getBlock(pos.x, pos.y, pos.z); if (brock == null) continue;

						if (brock instanceof IStructureAware)
							((IStructureAware)brock).onStructureCreated(worldObj, pos.x, pos.y, pos.z, xCoord, yCoord, zCoord);
						
						// Check the tile entity for upgrades
						TileEntity ent = worldObj.getTileEntity(pos.x, pos.y, pos.z);
						if (ent != null && ent instanceof StructureUpgradeEntity)
						{
							StructureUpgradeEntity structEnt = (StructureUpgradeEntity)ent;
							newEntities.add(structEnt);
							
							/* // Not sure about this.
							if (structEnt.coreX != xCoord && structEnt.coreY != yCoord && structEnt.coreZ != zCoord)
							{
								structEnt.onCoreConnected()
							}
							*/
						}
						// do stuff with that	
					}
					
					// I haven't figured out how the hell to do toArray() in this crap
					// so here's a weird combination of iterator and for loop
					upgrades = new StructureUpgradeEntity[newEntities.size()];
					for (int i = 0; i < newEntities.size(); i++)
						upgrades[i] = newEntities.get(i);

					// Tell all of the structure blocks to stripe it up!
					for (RelativeFaceCoords relPos : struct.relativeStriped)
					{
						BlockPosition pos = relPos.getPosition(rotation, xCoord, yCoord, zCoord);
						Block brock = worldObj.getBlock(pos.x, pos.y, pos.z);
						
						// If it's a structure block tell it to stripe up
						if (brock != null && brock instanceof StructureBlock)
							worldObj.setBlockMetadataWithNotify(pos.x, pos.y, pos.z, 2, 2);
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
	 * Does NOT check/validate structure, just a null check on structureID.
	 */
	public boolean structureComplete() { return structureID != null && structureID.length() != 0; }
	
	/**
	 * Return the machine ID used to look up machine structures.
	 */
	public abstract String getMachineID();
}
