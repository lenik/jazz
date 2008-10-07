package net.bodz.swt.gui.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD })
public @interface View {

    /**
     * View path. Preferred path format: bar.subbar...base
     * <p>
     * A link to the view will place in the expand-bar whose
     * barName.startsWith(value).
     * <p>
     * Discard the prefix part (bar.subbar... determined at runtime), items in
     * different subbar will separated by a horizontal line.
     */
    String value() default "";

}
