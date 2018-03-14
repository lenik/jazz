package net.bodz.bas.db.ibatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;
import net.bodz.bas.meta.meta.PatchType;

/**
 *
 */
@IndexedType(publishDir = PublishDir.features, includeAbstract = true, includeNonPublic = true)
@PatchType
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PatchMapperXml {

    boolean xjdoc() default true;

    String[] resources() default {};

}
