package net.bodz.bas.ui.css3.property;

import net.bodz.bas.meta.source.boDzFeature;
import net.bodz.bas.ui.style.IStrokeType;

public enum BorderStyleMode {

    /** No border; the computed border width is zero. */
    none,

    /** Same as 'none', except in terms of border conflict resolution for table elements. */
    hidden,

    /** The border is a series of dots. */
    dotted(1, 1),

    /** The border is a series of short line segments. */
    dashed(3, 1),

    /** The border is a single line segment. */
    solid(1),

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

    /**
     * @see IStrokeType#getDashPattern()
     */
    @boDzFeature
    pattern,

    ;

    private final float[] dashPatternf;
    private final int[] dashPattern;

    BorderStyleMode(int... dashPattern) {
        this.dashPattern = dashPattern;
        dashPatternf = new float[dashPattern.length];
        for (int i = 0; i < dashPattern.length; i++)
            dashPatternf[i] = dashPattern[i];
    }

    public float[] getDashPatternf() {
        return dashPatternf;
    }

    public int[] getDashPattern() {
        return dashPattern;
    }

}
