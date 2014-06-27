package magicAndIndustry.tileEntity;

import magicAndIndustry.blocks.FurnaceCoreBlock;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.machines.structure.MachineStructure;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SuppressWarnings("unused") // They're used.
public class FurnaceCoreEntity extends MachineCoreEntity implements IInventory
{
	// Cooking, fuel, output
	private ItemStack[] items;
	
	// The indices of the items in the different fuel compartments.
	private static final int SLOT_FUEL = 0, SLOT_INPUT = 1, SLOT_OUTPUT = 2;
	
	/** The amount of fuel the item in the furnace has produced; gradually counts down to zero */
	public int fuelBurnTime;
	/** The total amount of time the current fuel has to burn. */
	public int maxFuelTime;
	/** The amount of time that the item in the furnace has been cooking, gradually counts up to maxCookTime. */
	public int itemCookTime;
	/** The max amount of time it takes something to cook, it's a variable that can change depending on the structure. */
	public int maxCookTime;
	// Counts up to "update area" checks.
	private int updateAreaCounter = 0;
	private String inventoryName;
	
	//
	// Constructor and NBT load/save
	//
	
	public FurnaceCoreEntity()
	{
	}
	
	public FurnaceCoreEntity(MachineTier tier)
	{
		super(tier);
		items = new ItemStack[3];
		// Set the max cook times
		if    (tier.name.equals("cobble")) { maxCookTime = 20 * 9; } //  setStructures(MachineStructure.cobbleFurnace); }
		else if (tier.name.equals("iron")) { maxCookTime = 20 * 8; } // setStructures(MachineStructure.ironFurnace); }
		else if (tier.name.equals("steel")){ maxCookTime = 20 * 6; } // setStructures(MachineStructure.steelFurnace); }
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		//System.out.println("Reading from NBT data...");
		// Read position, etc.
		super.readFromNBT(tag);
		
		// Get the items from the tag.
		NBTTagList itemsList = tag.getTagList("Items", 10);
		items = new ItemStack[3];
		//System.out.println("Created items array: length " + items.length);
		
		// Loop through the items and add them.
		for (int i = 0; i < itemsList.tagCount(); i++)
		{
			//System.out.println("Reading item ");
			// Each item is saved in a tag compound with an added "slot" tag.
			NBTTagCompound item = itemsList.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			System.out.println("Found slot " + slot);
			
			// If the slot hasn't been tampered with, set the item in the slot.
			if (slot >= 0 && slot < items.length)
				items[slot] = ItemStack.loadItemStackFromNBT(item);
		}
		
		// Set the other variables. These names match the vanilla furnace's.
		fuelBurnTime = tag.getShort("BurnTime");
		itemCookTime = tag.getShort("CookTime");
		maxFuelTime = tag.getShort("MaxBurnTime");
		tier = MachineTier.get(tag.getString("FurnaceType"));
		
		// 8 seconds of cooking to start out with, should be set by like a scan area. Possibly on chunk load.
		//maxCookTime = 20 * 8; 
		
		if    (tier.name.equals("cobble")) { maxCookTime = 20 * 9; } // setStructures(MachineStructure.cobbleFurnace); }
		else if (tier.name.equals("iron")) { maxCookTime = 20 * 8; } // setStructures(MachineStructure.ironFurnace); }
		else if (tier.name.equals("steel")){ maxCookTime = 20 * 6; } // setStructures(MachineStructure.steelFurnace); }
		
		if (tag.hasKey("CustomName"))
			inventoryName = tag.getString("CustomName");
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		// Writes the position, etc.
		super.writeToNBT(tag);
				
		// Write the variables.
		tag.setShort("BurnTime", (short)fuelBurnTime);
		tag.setShort("CookTime", (short)itemCookTime);
		tag.setShort("MaxBurnTime", (short)maxFuelTime);
		tag.setString("FurnaceType", tier.name);
		
		// Save all of the items.
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < items.length; i++)
		{
			if (items[i] != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				items[i].writeToNBT(item);
				list.appendTag(item);
			}
		}
		tag.setTag("Items", list);

