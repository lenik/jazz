package net.bodz.bas.ui.style;

import java.io.Serializable;

import net.bodz.bas.i18n.unit.std.ILength;
import net.bodz.bas.ui.style.property.LineCapMode;
import net.bodz.bas.ui.style.property.LineJoinMode;

public interface IStrokeType
        extends Serializable {

    ILength getLineWidth();

    LineCapMode getLineCap();

    LineJoinMode getLineJoin();

    int[] getDashPattern();

}
