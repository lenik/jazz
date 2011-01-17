package net.bodz.bas.meta.obs;

/**
 * obfuscator hint
 */
public @interface Keep {

    boolean shrink() default true;

    boolean obfuscate() default true;

}
