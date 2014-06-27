package magicAndIndustry.tileEntity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class DiscoBlockEntity extends TileEntity 
{
	private static final double jumpValue = 0.45D;
	private int counter = 0;
	
	public DiscoBlockEntity()
	{
		//setBlockName("mobLauncher");
	}
	
	public void updateEntity()
	{
		if (worldObj.isRemote) return;
		
		counter++;
		
		// Only happens every ten seconds (?) tenth of a second?
		if (counter >= 12)
		{
			counter = 0;
			//System.out.println("Updating mobs: " +worldObj.getWorldTime());
			// Create a box around the block to search for entities in
			int radius = 9;                                    // Create around x, y, and z                             // make it 10 in radius but not in height
			AxisAlignedBB box = AxisAlignedBB.getAABBPool().getAABB(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(radius, 1, radius);
			// Make sure it's not taller than the world.            // y + 1 + height
			box.maxY = Math.min((double)worldObj.getHeight(), (double)(yCoord + 1 + 1));
			
			// Collect all of the entities in the box
			List list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, box);
			
			EntityLivingBase entity;
			Iterator iter = list.iterator();
			// Flag to see if we actually affected something
			boolean flag = false;
			
			// Loop through the list
			while (iter.hasNext())
			{
				// Get the entity
				entity = (EntityLivingBase)iter.next();
				
				// Don't affect players.
				// Make sure they're not airborne - only launch things off the ground.
				// This would be fun to work with items.
				if (entity != null && !(entity instanceof EntityPlayer) && entity.onGround)
				{
					entity.motionY += jumpValue;
					entity.fallDistance = 0;
					flag = true;
				}
			}
			
			if (flag)
				worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "note.bass", 5.0F, 0.00000001F);
		}
		
		// If not 12, and six - on the other beat!
		else if (counter == 6)
			worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "note.snare", 0.8F, 0.3F);
	}
}
