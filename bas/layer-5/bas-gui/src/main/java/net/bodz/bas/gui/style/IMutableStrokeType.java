package net.bodz.bas.gui.style;

import net.bodz.bas.gui.style.property.LineCapMode;
import net.bodz.bas.gui.style.property.LineJoinMode;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public interface IMutableStrokeType
        extends IStrokeType {

    void setWidth(LengthMeasure width);

    void setLineCap(LineCapMode lineCap);

    void setLineJoin(LineJoinMode lineJoin);

    void setDashPattern(int[] dashPattern);

}
