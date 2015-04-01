package net.bodz.bas.meta.decl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, //
        ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR })
public @interface ItemType {

    Class<?> value();

}
