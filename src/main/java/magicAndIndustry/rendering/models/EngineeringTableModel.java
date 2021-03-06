package magicAndIndustry.rendering.models;

import net.minecraft.client.model.ModelBase;
//Date: 6/20/2014 10:07:42 AM
//Template version 1.1
//Java generated by Techne
//Keep in mind that you still need to fill in some blanks
//- ZeuX
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class EngineeringTableModel extends ModelBase
{
	//fields
	ModelRenderer top;
	ModelRenderer back;
	ModelRenderer left;
	ModelRenderer right;
	ModelRenderer front;
	ModelRenderer leg_front_left;
	ModelRenderer leg_back_left;
	ModelRenderer conector_left;
	ModelRenderer conector_top_left;
	ModelRenderer conector_middle;
	ModelRenderer leg_front_right;
	ModelRenderer leg_back_right;
	ModelRenderer colector_botom_right;
	ModelRenderer conector_top_right;
	ModelRenderer box;
	ModelRenderer droorright;
	ModelRenderer droor_left;
	ModelRenderer handel_right;
	ModelRenderer handel_left;

	public EngineeringTableModel()
	{
		textureWidth = 256;
		textureHeight = 256;

		top = new ModelRenderer(this, 168, 109);
		top.addBox(0F, 0F, 0F, 14, 1, 30);
		top.setRotationPoint(-7F, 9F, -7F);
		top.setTextureSize(256, 256);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		back = new ModelRenderer(this, 104, 65);
		back.addBox(0F, 0F, 0F, 1, 3, 32);
		back.setRotationPoint(-8F, 8F, -8F);
		back.setTextureSize(256, 256);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		left = new ModelRenderer(this, 173, 35);
		left.addBox(0F, 0F, 0F, 14, 2, 1);
		left.setRotationPoint(-7F, 9F, -8F);
		left.setTextureSize(256, 256);
		left.mirror = true;
		setRotation(left, 0F, 0F, 0F);
		right = new ModelRenderer(this, 172, 39);
		right.addBox(0F, 0F, 0F, 14, 2, 1);
		right.setRotationPoint(-7F, 9F, 23F);
		right.setTextureSize(256, 256);
		right.mirror = true;
		setRotation(right, 0F, 0F, 0F);
		front = new ModelRenderer(this, 99, 0);
		front.addBox(0F, 0F, 0F, 1, 2, 32);
		front.setRotationPoint(7F, 9F, -8F);
		front.setTextureSize(256, 256);
		front.mirror = true;
		setRotation(front, 0F, 0F, 0F);
		leg_front_left = new ModelRenderer(this, 59, 29);
		leg_front_left.addBox(0F, 0F, 0F, 2, 14, 2);
		leg_front_left.setRotationPoint(5F, 10F, -7F);
		leg_front_left.setTextureSize(256, 256);
		leg_front_left.mirror = true;
		setRotation(leg_front_left, 0F, 0F, 0F);
		leg_back_left = new ModelRenderer(this, 77, 23);
		leg_back_left.addBox(0F, 0F, 0F, 2, 14, 2);
		leg_back_left.setRotationPoint(-7F, 10F, -7F);
		leg_back_left.setTextureSize(256, 256);
		leg_back_left.mirror = true;
		setRotation(leg_back_left, 0F, 0F, 0F);
		conector_left = new ModelRenderer(this, 45, 0);
		conector_left.addBox(0F, 0F, 0F, 10, 2, 2);
		conector_left.setRotationPoint(-5F, 21F, -7F);
		conector_left.setTextureSize(256, 256);
		conector_left.mirror = true;
		setRotation(conector_left, 0F, 0F, 0F);
		conector_top_left = new ModelRenderer(this, 17, 39);
		conector_top_left.addBox(0F, 0F, 0F, 10, 2, 2);
		conector_top_left.setRotationPoint(-5F, 12F, -7F);
		conector_top_left.setTextureSize(256, 256);
		conector_top_left.mirror = true;
		setRotation(conector_top_left, 0F, 0F, 0F);
		conector_middle = new ModelRenderer(this, 20, 140);
		conector_middle.addBox(0F, 0F, 0F, 2, 2, 26);
		conector_middle.setRotationPoint(-1F, 21F, -5F);
		conector_middle.setTextureSize(256, 256);
		conector_middle.mirror = true;
		setRotation(conector_middle, 0F, 0F, 0F);
		leg_front_right = new ModelRenderer(this, 0, 93);
		leg_front_right.addBox(0F, 0F, 0F, 2, 14, 2);
		leg_front_right.setRotationPoint(5F, 10F, 21F);
		leg_front_right.setTextureSize(256, 256);
		leg_front_right.mirror = true;
		setRotation(leg_front_right, 0F, 0F, 0F);
		leg_back_right = new ModelRenderer(this, 0, 49);
		leg_back_right.addBox(0F, 0F, 0F, 2, 14, 2);
		leg_back_right.setRotationPoint(-7F, 10F, 21F);
		leg_back_right.setTextureSize(256, 256);
		leg_back_right.mirror = true;
		setRotation(leg_back_right, 0F, 0F, 0F);
		colector_botom_right = new ModelRenderer(this, 36, 66);
		colector_botom_right.addBox(0F, 0F, 0F, 10, 2, 2);
		colector_botom_right.setRotationPoint(-5F, 21F, 21F);
		colector_botom_right.setTextureSize(256, 256);
		colector_botom_right.mirror = true;
		setRotation(colector_botom_right, 0F, 0F, 0F);
		conector_top_right = new ModelRenderer(this, 17, 27);
		conector_top_right.addBox(0F, 0F, 0F, 10, 2, 2);
		conector_top_right.setRotationPoint(-5F, 12F, 21F);
		conector_top_right.setTextureSize(256, 256);
		conector_top_right.mirror = true;
		setRotation(conector_top_right, 0F, 0F, 0F);
		box = new ModelRenderer(this, 0, 221);
		box.addBox(0F, 0F, 0F, 12, 7, 27);
		box.setRotationPoint(-6F, 10F, -5F);
		box.setTextureSize(256, 256);
		box.mirror = true;
		setRotation(box, 0F, 0F, 0F);
		droorright = new ModelRenderer(this, 120, 129);
		droorright.addBox(0F, 0F, 0F, 1, 3, 7);
		droorright.setRotationPoint(6F, 12F, 12F);
		droorright.setTextureSize(256, 256);
		droorright.mirror = true;
		setRotation(droorright, 0F, 0F, 0.0174533F);
		droor_left = new ModelRenderer(this, 0, 0);
		droor_left.addBox(0F, 0F, 0F, 1, 3, 7);
		droor_left.setRotationPoint(6F, 12F, -3F);
		droor_left.setTextureSize(256, 256);
		droor_left.mirror = true;
		setRotation(droor_left, 0F, 0F, 0.0174533F);
		handel_right = new ModelRenderer(this, 37, 89);
		handel_right.addBox(0F, 0F, 0F, 1, 1, 3);
		handel_right.setRotationPoint(6.5F, 13F, 14F);
		handel_right.setTextureSize(256, 256);
		handel_right.mirror = true;
		setRotation(handel_right, 0F, 0F, 0F);
		handel_left = new ModelRenderer(this, 22, 108);
		handel_left.addBox(0F, 0F, 0F, 1, 1, 3);
		handel_left.setRotationPoint(6.5F, 13F, -1F);
		handel_left.setTextureSize(256, 256);
		handel_left.mirror = true;
		setRotation(handel_left, 0F, 0F, 0F);
	}
	
	public void renderModel(float f)
	{
		top.render(f);
		back.render(f);
		left.render(f);
		right.render(f);
		front.render(f);
		leg_front_left.render(f);
		leg_back_left.render(f);
		conector_left.render(f);
		conector_top_left.render(f);
		conector_middle.render(f);
		leg_front_right.render(f);
		leg_back_right.render(f);
		colector_botom_right.render(f);
		conector_top_right.render(f);
		box.render(f);
		droorright.render(f);
		droor_left.render(f);
		handel_right.render(f);
		handel_left.render(f);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		top.render(f5);
		back.render(f5);
		left.render(f5);
		right.render(f5);
		front.render(f5);
		leg_front_left.render(f5);
		leg_back_left.render(f5);
		conector_left.render(f5);
		conector_top_left.render(f5);
		conector_middle.render(f5);
		leg_front_right.render(f5);
		leg_back_right.render(f5);
		colector_botom_right.render(f5);
		conector_top_right.render(f5);
		box.render(f5);
		droorright.render(f5);
		droor_left.render(f5);
		handel_right.render(f5);
		handel_left.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float armTime, float armswing, float f2, float f3, float f4, float f5, Entity ent)
	{
		super.setRotationAngles(armTime, armswing, f2, f3, f4, f5, ent);
	}

}
