package net.bodz.bas.cli;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.loader.JavaLibraryLoader;

/**
 * Dependancy and loading information.
 * 
 * <p>
 * load-expression: "cli-func (`|' argument)*
 * 
 * @example Expression Examples:
 *          <p>
 *          CLIFunctions.register(&quot;lfind&quot;,
 *          CLIFunctions.wrap(Lapiota.class, &quot;findcp&quot;));<br>
 *          &#64;RunInfo(load = "findcp|eclipse&#42;/plugins/org.eclipse.core*")
 *          <br>
 *          public class Program extends BasicCLI {}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RunInfo {

    Class<?>[] init() default {};

    /**
     * <code>jarname.jar</code> (with extension) is searched and loaded using
     * {@link JavaLibraryLoader}.
     * <p>
     * <code>libname</code> (without extension) are resolved using
     * <code>libraries.ini</code> file, if <code>libname</code> isn't defined in
     * <code>libraries.ini</code> , then <code>libname.jar</code> is used.
     */
    String[] lib() default {};

    /**
     * load before parse any options.
     */
    String[] load() default {};

    /**
     * load after the kernel is loaded.
     */
    String[] loadDelayed() default {};

}
