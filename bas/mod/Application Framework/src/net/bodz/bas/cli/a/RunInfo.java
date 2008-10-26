package net.bodz.bas.cli.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.cli._RunInfo;
import net.bodz.bas.cli.util.Conditions;

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
     * [type-condition|] library-spec
     * 
     * if type-condition isn't specified, 'fortype %libspec' is used.
     * 
     * @see _RunInfo#findLib(String)
     * @see Conditions#fortype(String)
     */
    String[] lib() default {};

    /**
     * [type-condition|] expression
     * 
     * load before parse any options.
     */
    String[] load() default {};

    /**
     * [type-condition|] expression
     * 
     * load after the kernel is loaded.
     */
    String[] loadDelayed() default {};

}
