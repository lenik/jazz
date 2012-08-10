package net.bodz.swt.draw.app;

import net.bodz.bas.geom_f.api.DecoratedShape2d;
import net.bodz.bas.geom_f.api.IShape2d;
import net.bodz.bas.gui.dev.GraphicsOperationException;
import net.bodz.bas.gui.dev.IDrawContext2d;

public class SWTShape2d
        extends DecoratedShape2d {

    private static final long serialVersionUID = 1L;

    SWTStyle style;

    public SWTShape2d(IShape2d _orig) {
        super(_orig);
    }

    public void draw(IDrawContext2d target)
            throws GraphicsOperationException {
        if (style != null) {
            if (style.storke != null)
                target.setStroke(style.storke);
            if (style.color != null)
                target.setColor(style.color);
            if (style.fillColor != null)
                target.setFillColor(style.fillColor);
            if (style.fontColor != null)
                target.setFontColor(style.fontColor);
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
