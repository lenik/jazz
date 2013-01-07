package net.bodz.bas.repr.viz.decl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.gui.dialog.IProposalListProvider;

/**
 * Bean Annotation
 * 
 * Candidates List
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface CandList {

    Class<? extends IProposalListProvider> value() default IProposalListProvider.class;

}
