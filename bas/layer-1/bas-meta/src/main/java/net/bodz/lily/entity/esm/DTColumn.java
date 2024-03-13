package net.bodz.lily.entity.esm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface DTColumn {

    /**
     * hidden by default.
     *
     * Same as className="hidden".
     */
    boolean hidden() default false;

    /**
     * CSS class names, separated by whitespace.
     */
    String className() default "";

    /**
     * type name, should be pre-defined in the typeMap.
     */
    String dataType() default "";

    String dataFormat() default "";

    /**
     * expression or function(lambda) to render the data to HTMLElement.
     */
    String dataRender() default "";

}
