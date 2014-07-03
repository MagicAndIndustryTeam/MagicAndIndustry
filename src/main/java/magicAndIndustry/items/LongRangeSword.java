package magicAndIndustry.items;

import magicAndIndustry.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LongRangeSword extends ItemSword 
{
	public LongRangeSword() 
	{
		super(Utils.magicMaterial);
		setUnlocalizedName("longRangeSword");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.swingItem();
		float f = 1.0F;
		float f1 = player.prevRotationPitch + ((player.rotationPitch - player.prevRotationPitch) * f);
		float f2 = player.prevRotationYaw + ((player.rotationYaw - player.prevRotationYaw) * f);
		double d = player.prevPosX + ((player.posX - player.prevPosX) * f);
		double d1 = (player.prevPosY + ((player.posY - player.prevPosY) * f) + 1.6200000000000001D) - player.yOffset;
		double d2 = player.prevPosZ + ((player.posZ - player.prevPosZ) * f);
		Vec3 theLookingVec = Vec3.createVectorHelper(d, d1, d2);
		
		float f3 = MathHelper.cos((-f2 * 0.01745329F) - 3.141593F);
		float f4 = MathHelper.sin((-f2 * 0.01745329F) - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f8 = f6;
		float f9 = f3 * f5;
		
		double distanceAdded = 50D;
		Vec3 destinationVec = theLookingVec.addVector(f7 * distanceAdded, f8 * distanceAdded, f9 * distanceAdded);
		MovingObjectPosition ray = player.worldObj.rayTraceBlocks(theLookingVec, destinationVec);
		
		if (ray == null) return stack;
		if (ray.typeOfHit == MovingObjectType.BLOCK)
		{
			Vec3 look = player.getLookVec();
			world.spawnEntityInWorld(new EntityLargeFireball(
					world, ray.blockX, ray.blockY, ray.blockZ, 
					0.3 * look.xCoord, 0.3 * look.yCoord, 0.3 * look.zCoord));
		}
		return stack;
	}
}
