package net.bodz.bas.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.loader.DefaultBooter;
import net.bodz.bas.loader.LoadConfig;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BootInfo {

    /**
     * must add to classpath before launch.
     * 
     * <strong> <code> bodz_bas </code> must be add to syslibs. </strong>
     */
    String[] syslibs() default {};

    /**
     * after fully booted, the user libs are added to the last class loader returned by
     * {@link LoadConfig#getLoader(ClassLoader)}.
     */
    String[] userlibs() default {};

    /**
     * syslibs are visible to the booter class, and the booter class itself must be with-in the
     * syslibs.
     * 
     * userlibs are passed to the booter's main() method.
     * <p>
     * The booter shall create a temporary class loader to get more boot info and load configs, and
     * drop the temporary loader after fully booted.
     * <p>
     * <code>
     * booter-main("-l", USERLIB, ..., "--", MAINCLASS, ARGS)
     * </code>
     * <p>
     * The default booter is {@link DefaultBooter}.
     */
    Class<?> booter() default DefaultBooter.class;

    Class<? extends LoadConfig>[] configs() default {};

    /**
     * arguments passed to ctors of each {@link LoadConfig}s.
     */
    String[] args() default {};

}
