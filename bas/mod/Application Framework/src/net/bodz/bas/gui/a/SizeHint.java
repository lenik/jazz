package net.bodz.bas.gui.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.mod.Factory;

@Retention(RetentionPolicy.RUNTIME)
public @interface SizeHint {

    /** width or columns */
    int h() default 0;

    /** height or rows */
    int v() default 0;

    /**
     * create([usage]) -> Point
     */
    Class<? extends Factory> factory() default Factory.class;

}
