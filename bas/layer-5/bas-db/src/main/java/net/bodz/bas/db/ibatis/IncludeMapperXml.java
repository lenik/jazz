package net.bodz.bas.db.ibatis;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;

/**
 * Specify extra mapper XML files to be included.
 */
@Documented
@IndexedType(includeAbstract = true, publishDir = PublishDir.features)
@Retention(RetentionPolicy.RUNTIME)
public @interface IncludeMapperXml {

    int priority() default 0;

    String[] value() default {};

}
