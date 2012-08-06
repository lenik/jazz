package net.bodz.bas.gui.dev;

import net.bodz.bas.geom_f.base.Polygon2d;
import net.bodz.bas.geom_f.base.Rectangle2d;
import net.bodz.bas.model.IDisposable;

public interface IRegion
        extends IDisposable {

    void add(Rectangle2d rect);

    void add(Polygon2d polygon);

    void remove(Rectangle2d rect);

    void remove(Polygon2d polygon);

}
