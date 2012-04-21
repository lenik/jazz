package net.bodz.swt.presents.layers;

import java.io.Serializable;

import org.eclipse.swt.graphics.Rectangle;

public class Snapshot
        implements Serializable {

    private static final long serialVersionUID = -5273269296005708858L;

    protected Rectangle bounds;
    protected Object data;

    public Snapshot(Rectangle bounds, Object data) {
        this.bounds = bounds;
        this.data = data;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
