package net.bodz.bas.ar.zip;

public @interface ExtraFieldType {

    short id();

    boolean sizeTotal() default false;

// int size() default 0;
    boolean bigEndian() default false;

}
