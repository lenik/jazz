package net.bodz.swt.gui.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.swt.widgets.Composite;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD })
public @interface MenuContrib {

    /**
     * Path for menubar, toolbar, etc.
     * <p>
     * Preferred path format: <code>bar.subbar...base</code>. The item will be
     * placed in the bar whose barName.startsWith(value).
     * <p>
     * Discard the prefix part (bar.subbar... determined at runtime), items in
     * different subbar will separated by a short line.
     * 
     * A view shall be {@link Composite}.
     * <p>
     * A link to the view may be place in the expand-bar whose
     * barName.startsWith(value).
     */
    String[] value() default {};

}
