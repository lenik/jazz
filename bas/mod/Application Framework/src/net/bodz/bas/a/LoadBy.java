package net.bodz.bas.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.cli.a.RunInfo;
import net.bodz.bas.loader.LoadByLauncher;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LoadBy {

    /**
     * This is used wrappers generator only. because when you try to get the
     * {@link LoadBy} annotation, the class is already loaded.
     */
    Class<? extends ClassLoader> value() default ClassLoader.class;

    /**
     * If a class is runnable, a launcher can be specified.
     * 
     * A launcher has a static main method and its first argument is the class
     * name of the ClassLoader.
     * 
     * The default Launcher is {@link LoadByLauncher}.
     */
    Class<?> launcher() default LoadByLauncher.class;

    int BOOT    = 1;
    int LIB     = 2;
    int DELAYED = 4;

    /**
     * {@link RunInfo#load()} of a class shall be pre-loaded (using the system
     * class-loader), before go deeper into the class, or launch it.
     */
    int preload() default BOOT;

}
