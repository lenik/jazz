package net.bodz.swt.draw.core.model;

import net.bodz.bas.geom.spec0_f.DecoratedPrimitive2d;
import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.gui.draw_f.dc.GraphicsOperationException;
import net.bodz.bas.gui.draw_f.dc.IDrawContext2d;

public class AbstractComponent2d
        extends DecoratedPrimitive2d {

    private static final long serialVersionUID = 1L;

    SWTStyle style;

    public AbstractComponent2d(IPrimitive2d _orig) {
        super(_orig);
    }

    public void draw(IDrawContext2d target)
            throws GraphicsOperationException {
        if (style != null) {
            if (style.color != null)
                target.setColor(style.color);

            if (style.fillColor != null)
                target.setFillColor(style.fillColor);

            if (style.storke != null)
                target.setStroke(style.storke);

            if (style.pattern != null)
                target.setPattern(style.pattern);

            if (style.fillPattern != null)
                target.setFillPattern(style.fillPattern);

            if (style.font != null)
                target.setFont(style.font);
        }
        getWrapped().draw(target);
    }

}
