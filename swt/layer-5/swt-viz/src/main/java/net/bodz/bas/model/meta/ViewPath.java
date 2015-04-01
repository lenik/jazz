package net.bodz.bas.model.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ViewPath {

    /**
     * View path. Preferred path format: bar.subbar...base
     * <p>
     * A view should be {@link org.eclipse.swt.widgets.Composite}.
     * <p>
     * A link to the view may be place in the expand-bar whose barName.startsWith(value).
     * <p>
     * Discard the prefix part (bar.subbar... determined at runtime), items in different subbar will
     * separated by a horizontal line.
     */
    String[] value() default {};

}
