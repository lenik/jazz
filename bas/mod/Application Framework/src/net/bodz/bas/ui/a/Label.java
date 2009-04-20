package net.bodz.bas.ui.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.util.Factory;

@Retention(RetentionPolicy.RUNTIME)
public @interface Label {

    /**
     * label text
     */
    String value() default "";

    /**
     * create(usage) -> label text
     */
    Class<? extends Factory<String>> factory() default Factory_String_null.class;

}
