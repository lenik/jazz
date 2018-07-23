package net.bodz.bas.ctx.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;
import net.bodz.bas.meta.meta.Implicit;

@Documented
@IndexedType(publishDir = PublishDir.features, includeClass = false, includeAnnotation = true)
@Implicit
@Retention(RetentionPolicy.RUNTIME)
public @interface _Component {

    String name() default "";

}
