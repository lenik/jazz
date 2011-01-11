package net.bodz.bas.ui.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.ui.CandMapProvider;

/**
 * Bean Annotation
 * 
 * Candidates Map
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.PARAMETER })
public @interface CandMap {

    Class<? extends CandMapProvider> value() default CandMapProvider.class;

}
