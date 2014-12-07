package net.bodz.bas.repr.form.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.ui.dialog.IProposalListProvider;
import net.bodz.bas.ui.dialog.IProposalMapProvider;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface Proposals {

    Class<? extends IProposalListProvider> list() default IProposalListProvider.class;

    Class<? extends IProposalMapProvider> map() default IProposalMapProvider.class;

}
