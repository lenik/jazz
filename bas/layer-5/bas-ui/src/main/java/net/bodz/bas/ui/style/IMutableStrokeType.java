package net.bodz.bas.ui.style;

import net.bodz.bas.i18n.unit.std.Length;
import net.bodz.bas.ui.style.property.LineCapMode;
import net.bodz.bas.ui.style.property.LineJoinMode;

public interface IMutableStrokeType
        extends IStrokeType {

    void setWidth(Length width);

    void setLineCap(LineCapMode lineCap);

    void setLineJoin(LineJoinMode lineJoin);

    void setDashPattern(int[] dashPattern);

}
