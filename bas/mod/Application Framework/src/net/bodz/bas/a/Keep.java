package net.bodz.bas.a;

/**
 * obfuscator hint
 */
public @interface Keep {

    boolean shrink() default true;

    boolean obfuscate() default true;

}
