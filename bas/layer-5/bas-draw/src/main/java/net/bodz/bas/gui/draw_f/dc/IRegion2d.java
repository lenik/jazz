package net.bodz.bas.gui.draw_f.dc;

import net.bodz.bas.geom.spec1_f.Polygon2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.t.object.IDisposable;

public interface IRegion2d
        extends IDisposable {

    void add(Rectangle2d rect);

    void remove(Rectangle2d rect);

    void add(Polygon2d polygon);

    void remove(Polygon2d polygon);

}
