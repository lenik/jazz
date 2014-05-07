package net.bodz.bas.ui.css3.property;

public enum VisibilityMode {

    /** The generated box is visible. */
    visible,

    /**
     * The generated box is invisible (fully transparent, nothing is drawn), but still affects
     * layout. Furthermore, descendants of the element will be visible if they have 'visibility:
     * visible'.
     */
    hidden,

    /**
     * Please consult the section on dynamic row and column effects in tables. If used on elements
     * other than rows, row groups, columns, or column groups, 'collapse' has the same meaning as
     * 'hidden'.
     */
    collapse,

}
