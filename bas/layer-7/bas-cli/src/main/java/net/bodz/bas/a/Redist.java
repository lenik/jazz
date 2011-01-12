package net.bodz.bas.a;

public @interface Redist {

    /** Classifications */
    Class<?>[] value() default Object.class;

}
