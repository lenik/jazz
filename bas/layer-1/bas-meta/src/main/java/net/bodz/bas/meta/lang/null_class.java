package net.bodz.bas.meta.lang;

/**
 * Used for:
 * <ul>
 * <li>null.class
 * <li>class literals in fields of an annotation class can't be <code>null</code>, for example:
 * 
 * <pre>
 * class Serializable_null implements Serializable, null_class {
 *     private static final long serialVersionUID = 0;
 * }
 * 
 * &#064;interface Ann {
 *     Class&lt;? extends Serializable&gt; field() default Serializable_null.class;
 * }
 * 
 * </pre>
 * 
 * </ul>
 */
public interface null_class {

}
