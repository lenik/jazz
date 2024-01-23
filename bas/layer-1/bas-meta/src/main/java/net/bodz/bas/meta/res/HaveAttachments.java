package net.bodz.bas.meta.res;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;

@Documented
@IndexedType(publishDir = PublishDir.features)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HaveAttachments {

    String dirNaming() default ""; // "${primary-key[0]}/${primary-key[1]}/...";

    String uploadLocation() default "";

    long maxSize() default 0;

    String sharedDict() default "";

}
