package net.bodz.bas.gui.css3.property;

public enum StrokePatternMode {

    /** No border; the computed border width is zero. */
    none,

    /** Same as 'none', except in terms of border conflict resolution for table elements. */
    hidden,

    /** The border is a series of dots. */
    dotted,

    /** The border is a series of short line segments. */
    dashed,

    /** The border is a single line segment. */
    solid,

    /**
     * The border is two solid lines. The sum of the two lines and the space between them equals the
     * value of 'border-width'.
     */
    double_,

    /** The border looks as though it were carved into the canvas. */
    groove,

    /** The opposite of 'groove': the border looks as though it were coming out of the canvas. */
    ridge,

    /** The border makes the box look as though it were embedded in the canvas. */
    inset,

    /**
     * The opposite of 'inset': the border makes the box look as though it were coming out of the
     * canvas.
     */
    outset,

}
