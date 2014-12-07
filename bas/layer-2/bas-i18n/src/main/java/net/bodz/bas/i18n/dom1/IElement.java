package net.bodz.bas.i18n.dom1;

import java.lang.reflect.Modifier;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.bean.DetailLevel;

/**
 * TODO Refactor to IUIDescribed.ui: IUIDescriptor
 */
public interface IElement {

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
     * @see DetailLevel#CRITICAL
     * @see DetailLevel#NORMAL
     * @see DetailLevel#EXTEND
     * @see DetailLevel#EXPERT
     * @see DetailLevel#EXPERT2
     * @see DetailLevel#HIDDEN
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

}
