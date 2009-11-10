package net.bodz.bas.ui.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.util.Factory;

@Retention(RetentionPolicy.RUNTIME)
public @interface Font {

    String name() default "";

    int height() default 0;

    int PLAIN = java.awt.Font.PLAIN;
    int BOLD = java.awt.Font.BOLD;
    int ITALIC = java.awt.Font.ITALIC;

    int style() default PLAIN;

    /**
     * create(usage) -> FontData
     */
    Class<? extends Factory<?>> factory() default Factory_Object_null.class;

}
