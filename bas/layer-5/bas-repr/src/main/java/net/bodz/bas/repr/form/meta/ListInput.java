package net.bodz.bas.repr.form.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.repr.form.SortOrder;

@Retention(RetentionPolicy.RUNTIME)
public @interface ListInput {

    /**
     * Sort the list items in the view.
     */
    SortOrder sort() default SortOrder.NONE;

}
