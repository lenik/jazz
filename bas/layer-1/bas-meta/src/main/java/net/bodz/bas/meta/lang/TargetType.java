package net.bodz.bas.meta.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * For example: max-length only meaningful for char sequences:
 * 
 * <pre>
 * &#064;TargetType(CharSequence.class)
 * &#064;interface MaxLength {
 * }
 * 
 * &#064;MaxLength(10)
 * String field1;
 * 
 * &#064;MaxLength(10)
 * class Class1
 *         implements CharSequence {
 * }
 * </pre>
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.ANNOTATION_TYPE)
public @interface TargetType {

    Class<?>[] value();

}
