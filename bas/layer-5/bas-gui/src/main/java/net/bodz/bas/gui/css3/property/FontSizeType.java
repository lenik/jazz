package net.bodz.bas.gui.css3.property;

public enum FontSizeType {

    /**
     * An <absolute-size> keyword is an index to a table of font sizes computed and kept by the UA.
     * Possible values are:
     */
    // absolute_size,
    xx_small, x_small, small, medium, large, x_large, xx_large,

    /**
     * A <relative-size> keyword is interpreted relative to the table of font sizes and the font
     * size of the parent element. Possible values are: [ larger | smaller ]. For example, if the
     * parent element has a font size of 'medium', a value of 'larger' will make the font size of
     * the current element be 'large'. If the parent element's size is not close to a table entry,
     * the UA is free to interpolate between table entries or round off to the closest one. The UA
     * may have to extrapolate table values if the numerical value goes beyond the keywords.
     */
    // relative_size,
    larger, smaller,

    length,

    percentage,

}
