package net.bodz.bas.gui.spec0;

import java.io.Serializable;

import net.bodz.bas.gui.spec0.property.LineCapMode;
import net.bodz.bas.gui.spec0.property.LineJoinMode;
import net.bodz.bas.i18n.unit.Measure;

public interface IStrokeType
        extends Serializable {

    Measure getWidth();

    void setWidth(Measure width);

    LineCapMode getLineCap();

    void setLineCap(LineCapMode lineCap);

    LineJoinMode getLineJoin();

    void setLineJoin(LineJoinMode lineJoin);

    int[] getDashPattern();

    void setDashPattern(int[] dashPattern);

}
