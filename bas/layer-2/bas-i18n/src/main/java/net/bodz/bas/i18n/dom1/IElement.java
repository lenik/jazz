package net.bodz.bas.i18n.dom1;

import java.lang.reflect.Modifier;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.t.order.IPriority;

/**
 * TODO Refactor to IUIDescribed.ui: IUIDescriptor
 */
public interface IElement
        extends IPriority {

    /**
     * The unique name of the potato element.
     *
     * @return non-<code>null</code> name.
     */
    String getName();

    /**
     * The human readable display name.
     *
     * @return non-<code>null</code> string of display name.
     */
    iString getLabel();

    /**
     * @return <code>null</code> If no description available.
     */
    iString getDescription();

    /**
     * @return <code>null</code> if no help doc available.
     */
    iString getHelpDoc();

    /**
     * The detail level controls whether this element is displayed. When user specifies a display
     * detail level, then only elements with detail level smaller then the the display detail level
     * is displayed.
     *
     * @see DetailLevel
     */
    int getDetailLevel();

    /**
     * Returns the Java language modifiers for this element, as an integer. The {@link Modifier}
     * class should be used to decode the modifiers in the integer.
     *
     * @return the Java language modifiers for the underlying element.
     * @see java.lang.reflect.Modifier
     */
    int getModifiers();

    NullElement NULL = NullElement.INSTANCE;

    class fn {

        public static final String labelName(IElement element) {
            return labelName(element, false);
        }

        public static final String labelName(IElement element, boolean allowEmpty) {
            if (element == null)
                return null;

            String label = Nullables.toString(element.getLabel());
            if (label != null) {
                if (allowEmpty || !label.isEmpty())
                    return label;
            }

            return element.getName();
        }

        public static final String description(IElement element) {
            return description(element, null);
        }

        /**
         * @return <code>null</code> if the element is <code>null</code>.
         */
        public static final String description(IElement element, String nullStr) {
            if (element == null)
                return null;

            String description = Nullables.toString(element.getDescription());
            if (description != null)
                return description;

            return nullStr;
        }

    }

}
