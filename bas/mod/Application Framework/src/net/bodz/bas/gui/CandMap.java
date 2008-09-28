package net.bodz.bas.gui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Bean Annotation
 * 
 * Candidates Map
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.METHOD })
public @interface CandMap {

    Class<? extends CandMapProvider> value() default CandMapProvider.class;

}
