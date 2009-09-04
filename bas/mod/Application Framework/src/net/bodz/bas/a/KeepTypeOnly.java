package net.bodz.bas.a;

/**
 * obfuscator hint
 */
public @interface KeepTypeOnly {

    boolean shrink() default true;

    boolean obfuscate() default true;

}
