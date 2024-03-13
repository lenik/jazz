package net.bodz.lily.entity.esm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A TypeScript class name to be used in the ES module.
 *
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface TsType {

    Class<?> value() default void.class;

    String qName() default "";

    /**
     * for Array and collection types.
     */
    Class<?> componentType() default void.class;

    String componentQName() default "";

    /**
     * By default, a -TypeInfo class should be defined along with the TypeScript type.
     */
    String typeInfo() default "";

}
