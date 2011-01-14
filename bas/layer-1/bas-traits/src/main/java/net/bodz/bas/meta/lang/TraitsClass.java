package net.bodz.bas.meta.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.lang.IQueryable;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER })
// , ElementType.TYPE_PARAMETER for Java-7
public @interface TraitsClass {

    Class<? extends IQueryable> value();

}
