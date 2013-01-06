package net.bodz.swt.gui.style;

import org.eclipse.swt.graphics.GC;

public class GCStrokeType
        implements ISwtStrokeType {

    private final GC gc;

    public GCStrokeType(GC gc) {
        if (gc == null)
            throw new NullPointerException("gc");
        this.gc = gc;
    }

    @Override
    public int getCap() {
        return gc.getLineCap();
    }

    @Override
    public void setCap(int cap) {
        gc.setLineCap(cap);
    }

    @Override
    public int[] getDash() {
        return gc.getLineDash();
    }

    @Override
    public void setDash(int[] dashes) {
        gc.setLineDash(dashes);
    }

    @Override
    public int getJoin() {
        return gc.getLineJoin();
    }

    @Override
    public void setJoin(int join) {
        gc.setLineJoin(join);
    }

    @Override
    public int getStyle() {
        return gc.getLineStyle();
    }

    @Override
    public void setStyle(int style) {
        gc.setLineStyle(style);
    }

    @Override
    public int getWidth() {
        return gc.getLineWidth();
    }

    @Override
    public void setWidth(int width) {
        gc.setLineWidth(width);
    }

}
