package net.bodz.bas.cli.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.cli.ClassOptions;
import net.bodz.bas.cli._Option;

/**
 * [OPTION] [--] FILES
 * <p>
 * OPTION: -NAME PARAM or --NAME=PARAM
 * 
 * <ul>
 * <li>NAME is the field/property name declared this annotation
 * <li>using shortest non-ambiguous option names.
 * <li>for boolean types, --no-NAME is auto defined and no PARAM
 * <li>for collection/array types, multiple options are allowed
 * <li>for map types, PARAM has form of --NAME=KEY=VAL, where valparser and valcheck are applied on
 * VAL.
 * <li>for method/callback types, the number of PARAM is determined by the arguments of that method,
 * and the wanted types applied on each argument.
 * </ul>
 * 
 * Recommend eclipse template `opt':
 * 
 * <pre>
 * ${:import(
 *     net.bodz.bas.cli.Option
 * )}@Option(alias=&quot;${alias}&quot;, vnam=&quot;${value}&quot;, doc=&quot;${description}&quot;)
 * private ${type:newType(java.lang.String)} ${name:newName(option)};
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
public @interface Option {

    /**
     * {@link _Option#isWeak() weak-option} is option with name prefixed by '.', such option can be
     * overrided by options with the same name declared in derived classes.
     */
    String name() default "";

    /**
     * {@link ClassOptions#isReusable(String) weak-alias} is aliases prefixed by '.', such alias can
     * be reused by options declared in derived classes.
     */
    String[] alias() default {};

    String vnam() default "";

    String doc() default "";

    boolean hidden() default false;

    boolean required() default false;

    /**
     * when the VALUE is missing in --option=VALUE, --option VALUE, then
     * <ul>
     * <li>--option if optional() is defined, then set to optional(). otherwise:
     * <ul>
     * <li>true for boolean type
     * 
     * <li>1 for number types
     * 
     * <li>the value of parse result of "" for other types
     * </ul>
     * <li>--option= the value of parse result of ""
     * </ul>
     */
    String optional() default "";

    /**
     * when valtype is specified, the value must be collection type and the parser and check are
     * applied for each item in the collection.
     */
    Class<?> valtype() default void.class;

    /**
     * Get option also from FILES by index. for collection/map types, this is the starting index.
     */
    int fileIndex() default -1;

}