		if (hasCustomInventoryName())
			tag.setString("CustomName", inventoryName);
	}
	
	@Override
	public void updateEntity()
	{
		// Machine core checks structure and whatnot
		super.updateEntity();
		
		// Structure incompleteness kills cooking.
		// Could add a countdown for having a delay betwen structure break and failure, probably not needed.
		if (!structureComplete)
		{
			// If we already are cooking
			if (fuelBurnTime > 0)
			{
				FurnaceCoreBlock.setFurnaceState(false, tier, worldObj, xCoord, yCoord, zCoord);
				markDirty();
			}
			fuelBurnTime = 0; itemCookTime = 0;
			return;
		}
		
		// Save how the burning is going: if it changed this tick,
		// update the underlying block's burn settings.
		boolean startingBurningState = fuelBurnTime > 0;
		boolean isDirty = false;
		
		if (fuelBurnTime > 0) fuelBurnTime--;
		
		// Most processing is done serverside.
		if (!worldObj.isRemote)
		{
			// If we're out of fuel and we can cook the input, try to get more fuel
			if (fuelBurnTime == 0 && canCookItem())
			{
				// Set up the remaining cook time from the fuel slot.
				fuelBurnTime = TileEntityFurnace.getItemBurnTime(items[SLOT_FUEL]);
				
				// If the item can actually burn
				if (fuelBurnTime > 0)
				{
					// We're gonna get our hands dirty.
					// (we've modified data in this block)
					isDirty = true;
					
					if (items[SLOT_FUEL] != null)
					{
						// CONSUME THE FUEL
						items[SLOT_FUEL].stackSize--;
						
						// This handles things like buckets of lava: it will transform into a bucket.
						// It's what the vanilla furnace uses, and it assumes such items won't have a maxstack > 1.
						if (items[SLOT_FUEL].stackSize == 0)
							items[SLOT_FUEL] = items[SLOT_FUEL].getItem().getContainerItem(items[SLOT_FUEL]);
						
						// Update what the max fuel burn is.
						maxFuelTime = fuelBurnTime;
					}
				}
			}
			
			// Now, if the fire's burning and the item can be cooked, raise the smelt bar.
			if (fuelBurnTime > 0 && canCookItem())
			{
				// The item's been cooking a little longer.
				itemCookTime++;
				
				// If enough time has passed to cook
				if (itemCookTime == maxCookTime)
				{
					// Cookies are ready!
					itemCookTime = 0;
					
					ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(items[SLOT_INPUT]);
					
					// If there's nothing in the output slot just use the result.
					if (items[SLOT_OUTPUT] == null)
						items[SLOT_OUTPUT] = result.copy();
					
					// If it's smelting more of the same, increase output's stack.
					else if (items[SLOT_OUTPUT].getItem() == result.getItem())
						items[SLOT_OUTPUT].stackSize += result.stackSize;
					
					// Decrease the input.
					items[SLOT_INPUT].stackSize--;
					
					// Maintain sanity.
					if (items[SLOT_INPUT].stackSize <= 0)
						items[SLOT_INPUT] = null;
					
					// We've modified data in this block!
					isDirty = true;
				}
			}
			// If there's no item to cook, or the output is blocked, reset the cook time.
			else itemCookTime = 0;
			
			// Check for updates!
			// If our fuel burning status has changed this tick, change the texture.
			if (startingBurningState != fuelBurnTime > 0)
			{
				// Also that needs to be saved.
				isDirty = true;
				FurnaceCoreBlock.setFurnaceState(fuelBurnTime > 0, tier, worldObj, xCoord, yCoord, zCoord);
			}
		}
		
		// Make sure the chunk is saved, the furnace along with it.
		if (isDirty) markDirty();
	}
	
	/** Checks that an item from the input slot can be cooked and enter the output slot */
	public boolean canCookItem()
	{
		// If there's no item to cook nothing happens.
		if (items[SLOT_INPUT] == null) return false;
		
		// Get the item that input would be smelted into.
		ItemStack smeltResult = FurnaceRecipes.smelting().getSmeltingResult(items[SLOT_INPUT]);
		if (smeltResult == null) return false;
		
		// If there's nothing in the item's way (in output) return true
		if (items[SLOT_OUTPUT] == null) return true;
		
		// But if there's a different output, return false
		if (!items[SLOT_OUTPUT].isItemEqual(smeltResult)) return false;
		
		// Expected stack size of the output: current output stack size + possible future smelting stack size.
		int totalStack = smeltResult.stackSize + items[SLOT_OUTPUT].stackSize;
		
		// Make sure result is <= 64, and it's <= the output item's max stack size.
		return totalStack <= getInventoryStackLimit() &&
				totalStack <= items[SLOT_OUTPUT].getMaxStackSize();
	}
	
	@SideOnly(Side.CLIENT)
	public int getScaledFuelBurnTime(int width)
	{
		// TODO work on it
		return fuelBurnTime * width / maxFuelTime;
	}
	
	
	@SideOnly(Side.CLIENT)
	public int getScaledItemCookTime(int width)
	{
		return itemCookTime * width / maxCookTime;
	}
	
	@Override
	public int getSizeInventory() 
	{
		return 3;
	}
	
	@Override
	public boolean hasCustomInventoryName()
	{
		return inventoryName != null && inventoryName.length() > 0;
	}
	
	@Override
	public String getInventoryName()
	{
		return hasCustomInventoryName() ? inventoryName : "container." + tier.name +  "_furnace";
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return items[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) 
	{
		ItemStack item = getStackInSlot(slot);
		if (item != null)
		{
			if (item.stackSize <= count)
				setInventorySlotContents(slot, null);
			
			else // Count is less than amount
			{
				ItemStack newer = ItemStack.copyItemStack(item); newer.stackSize -= count;
				setInventorySlotContents(slot, newer);
				item = item.splitStack(count);
			}
		}
		return item;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) 
	{
		ItemStack item = getStackInSlot(slot);
		setInventorySlotContents(slot, item);
		return item;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item) 
	{
		// Maybe the slot is from the player?
		// This line isn't included in vanilla furnace
		//if (slot == 2) return;
		
		items[slot] = item;
		
		if (item != null && item.stackSize > getInventoryStackLimit())
			item.stackSize = getInventoryStackLimit();
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		return player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	@Override
	public void openInventory() { }

	@Override
	public void closeInventory() { }

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) 
	{
		switch (slot)
		{
			// the loading slot
			case 0: 
				// Return if there's a valid smelting ability
				return FurnaceRecipes.smelting().getSmeltingResult(item) != null;
			
			// the burning slot
			case 1: 
				// Return that it has a fuel.
				// The furnace entity does a couple checks.
				return TileEntityFurnace.isItemFuel(item);
				
			// I guess the output
			default: 
				// Return false, items should not be added to it.
				return false;
		}
	}

	@Override
	public String getMachineID() { return "furnace"; }
}
