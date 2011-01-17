package net.bodz.bas.meta.obs;

/**
 * obfuscator hint
 */
public @interface KeepTypeOnly {

    boolean shrink() default true;

    boolean obfuscate() default true;

}
