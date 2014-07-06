package magicAndIndustry.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BlockClass 
{
	/**
	 * The name of the block to register.
	 */
	String blockName();
	
	String textureName() default "";
	
	boolean doTextures() default true;
	
	boolean doOreDict() default false;
	
	boolean doTab() default true;
	
	boolean doRegistration() default true;
}
