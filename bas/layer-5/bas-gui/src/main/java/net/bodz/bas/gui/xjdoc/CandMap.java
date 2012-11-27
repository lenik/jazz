package net.bodz.bas.gui.xjdoc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.gui.dialog.IProposalMapProvider;

/**
 * Bean Annotation
 * 
 * Candidates Map
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface CandMap {

    Class<? extends IProposalMapProvider> value() default IProposalMapProvider.class;

}
