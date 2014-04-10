package net.bodz.bas.gui.style;

import net.bodz.bas.gui.style.property.LineCapMode;
import net.bodz.bas.gui.style.property.LineJoinMode;
import net.bodz.bas.i18n.unit.std.Length;

public interface IMutableStrokeType
        extends IStrokeType {

    void setWidth(Length width);

    void setLineCap(LineCapMode lineCap);

    void setLineJoin(LineJoinMode lineJoin);

    void setDashPattern(int[] dashPattern);

}
