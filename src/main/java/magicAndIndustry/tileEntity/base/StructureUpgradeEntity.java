package magicAndIndustry.tileEntity.base;

import java.util.Random;

import magicAndIndustry.blocks.StructureBlock;
import magicAndIndustry.machines.event.InputRequestEvent;
import magicAndIndustry.machines.event.ItemOutputEvent;
import magicAndIndustry.machines.event.PowerRequestEvent;
import magicAndIndustry.machines.event.ProcessingEvent;
import magicAndIndustry.tileEntity.ProcessingCoreEntity;
import magicAndIndustry.tileEntity.StructureEntity;
import magicAndIndustry.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * TileEntity that holds structure upgrades.
 * This base class only implements read/write NBT.
 * <br/>
 * Custom StructureUpgrades should create their own TileEntities to do world-interactive things,
 * such as right clicks, block breaks, manage IInventory, redstone or fluid tank.
 * <br/>
 * Most machine-type code should be called from the StructureUpgrade itself through machine events.
 */
public abstract class StructureUpgradeEntity extends StructureEntity
{	
	/**
	 * This random is initialized with the super() constructor (new Random()). 
	 * Use it for all of your subclass pseudorandomizing needs.
	 */
	public Random rand;
	
	public StructureUpgradeEntity()
	{
		super();
		rand = new Random();
	}
	
	/**
	 * Called from the structure block when it is right clicked.
	 * These parameters are copied from the block's event - server side only.
	 * The base class {@link StructureUpgradeEntity } does not handle this event.
	 * <br/>
	 * If you would like a client side event please contact us.
	 * @param player The player right-clicking (use, etc) the block.
	 * @param side The side the player hit.
	 * @param hitX Block x coord.
	 * @param hitY Block y coord.
	 * @param hitZ Block z coord.
	 * @return 
	 */
	public boolean onBlockActivated(World world, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		return false;
	}
	
	/**
	 * Called from {@link StructureBlock} when it is destroyed. 
	 * <br/>
	 * Base implementation calls {@code getUpradeStack()} with null and false and adds that to the world.
	 */
	public void onBlockBroken()
	{
		ItemStack stack = getUpgradeStack(null, false);
		
		if (stack != null) Utils.dropItem(stack, worldObj, null, xCoord, yCoord, zCoord, ForgeDirection.UNKNOWN, rand);
		// Now handled by the block class.
		//worldObj.removeTileEntity(xCoord, yCoord, zCoord);
		//worldObj.setTileEntity(xCoord, yCoord, zCoord, new StructureEntity());
	}
	
	/**
	 * Called when the block is "used" with a wrench.
	 * Please call super.onWrenched - the base class handles dropping the upgrade if the player is shifted.
	 */
	public void onWrenched(EntityPlayer player, int side)
	{
		// Only handle shift-wrench in base.
		if (!player.isSneaking()) return;
		
		// Prepare to drop upgrade stack.
		ItemStack stack = getUpgradeStack(player, true);
		if (stack == null) return;
		
		// drdrdrdrdrop the item
		Utils.dropItem(stack, worldObj, player, xCoord, yCoord, zCoord, ForgeDirection.getOrientation(side), rand);
	}
	
	/**
	 * Called when the player shift-wrenches the structure block through {@link onWrenched}
	 * (with a {@link Player} and {@code true}), and when the player breaks the block 
	 * <i>(with <b>{@code null}</b> and {@code false})</i> through {@link onBlockBroken}.
	 * @param player The player who wrenched the block, or null!
	 * @param wrenched Whether the block was wrenched (and player isn't null)
	 * @return An ItemStack representation of this {@link StructureUpgradeEntity}'s upgrade.
	 */
	public abstract ItemStack getUpgradeStack(EntityPlayer player, boolean wrenched);
	
	//
	// Events
	//
	
	/**
	 * Return true to dubscribe to machine ProcessingEvents.
	 */
	public boolean handlesProcessing() { return false; }
	
	/**
	 * Used to adjust the rate or cost of processing for machines. <br/>
	 * Called by a {@link ProcessingCoreEntity} every second while processing. <br/>
	 * Need {@link StructureUpgradeEntity#handlesProcessing() handlesProcessing()} to {@code return true}.
	 * @param event Event arguments.
	 */
	public void handleProcessing(ProcessingEvent event) { }
	
	/**
	 * Return true to dubscribe to PowerRequestEvents, ItemOutputEvents, and ItemRequestEvents.
	 */
	public boolean handlesItemMovement() { return false; }
	
	/**
	 * Return true to dubsribe to PowerRequestEvents.
	 * @return
	 */
	public boolean handlesPowerUsage() { return false; }
	
	/**
	 * Used to provide a machine with power when it requests it. <br/>
	 * Called by a {@link ProcessingCoreEntity} when it needs power. <br/>
	 * Need {@link StructureUpgradeEntity#handlesItemMovement() handlesItemMovement} to {@code return true}.
	 * @param event
	 */
	public void handlePowerRequest(PowerRequestEvent event) { }
	
	/**
	 * Used to take items output by a machine. <br/>
	 * Called by a {@link ProcessingMachineCore} when it has finished working.<br/>
	 * Requires {@link StructureUpgradeEntity#handlesItemMovement()} to {@code return true}.
	 * @param event Event data
	 */
	public void handleItemOutput(ItemOutputEvent event) { }
	
	/**
	 * Used to input items into a machine. <br/>
	 * Called by a {@link MachineCoreEntity} when it needs items. <br/>
	 * Need {@link StructureUpgradeEntity#handlesItemMovement() handlesItemMovement()} to {@code return true}.
	 * @param event Event data
	 */
	public void handleInputRequest(InputRequestEvent event) { }
	
	/**
	 * Call to give the core an item manually - for example, when an item is placed in an input slot.
	 * @param stack
	 * @return whether the core could accept the item.
	 */
	public boolean ouputToCore(ItemStack stack)
	{
		return false;
	}
	
	/**
	 * Checks if the machine core can provide {@link amount} of power.
	 * @param amount
	 * @return
	 */
	public boolean canGetPower(int amount)
	{
		return false;
	}
	
	/**
	 * Requests {@code amount} of power from the machine core.
	 * Returns the 
	 * @param amount
	 * @return
	 */
	public int powerRequest(int amount)
	{
		return 0;
	}
}
