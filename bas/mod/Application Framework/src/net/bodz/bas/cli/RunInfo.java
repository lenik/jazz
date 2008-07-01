package net.bodz.bas.cli;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RunInfo {

    Class<?>[] init() default {};

    /**
     * Will be expanded to variable $libLIBNAME, which contains the latest
     * version number.
     */
    String[] lib() default {};

    /**
     * Find jar in the java lib search path.
     * 
     * For lapiota, it should be placed under /lapiota/usr/lib/java
     */
    String[] jar() default {};

    /**
     * load before parse any options
     */
    String[] _load() default {};

    /**
     * load="cli-func (`|' argument)*
     * 
     * @example CLIFunctions.register(&quot;lfind&quot;,
     *          CLIFunctions.wrap(Lapiota.class, &quot;findcp&quot;));<br>
     *          &#64;RunInfo(load =
     *          "findcp|eclipse&#42;/plugins/org.eclipse.core*") <br>
     *          public class Program extends BasicCLI {}
     */
    String[] load() default {};

}
