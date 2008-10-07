package net.bodz.bas.gui.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.gui.CandMapProvider;

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
