package magicAndIndustry.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BagOfWinds extends Item
{
	private static final double velocityGain = -2.4;
	private static final int maxUses = 2000;
	
	public BagOfWinds()
	{
		super();
		setMaxStackSize(1);
		setUnlocalizedName("bag_of_winds");
		setHasSubtypes(true);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		ItemStack stack = new ItemStack(item);
		stack.setTagCompound(new NBTTagCompound());
		stack.stackTagCompound.setInteger("Uses", 0);
		list.add(stack);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (player.isSneaking()) return stack;
		
		// Through the looking vec
		Vec3 theLookingVec = player.getLookVec();
		
		// Move whatever the player is in (i.e. boat)
		// NOTE: boats seem to have maximum velocity,
		// so this 
		if (player.isRiding())
		{
			Entity ridee = player.ridingEntity;
			
			if (ridee instanceof EntityBoat)
			{
				EntityBoat boat = (EntityBoat)ridee;
				
				boat.setVelocity(
						theLookingVec.xCoord * velocityGain * 3, 
						theLookingVec.yCoord * -0.32D, 
						theLookingVec.zCoord * velocityGain * 3);
			}
			else if (!(ridee instanceof EntityLiving))
			{
				ridee.motionX = theLookingVec.xCoord * velocityGain * 2;
				ridee.motionZ = theLookingVec.yCoord * velocityGain * 2;
			}
		}
		else // Move the player around
		{
			// Shoot the player in the opposite direction
			// TODO: function that takes uses and scales velocitygains down in a log? manner as uses increases
			// TODO: make it a little random, maybe the random is greater the more filled it is :D
			player.motionX = theLookingVec.xCoord * velocityGain * (world.rand.nextDouble() * 2.9D); // TODO need to adjust the randomness up/down, maybe addition
			player.motionY = theLookingVec.yCoord * velocityGain * (world.rand.nextDouble()) * 0.29D; // Slight altitude gain
			player.motionZ = theLookingVec.zCoord * velocityGain * (world.rand.nextDouble() * 2.9D);
		}
		
		// Sometimes, the player can save themselves from falling.
		if (world.rand.nextInt(5) == 0)
			player.fallDistance = 0;
			
		
		// Fancy noises TODO replace with wind.ogg
		world.playSoundEffect(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, "random.fizz", 1F, world.rand.nextFloat() * 0.1F + 0.9F);
		
		// "Damage" the item if the player isn't in creative mode.
		if (!player.capabilities.isCreativeMode && stack.hasTagCompound())
		{
			int uses = stack.stackTagCompound.getInteger("Uses");
			
			// Handle the item being finally used up.
			if (++uses > maxUses) 
				// TODO replace with a fillable empty sack
				return new ItemStack(ItemRegistrar.emptyBag);
				
			// "Damage" the item through NBT.
			stack.stackTagCompound.setInteger("Uses", uses + 1);
		}
		
		return stack;
	}
	
	// Durability overrides:
	// Because there are enough mods with magic repair as it is.
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		if (stack.hasTagCompound())
		{
			return stack.stackTagCompound.getInteger("Uses") != 0;
		}
		return false;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		if (stack.hasTagCompound())
		{
			return (double)stack.stackTagCompound.getInteger("Uses") / maxUses;
		}
		return 1;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		if (stack.stackTagCompound != null)
		{
			list.add("Uses: " + stack.stackTagCompound.getInteger("Uses") + "/" + maxUses);
		}
		else list.add("There's no tag compound here :'(");
	}
}
