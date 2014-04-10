package net.bodz.bas.gui.css3;

public interface ICss3Keywords {

    /**
     * Used by:
     * <ul>
     * <li><code>max-width</code>, <code>max-height</code>: No limit on the width or height of the
     * box.
     * </ul>
     */
    int NONE = 0;
    int INITIAL = 1;

    /**
     * Specifying a value of inherit for any CSS property that’s applied to an element will cause
     * the element to gain its <i>parent element</i>’s computed value for the property in question.
     * 
     * Use null instead.
     */
    @Deprecated
    int INHERIT = -1;

    int LENGTH = 2;
    int PERCENTAGE = 3;
    int NUMBER = 4;

    /**
     * For non-replaced elements, the effect of this value depends on which of related properties
     * have the value 'auto' as well. See the sections on the width and height of absolutely
     * positioned, non-replaced elements for details. For replaced elements, the effect of this
     * value depends only on the intrinsic dimensions of the replaced content. See the sections on
     * the width and height of absolutely positioned, replaced elements for details.
     */
    int AUTO = 10;
    int NORMAL = 11;

    // ________________________________________________________________________
    // ⇱ Part: Background Position
    //
    int __pos__ = 100;
    int TOP = __pos__ + 0;
    int RIGHT = __pos__ + 1;
    int BOTTOM = __pos__ + 2;
    int LEFT = __pos__ + 3;
    int CENTER = __pos__ + 4;
    int BASELINE = __pos__ + 5;
    int MIDDLE = __pos__ + 6;
    int SUB = __pos__ + 7;
    int SUPER = __pos__ + 8;
    int TEXT_TOP = __pos__ + 9;
    int TEXT_BOTTOM = __pos__ + 10;

    // ________________________________________________________________________
    // ⇱ Part: Font Size
    //
    int __font__ = 200;

    /**
     * An <absolute-size> keyword is an index to a table of font sizes computed and kept by the UA.
     * Possible values are:
     */
    int XX_SMALL = __font__ + 0;
    int X_SMALL = __font__ + 1;
    int SMALL = __font__ + 2;
    int MEDIUM = __font__ + 3;
    int LARGE = __font__ + 4;
    int X_LARGE = __font__ + 5;
    int XX_LARGE = __font__ + 6;

    /**
     * A <relative-size> keyword is interpreted relative to the table of font sizes and the font
     * size of the parent element. Possible values are: [ larger | smaller ]. For example, if the
     * parent element has a font size of 'medium', a value of 'larger' will make the font size of
     * the current element be 'large'. If the parent element's size is not close to a table entry,
     * the UA is free to interpolate between table entries or round off to the closest one. The UA
     * may have to extrapolate table values if the numerical value goes beyond the keywords.
     */
    int LARGER = __font__ + 7;
    int SMALLER = __font__ + 8;

}
