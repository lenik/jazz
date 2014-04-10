package net.bodz.bas.gui.style;

import java.io.Serializable;

import net.bodz.bas.gui.style.property.LineCapMode;
import net.bodz.bas.gui.style.property.LineJoinMode;
import net.bodz.bas.i18n.unit.std.ILength;

public interface IStrokeType
        extends Serializable {

    ILength getLineWidth();

    LineCapMode getLineCap();

    LineJoinMode getLineJoin();

    int[] getDashPattern();

}
