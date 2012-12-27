package net.bodz.swt.gui.style;

import net.bodz.bas.gui.style.IStrokeType;

public class SwtStrokeType
        implements IStrokeType {

    private static final long serialVersionUID = 1L;

    public int cap;
    public int[] dash;
    public int join;
    public int style;
    public int width;

    @Override
    public int getCap() {
        return cap;
    }

    @Override
    public void setCap(int cap) {
        this.cap = cap;
    }

    @Override
    public int[] getDash() {
        return dash;
    }

    @Override
    public void setDash(int[] dash) {
        this.dash = dash;
    }

    @Override
    public int getJoin() {
        return join;
    }

    @Override
    public void setJoin(int join) {
        this.join = join;
    }

    @Override
    public int getStyle() {
        return style;
    }

    @Override
    public void setStyle(int style) {
        this.style = style;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

}
