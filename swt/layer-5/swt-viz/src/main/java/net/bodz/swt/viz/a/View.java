package net.bodz.swt.viz.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.swt.widgets.Composite;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface View {

    /**
     * View path. Preferred path format: bar.subbar...base
     * <p>
     * A view shall be {@link Composite}.
     * <p>
     * A link to the view may be place in the expand-bar whose barName.startsWith(value).
     * <p>
     * Discard the prefix part (bar.subbar... determined at runtime), items in different subbar will
     * separated by a horizontal line.
     */
    String[] value() default {};

}
