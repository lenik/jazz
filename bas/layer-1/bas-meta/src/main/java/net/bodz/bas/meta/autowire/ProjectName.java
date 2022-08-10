package net.bodz.bas.meta.autowire;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;

@IndexedType(publishDir = PublishDir.features, includeAbstract = true)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProjectName {

    String value();

}
