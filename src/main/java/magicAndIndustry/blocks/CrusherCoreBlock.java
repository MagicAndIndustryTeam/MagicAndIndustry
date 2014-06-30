package magicAndIndustry.blocks;

import java.util.Random;

import magicAndIndustry.Textures;
import magicAndIndustry.Utils;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.tileEntity.CrusherCoreEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CrusherCoreBlock extends MachineCoreBlock 
{
	// Not storing this in metadata ATM because of rotation comparison, may implement in the future
	// for machines which do not need a separate block for lighting reasons.
	private final boolean isWorking;
	private static boolean switchingCrushers = false;
	private final Random rand = new Random();
	
	public CrusherCoreBlock(MachineTier tier, boolean working)
	{
		super(Material.rock, tier, "crusher");
		isWorking = working;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		String prefix = Textures.block(tier.name + "_" + machineID);
		textureFront = reg.registerIcon(isWorking ? prefix + "_lit" : prefix + "_front");
		blockIcon = reg.registerIcon(tier.getFaceTexture());
		textureBottom = reg.registerIcon(tier.getAltTexture());
		textureSide = reg.registerIcon(tier.getStripedTexture());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side == 0) return blockIcon;
		if (side == 1) return textureBottom;
		if (side == Utils.sideFromMeta(meta)) return textureFront;
		return textureSide;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		// Particle effects????
		// Should these be done from something else?
		if (isWorking)
		{
			
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new CrusherCoreEntity(tier);
	}
	
	public static void setCrusherState(World world, int x, int y, int z, MachineTier tier, boolean dammitWesley)
	{
		int meta = world.getBlockMetadata(x, y, z);
		TileEntity crusher = world.getTileEntity(x, y, z);
		
		switchingCrushers = true;
		world.setBlock(x, y, z, BlockRegistrar.crusherForType(tier, dammitWesley));
		switchingCrushers = false;
		
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
		
		if (crusher != null)
		{
			crusher.validate();
			world.setTileEntity(x, y, z, crusher);
		}
		
	}

}
