package net.bodz.bas.commons.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target( { ElementType.ANNOTATION_TYPE })
public @interface TargetType {

    Class<?>[] value();

}
