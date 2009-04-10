package net.bodz.bas.gui.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.mod.Factory;

@Retention(RetentionPolicy.RUNTIME)
public @interface Icon {

    /**
     * resource-path
     */
    String[] value() default {};

    /**
     * create(usage) -> resource-path or ImageData, where usage is `big',
     * `small', etc.
     */
    Class<? extends Factory<?>> factory() default Factory_Object_null.class;

}
