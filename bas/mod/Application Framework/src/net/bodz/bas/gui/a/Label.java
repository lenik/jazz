package net.bodz.bas.gui.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.mod.Factory;

@Retention(RetentionPolicy.RUNTIME)
public @interface Label {

    /**
     * label text
     */
    String value() default "";

    /**
     * create(usage) -> label text
     */
    Class<? extends Factory> factory() default Factory.class;

}
