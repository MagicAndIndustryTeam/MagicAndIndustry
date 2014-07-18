package magicAndIndustry.tileEntity;

import ibxm.Player;

import java.util.Random;

import magicAndIndustry.blocks.StructureBlock;
import magicAndIndustry.machines.event.InputRequestEvent;
import magicAndIndustry.machines.event.ItemOutputEvent;
import magicAndIndustry.machines.event.PowerRequestEvent;
import magicAndIndustry.machines.event.ProcessingEvent;
import magicAndIndustry.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
	public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ)
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
	 * Return true to dubscribe to machine processing events.
	 */
	public boolean handlesProcessing() { return false; }
	
	public void handleProcessing(ProcessingEvent event)
	{
		
	}
	
	/**
	 * Return true to dubscribe to 
	 * @return
	 */
	public boolean handlesItemMovement() { return false; }
	
	public void handleFuelRequest(PowerRequestEvent event) { }
	
	public void handleItemOutput(ItemOutputEvent event) { }
	
	/**
	 * Called from the machine core when it asks for items.
	 */
	public void handleInputRequest(InputRequestEvent event) { }
	
	/**
	 * Call to give the core an item manually - for example, when an item is placed in an input slot.
	 * Note that
	 * @param stack
	 * @return whether the core could accept the item.
	 */
	public boolean ouputToCore(ItemStack stack)
	{
		return false;
	}
}
