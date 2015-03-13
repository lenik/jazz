package net.bodz.lily.model.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableDefaults {

    int accessMode() default CoObject.M_PUBLIC;

    int acl() default 0;

    class fn {
        int getDefaultAccessMode(Class<?> clazz) {
            clazz.getAnnotation(TableDefaults.class);
            return 0;
        }
    }

}
