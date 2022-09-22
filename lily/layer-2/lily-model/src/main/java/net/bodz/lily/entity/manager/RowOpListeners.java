package net.bodz.lily.entity.manager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.persistence.Inheritance;

@Inheritance
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RowOpListeners {

    Class<? extends IJdbcRowOpListener>[] value();

}
