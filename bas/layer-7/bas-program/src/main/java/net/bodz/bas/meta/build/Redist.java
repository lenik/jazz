package net.bodz.bas.meta.build;

public @interface Redist {

    /** Classifications */
    Class<?>[] value() default Object.class;

}
