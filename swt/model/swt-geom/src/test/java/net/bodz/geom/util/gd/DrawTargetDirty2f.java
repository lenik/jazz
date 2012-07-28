package net.bodz.geom.util.gd;

import net.bodz.geom.drawtarget.AbstractDrawTarget2f;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.base.AbstractRectangle2f;
import net.bodz.geom.shape.base.IRectangle2f;

public class DrawTargetDirty2f
        extends AbstractDrawTarget2f {

    private DrawTarget2f delegatee;

    private AbstractRectangle2f dirtyRect;

    public DrawTargetDirty2f(DrawTarget2f delegatee) {
        this.delegatee = delegatee;
        clearDirty();
    }

    private void clearDirty() {
        dirtyRect = new IRectangle2f.LeftPositive(Float.MAX_VALUE, Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE);
    }

    @Override
    public void drawLine(float x0, float y0, float x1, float y1)
            throws DrawException {
        dirtyRect.addWithScale(x0, y0);
        dirtyRect.addWithScale(x1, y1);
        delegatee.drawLine(x0, y0, x1, y1);
    }

    @Override
    public void drawPixel(float x, float y)
            throws DrawException {
        dirtyRect.addWithScale(x, y);
        delegatee.drawPixel(x, y);
    }

    @Override
    public void fillTriangle(float x0, float y0, float x1, float y1, float x2, float y2)
            throws DrawException {
        dirtyRect.addWithScale(x0, y0);
        dirtyRect.addWithScale(x1, y1);
        dirtyRect.addWithScale(x2, y2);
        delegatee.fillTriangle(x0, y0, x1, y1, x2, y2);
    }

}
