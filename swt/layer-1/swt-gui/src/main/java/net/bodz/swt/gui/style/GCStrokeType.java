package net.bodz.swt.gui.style;

import org.eclipse.swt.graphics.GC;

import net.bodz.bas.meta.decl.NotSerializable;

@NotSerializable
public class GCStrokeType
        extends AbstractMutableSwtStrokeType {

    private static final long serialVersionUID = 1L;

    private final GC gc;

    public GCStrokeType(GC gc) {
        super(gc.getDevice().getDPI().x);
        this.gc = gc;
    }

    @Override
    public int getSwtLineCap() {
        return gc.getLineCap();
    }

    @Override
    public void setSwtLineCap(int cap) {
        gc.setLineCap(cap);
    }

    @Override
    public int[] getSwtLineDash() {
        return gc.getLineDash();
    }

    @Override
    public void setSwtLineDash(int[] dashes) {
        gc.setLineDash(dashes);
    }

    @Override
    public int getSwtLineJoin() {
        return gc.getLineJoin();
    }

    @Override
    public void setSwtLineJoin(int join) {
        gc.setLineJoin(join);
    }

    @Override
    public int getSwtLineStyle() {
        return gc.getLineStyle();
    }

    @Override
    public void setSwtLineStyle(int style) {
        gc.setLineStyle(style);
    }

    @Override
    public int getSwtLineWidth() {
        return gc.getLineWidth();
    }

    @Override
    public void setSwtLineWidth(int width) {
        gc.setLineWidth(width);
    }

}
