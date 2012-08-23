package net.bodz.bas.gui.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.gui.ia.CandListProvider;

/**
 * Bean Annotation
 * 
 * Candidates List
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface CandList {

    Class<? extends CandListProvider> value() default CandListProvider.class;

}