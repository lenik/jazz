package net.bodz.lily.model.base.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface AccessControl {

    /**
     * By default, login is needed.
     */
    boolean anonymous() default false;

    /**
     * The permission ids required.
     */
    String[] require() default {};

}
