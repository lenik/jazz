package net.bodz.bas.gui.css3;

import java.io.Serializable;

import net.bodz.bas.gui.css3.property.BorderStyleMode;
import net.bodz.bas.gui.spec0.IColor;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public class Border
        implements Serializable {

    private static final long serialVersionUID = 1L;

    BorderStyleMode style;
    LengthMeasure width;
    IColor color;
    boolean useCurrentColor = true;

    LengthMeasure radiusLeft;
    LengthMeasure radiusRight; // = left.

}
