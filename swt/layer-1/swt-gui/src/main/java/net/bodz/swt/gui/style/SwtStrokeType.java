package net.bodz.swt.gui.style;

import java.io.Serializable;

public class SwtStrokeType
        implements ISwtStrokeType, Serializable {

    private static final long serialVersionUID = 1L;

    public int cap;
    public int[] dash;
    public int join;
    public int style;
    public int width;

    @Override
    public int getSwtLineCap() {
        return cap;
    }

    @Override
    public void setSwtLineCap(int cap) {
        this.cap = cap;
    }

    @Override
    public int[] getSwtLineDash() {
        return dash;
    }

    @Override
    public void setSwtLineDash(int[] dash) {
        this.dash = dash;
    }

    @Override
    public int getSwtLineJoin() {
        return join;
    }

    @Override
    public void setSwtLineJoin(int join) {
        this.join = join;
    }

    @Override
    public int getSwtLineStyle() {
        return style;
    }

    @Override
    public void setSwtLineStyle(int style) {
        this.style = style;
    }

    @Override
    public int getSwtLineWidth() {
        return width;
    }

    @Override
    public void setSwtLineWidth(int width) {
        this.width = width;
    }

}
