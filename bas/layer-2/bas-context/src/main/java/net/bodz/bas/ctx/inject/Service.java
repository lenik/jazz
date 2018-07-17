package net.bodz.bas.ctx.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;

@_Component
@Documented
@IndexedType(publishDir = PublishDir.features)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    String name() default "";

}
