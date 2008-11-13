package net.bodz.bas.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.loader.LoadByLauncher;
import net.bodz.bas.loader.LoadConfig;

/**
 * @see BootInfo#booter()
 * @see LoadConfig#getLoader(ClassLoader)
 */
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LoadBy {

    /**
     * If a class is runnable, a launcher can be specified.
     * 
     * A launcher has a static main method and its first argument is the class
     * name of the ClassLoader.
     * 
     * The default Launcher is {@link LoadByLauncher}.
     */
    Class<?> launcher() default LoadByLauncher.class;

    /**
     * This is used by wrappers generator only. because when you try to get the
     * {@link LoadBoot} annotation, the class is already loaded.
     */
    Class<? extends ClassLoader> value() default ClassLoader.class;

}
