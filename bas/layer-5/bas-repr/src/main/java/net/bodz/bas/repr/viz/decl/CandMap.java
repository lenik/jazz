package net.bodz.bas.repr.viz.decl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.ui.dialog.IProposalMapProvider;

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
