package net.bodz.bas.gui.style;

import java.io.Serializable;

import net.bodz.bas.gui.style.property.LineCapMode;
import net.bodz.bas.gui.style.property.LineJoinMode;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public interface IStrokeType
        extends Serializable {

    LengthMeasure getWidth();

    LineCapMode getLineCap();

    LineJoinMode getLineJoin();

    int[] getDashPattern();

}
