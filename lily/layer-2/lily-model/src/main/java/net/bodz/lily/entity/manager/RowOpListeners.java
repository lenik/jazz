package net.bodz.lily.entity.manager;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.persistence.Inheritance;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;

@Documented
@Inheritance
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@IndexedType(publishDir = PublishDir.features, includeAbstract = true)
public @interface RowOpListeners {

    Class<? extends IJdbcRowOpListener>[] value();

}
