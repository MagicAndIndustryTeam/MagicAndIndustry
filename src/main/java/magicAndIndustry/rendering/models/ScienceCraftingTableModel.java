package magicAndIndustry.rendering.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ScienceCraftingTableModel extends ModelBase
{
  //fields
    ModelRenderer top;
    ModelRenderer botom;
    ModelRenderer leg_1;
    ModelRenderer leg_2;
    ModelRenderer leg_3;
    ModelRenderer leg_4;
    ModelRenderer box;
    ModelRenderer shelf;
    ModelRenderer hanel;
    ModelRenderer hammer_head;
    ModelRenderer saw_hanel;
    ModelRenderer saw_middle;
    ModelRenderer saw_right;
    ModelRenderer saw_left;
    ModelRenderer hammer_hadle;
    ModelRenderer hamer_top;
    ModelRenderer sizor_hadle_right;
    ModelRenderer scizor_blade_right;
    ModelRenderer sizor_midle;
    ModelRenderer sizor_handle_left;
    ModelRenderer sizor_blade_left;
    ModelRenderer wrench_part_8;
    ModelRenderer wrench_part_10;
    ModelRenderer wrench_part_9;
    ModelRenderer wrench_part_5;
    ModelRenderer wrench_part_7;
    ModelRenderer wrench_part_6;
    ModelRenderer wrench_part_1;
    ModelRenderer wrench_part_4;
    ModelRenderer wrench_part_3;
    ModelRenderer wrench_part_2;
    ModelRenderer book_4_pages;
    ModelRenderer book_4_bottom;
    ModelRenderer book_4_back;
    //ModelRenderer book_4_botom;
  
  public ScienceCraftingTableModel()
  {
    textureWidth = 256;
    textureHeight = 256;
    
      top = new ModelRenderer(this, 192, 0);
      top.addBox(0F, 0F, 0F, 16, 4, 16);
      top.setRotationPoint(-8F, 8F, -8F);
      top.setTextureSize(256, 256);
      top.mirror = true;
      setRotation(top, 0F, 0F, 0F);
      botom = new ModelRenderer(this, 128, 0);
      botom.addBox(0F, 0F, 0F, 16, 2, 16);
      botom.setRotationPoint(-8F, 22F, -8F);
      botom.setTextureSize(256, 256);
      botom.mirror = true;
      setRotation(botom, 0F, 0F, 0F);
      leg_1 = new ModelRenderer(this, 64, 0);
      leg_1.addBox(0F, 0F, 0F, 2, 10, 2);
      leg_1.setRotationPoint(5F, 12F, -7F);
      leg_1.setTextureSize(256, 256);
      leg_1.mirror = true;
      setRotation(leg_1, 0F, 0F, 0F);
      leg_2 = new ModelRenderer(this, 72, 0);
      leg_2.addBox(1F, 0F, 0F, 2, 10, 2);
      leg_2.setRotationPoint(4F, 12F, 5F);
      leg_2.setTextureSize(256, 256);
      leg_2.mirror = true;
      setRotation(leg_2, 0F, 0F, 0F);
      leg_3 = new ModelRenderer(this, 48, 0);
      leg_3.addBox(0F, 0F, 0F, 2, 10, 2);
      leg_3.setRotationPoint(-7F, 12F, 5F);
      leg_3.setTextureSize(256, 256);
      leg_3.mirror = true;
      setRotation(leg_3, 0F, 0F, 0F);
      leg_4 = new ModelRenderer(this, 56, 0);
      leg_4.addBox(0F, 0F, 0F, 2, 10, 2);
      leg_4.setRotationPoint(-7F, 12F, -7F);
      leg_4.setTextureSize(256, 256);
      leg_4.mirror = true;
      setRotation(leg_4, 0F, 0F, 0F);
      box = new ModelRenderer(this, 80, 0);
      box.addBox(0F, 0F, 0F, 12, 4, 12);
      box.setRotationPoint(-6F, 12F, -6F);
      box.setTextureSize(256, 256);
      box.mirror = true;
      setRotation(box, 0F, 0F, 0F);
      shelf = new ModelRenderer(this, 30, 0);
      shelf.addBox(0F, 0F, 0F, 1, 3, 8);
      shelf.setRotationPoint(-7F, 12F, -4F);
      shelf.setTextureSize(256, 256);
      shelf.mirror = true;
      setRotation(shelf, 0F, 0F, 0F);
      hanel = new ModelRenderer(this, 20, 0);
      hanel.addBox(0F, 0F, 0F, 1, 1, 4);
      hanel.setRotationPoint(-7.5F, 13F, -2F);
      hanel.setTextureSize(256, 256);
      hanel.mirror = true;
      setRotation(hanel, 0F, 0F, 0F);
      hammer_head = new ModelRenderer(this, 16, 245);
      hammer_head.addBox(0F, 0F, 0F, 1, 3, 1);
      hammer_head.setRotationPoint(1F, 15F, 5.5F);
      hammer_head.setTextureSize(256, 256);
      hammer_head.mirror = true;
      setRotation(hammer_head, 0F, 0F, 0F);
      saw_hanel = new ModelRenderer(this, 32, 254);
      saw_hanel.addBox(0F, 0F, 0F, 3, 1, 1);
      saw_hanel.setRotationPoint(-4F, 12F, 5.5F);
      saw_hanel.setTextureSize(256, 256);
      saw_hanel.mirror = true;
      setRotation(saw_hanel, 0F, 0F, 0F);
      saw_middle = new ModelRenderer(this, 32, 242);
      saw_middle.addBox(0F, 0F, 0F, 1, 6, 1);
      saw_middle.setRotationPoint(-3F, 14F, 5.5F);
      saw_middle.setTextureSize(256, 256);
      saw_middle.mirror = true;
      setRotation(saw_middle, 0F, 0F, 0F);
      saw_right = new ModelRenderer(this, 32, 233);
      saw_right.addBox(0F, 0F, 0F, 1, 8, 1);
      saw_right.setRotationPoint(-4F, 13F, 5.5F);
      saw_right.setTextureSize(256, 256);
      saw_right.mirror = true;
      setRotation(saw_right, 0F, 0F, 0F);
      saw_left = new ModelRenderer(this, 32, 249);
      saw_left.addBox(0F, 0F, 0F, 1, 4, 1);
      saw_left.setRotationPoint(-2F, 13F, 5.5F);
      saw_left.setTextureSize(256, 256);
      saw_left.mirror = true;
      setRotation(saw_left, 0F, 0F, 0F);
      hammer_hadle = new ModelRenderer(this, 16, 251);
      hammer_hadle.addBox(0F, 0F, 0F, 1, 4, 1);
      hammer_hadle.setRotationPoint(2F, 12F, 5.5F);
      hammer_hadle.setTextureSize(256, 256);
      hammer_hadle.mirror = true;
      setRotation(hammer_hadle, 0F, 0F, 0F);
      hamer_top = new ModelRenderer(this, 16, 249);
      hamer_top.addBox(0F, 0F, 0F, 2, 1, 1);
      hamer_top.setRotationPoint(2F, 16F, 5.5F);
      hamer_top.setTextureSize(256, 256);
      hamer_top.mirror = true;
      setRotation(hamer_top, 0F, 0F, 0F);
      sizor_hadle_right = new ModelRenderer(this, 0, 250);
      sizor_hadle_right.addBox(0F, 0F, 0F, 1, 2, 1);
      sizor_hadle_right.setRotationPoint(5.5F, 12F, -2F);
      sizor_hadle_right.setTextureSize(256, 256);
      sizor_hadle_right.mirror = true;
      setRotation(sizor_hadle_right, 0F, 0F, 0F);
      scizor_blade_right = new ModelRenderer(this, 0, 242);
      scizor_blade_right.addBox(0F, 0F, 0F, 1, 2, 1);
      scizor_blade_right.setRotationPoint(5.5F, 15F, -2F);
      scizor_blade_right.setTextureSize(256, 256);
      scizor_blade_right.mirror = true;
      setRotation(scizor_blade_right, 0F, 0F, 0F);
      sizor_midle = new ModelRenderer(this, 0, 248);
      sizor_midle.addBox(0F, 0F, 0F, 1, 1, 1);
      sizor_midle.setRotationPoint(5.5F, 14F, -3F);
      sizor_midle.setTextureSize(256, 256);
      sizor_midle.mirror = true;
      setRotation(sizor_midle, 0F, 0F, 0F);
      sizor_handle_left = new ModelRenderer(this, 0, 253);
      sizor_handle_left.addBox(0F, 0F, 0F, 1, 2, 1);
      sizor_handle_left.setRotationPoint(5.5F, 12F, -4F);
      sizor_handle_left.setTextureSize(256, 256);
      sizor_handle_left.mirror = true;
      setRotation(sizor_handle_left, 0F, 0F, 0F);
      sizor_blade_left = new ModelRenderer(this, 0, 245);
      sizor_blade_left.addBox(0F, 0F, 0F, 1, 2, 1);
      sizor_blade_left.setRotationPoint(5.5F, 15F, -4F);
      sizor_blade_left.setTextureSize(256, 256);
      sizor_blade_left.mirror = true;
      setRotation(sizor_blade_left, 0F, 0F, 0F);
      wrench_part_8 = new ModelRenderer(this, 48, 220);
      wrench_part_8.addBox(0F, 0F, 0F, 1, 4, 1);
      wrench_part_8.setRotationPoint(2F, 12F, -6.5F);
      wrench_part_8.setTextureSize(256, 256);
      wrench_part_8.mirror = true;
      setRotation(wrench_part_8, 0F, 0F, 0F);
      wrench_part_10 = new ModelRenderer(this, 48, 214);
      wrench_part_10.addBox(0F, 0F, 0F, 1, 2, 1);
      wrench_part_10.setRotationPoint(4F, 13F, -6.5F);
      wrench_part_10.setTextureSize(256, 256);
      wrench_part_10.mirror = true;
      setRotation(wrench_part_10, 0F, 0F, 0F);
      wrench_part_9 = new ModelRenderer(this, 48, 217);
      wrench_part_9.addBox(0F, 0F, 0F, 1, 2, 1);
      wrench_part_9.setRotationPoint(3F, 12F, -6.5F);
      wrench_part_9.setTextureSize(256, 256);
      wrench_part_9.mirror = true;
      setRotation(wrench_part_9, 0F, 0F, 0F);
      wrench_part_5 = new ModelRenderer(this, 48, 235);
      wrench_part_5.addBox(0F, 0F, 0F, 1, 4, 1);
      wrench_part_5.setRotationPoint(-1F, 15F, -6.5F);
      wrench_part_5.setTextureSize(256, 256);
      wrench_part_5.mirror = true;
      setRotation(wrench_part_5, 0F, 0F, 0F);
      wrench_part_7 = new ModelRenderer(this, 48, 225);
      wrench_part_7.addBox(0F, 0F, 0F, 1, 4, 1);
      wrench_part_7.setRotationPoint(1F, 13F, -6.5F);
      wrench_part_7.setTextureSize(256, 256);
      wrench_part_7.mirror = true;
      setRotation(wrench_part_7, 0F, 0F, 0F);
      wrench_part_6 = new ModelRenderer(this, 48, 230);
      wrench_part_6.addBox(0F, 0F, 0F, 1, 4, 1);
      wrench_part_6.setRotationPoint(0F, 14F, -6.5F);
      wrench_part_6.setTextureSize(256, 256);
      wrench_part_6.mirror = true;
      setRotation(wrench_part_6, 0F, 0F, 0F);
      wrench_part_1 = new ModelRenderer(this, 48, 253);
      wrench_part_1.addBox(0F, 0F, 0F, 1, 2, 1);
      wrench_part_1.setRotationPoint(-5F, 20F, -6.5F);
      wrench_part_1.setTextureSize(256, 256);
      wrench_part_1.mirror = true;
      setRotation(wrench_part_1, 0F, 0F, 0F);
      wrench_part_4 = new ModelRenderer(this, 48, 240);
      wrench_part_4.addBox(0F, 0F, 0F, 1, 4, 1);
      wrench_part_4.setRotationPoint(-2F, 16F, -6.5F);
      wrench_part_4.setTextureSize(256, 256);
      wrench_part_4.mirror = true;
      setRotation(wrench_part_4, 0F, 0F, 0F);
      wrench_part_3 = new ModelRenderer(this, 48, 245);
      wrench_part_3.addBox(0F, 0F, 0F, 1, 3, 1);
      wrench_part_3.setRotationPoint(-3F, 18F, -6.5F);
      wrench_part_3.setTextureSize(256, 256);
      wrench_part_3.mirror = true;
      setRotation(wrench_part_3, 0F, 0F, 0F);
      wrench_part_2 = new ModelRenderer(this, 48, 249);
      wrench_part_2.addBox(0F, 0F, 0F, 1, 3, 1);
      wrench_part_2.setRotationPoint(-4F, 19F, -6.5F);
      wrench_part_2.setTextureSize(256, 256);
      wrench_part_2.mirror = true;
      setRotation(wrench_part_2, 0F, 0F, 0F);
      book_4_pages = new ModelRenderer(this, 64, 245);
      book_4_pages.addBox(0.5F, 0F, 0.2F, 7, 1, 4);
      book_4_pages.setRotationPoint(-4F, 21F, -2F);
      book_4_pages.setTextureSize(256, 256);
      book_4_pages.mirror = true;
      setRotation(book_4_pages, 0F, 0.2617994F, 0F);
      book_4_bottom = new ModelRenderer(this, 64, 238);
      book_4_bottom.addBox(0F, 0F, 0F, 8, 0, 5);
      book_4_bottom.setRotationPoint(-4F, 20.9F, -2F);
      book_4_bottom.setTextureSize(256, 256);
      book_4_bottom.mirror = true;
      setRotation(book_4_bottom, 0F, 0.2617994F, 0F);
      book_4_back = new ModelRenderer(this, 64, 243);
      book_4_back.addBox(0F, 0F, 0F, 8, 1, 0);
      book_4_back.setRotationPoint(-4F, 20.9F, -2F);
      book_4_back.setTextureSize(256, 256);
      book_4_back.mirror = true;
      setRotation(book_4_back, 0F, 0.2617994F, 0F);
      book_4_bottom = new ModelRenderer(this, 64, 238);
      book_4_bottom.addBox(0F, 0F, 0F, 8, 0, 5);
      book_4_bottom.setRotationPoint(-4F, 21.9F, -2F);
      book_4_bottom.setTextureSize(256, 256);
      book_4_bottom.mirror = true;
      setRotation(book_4_bottom, 0F, 0.2617994F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(entity, f, f1, f2, f3, f4, f5);
    top.render(f5);
    botom.render(f5);
    leg_1.render(f5);
    leg_2.render(f5);
    leg_3.render(f5);
    leg_4.render(f5);
    box.render(f5);
    shelf.render(f5);
    hanel.render(f5);
    hammer_head.render(f5);
    saw_hanel.render(f5);
    saw_middle.render(f5);
    saw_right.render(f5);
    saw_left.render(f5);
    hammer_hadle.render(f5);
    hamer_top.render(f5);
    sizor_hadle_right.render(f5);
    scizor_blade_right.render(f5);
    sizor_midle.render(f5);
    sizor_handle_left.render(f5);
    sizor_blade_left.render(f5);
    wrench_part_8.render(f5);
    wrench_part_10.render(f5);
    wrench_part_9.render(f5);
    wrench_part_5.render(f5);
    wrench_part_7.render(f5);
    wrench_part_6.render(f5);
    wrench_part_1.render(f5);
    wrench_part_4.render(f5);
    wrench_part_3.render(f5);
    wrench_part_2.render(f5);
    book_4_pages.render(f5);
    book_4_bottom.render(f5);
    book_4_back.render(f5);
    book_4_bottom.render(f5);
  }
  
  public void renderModel(float scale)
  {
	  top.render(scale);
	  botom.render(scale);
	  leg_1.render(scale);
	  leg_2.render(scale);
	  leg_3.render(scale);
	  leg_4.render(scale);
	  box.render(scale);
	  shelf.render(scale);
	  hanel.render(scale);
	  hammer_head.render(scale);
	  saw_hanel.render(scale);
	  saw_middle.render(scale);
	  saw_right.render(scale);
	  saw_left.render(scale);
	  hammer_hadle.render(scale);
	  hamer_top.render(scale);
	  sizor_hadle_right.render(scale);
	  scizor_blade_right.render(scale);
	  sizor_midle.render(scale);
	  sizor_handle_left.render(scale);
	  sizor_blade_left.render(scale);
	  wrench_part_8.render(scale);
	  wrench_part_10.render(scale);
	  wrench_part_9.render(scale);
	  wrench_part_5.render(scale);
	  wrench_part_7.render(scale);
	  wrench_part_6.render(scale);
	  wrench_part_1.render(scale);
	  wrench_part_4.render(scale);
	  wrench_part_3.render(scale);
	  wrench_part_2.render(scale);
	  book_4_pages.render(scale);
	  book_4_bottom.render(scale);
	  book_4_back.render(scale);
	  book_4_bottom.render(scale);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
  }

}