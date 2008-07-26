package net.bodz.bas.annotations;

public @interface Redist {

    /** Classifications */
    Class<?>[] value() default Object.class;

}
