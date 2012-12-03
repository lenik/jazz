package net.bodz.bas.gui.css3;

import java.io.Serializable;

import net.bodz.bas.gui.css3.property.BorderStyleMode;
import net.bodz.bas.gui.spec0.IColor;
import net.bodz.bas.i18n.unit.Measure;

public class Border
        implements Serializable {

    private static final long serialVersionUID = 1L;

    BorderStyleMode style;
    Measure width;
    IColor color;
    boolean useCurrentColor = true;

    Measure radiusLeft;
    Measure radiusRight; // = left.

}
