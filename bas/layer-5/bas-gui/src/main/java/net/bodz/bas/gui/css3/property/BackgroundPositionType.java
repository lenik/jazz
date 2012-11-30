package net.bodz.bas.gui.css3.property;

public enum BackgroundPositionType {

    /**
     * A percentage X aligns the point X% across (for horizontal) or down (for vertical) the image
     * with the point X% across (for horizontal) or down (for vertical) the element's padding box.
     * For example, with a value pair of '0% 0%',the upper left corner of the image is aligned with
     * the upper left corner of the padding box. A value pair of '100% 100%' places the lower right
     * corner of the image in the lower right corner of the padding box. With a value pair of '14%
     * 84%', the point 14% across and 84% down the image is to be placed at the point 14% across and
     * 84% down the padding box.
     */
    percentage,

    /**
     * A length L aligns the top left corner of the image a distance L to the right of (for
     * horizontal) or below (for vertical) the top left corner of the element's padding box. For
     * example, with a value pair of '2cm 1cm', the upper left corner of the image is placed 2cm to
     * the right and 1cm below the upper left corner of the padding box.
     */
    length,

    /** Equivalent to '0%' for the vertical position. */
    top,

    /** Equivalent to '100%' for the horizontal position. */
    right,

    /** Equivalent to '100%' for the vertical position. */
    bottom,

    /** Equivalent to '0%' for the horizontal position. */
    left,

    /**
     * Equivalent to '50%' for the horizontal position if it is not otherwise given, or '50%' for
     * the vertical position if it is.
     */
    center,

}
