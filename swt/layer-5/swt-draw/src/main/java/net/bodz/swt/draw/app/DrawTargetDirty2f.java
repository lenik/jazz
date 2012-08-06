package net.bodz.swt.draw.app;

import net.bodz.bas.geom_f.base.AbstractRectangle2d;
import net.bodz.bas.geom_f.base.IRectangle2d;
import net.bodz.swt.draw.dev.AbstractDrawTarget2f;
import net.bodz.swt.draw.dev.DrawException;
import net.bodz.swt.draw.dev.DrawTarget2f;

public class DrawTargetDirty2f
        extends AbstractDrawTarget2f {

    private DrawTarget2f delegatee;

    private AbstractRectangle2d dirtyRect;

    public DrawTargetDirty2f(DrawTarget2f delegatee) {
        this.delegatee = delegatee;
        clearDirty();
    }

    private void clearDirty() {
        dirtyRect = new IRectangle2d.LeftPositive(Float.MAX_VALUE, Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE);
    }

    @Override
    public void drawLine(float x0, float y0, float x1, float y1)
            throws DrawException {
        dirtyRect.include(x0, y0);
        dirtyRect.include(x1, y1);
        delegatee.drawLine(x0, y0, x1, y1);
    }

    @Override
    public void drawPixel(float x, float y)
            throws DrawException {
        dirtyRect.include(x, y);
        delegatee.drawPixel(x, y);
    }

    @Override
    public void fillTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException {
        dirtyRect.include(x0, y0);
        dirtyRect.include(x1, y1);
        dirtyRect.include(x2, y2);
        delegatee.fillTriangle(x0, y0, x1, y1, x2, y2);
    }

}
