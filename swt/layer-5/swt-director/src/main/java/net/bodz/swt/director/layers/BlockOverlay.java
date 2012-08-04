package net.bodz.swt.director.layers;

import org.eclipse.swt.graphics.Rectangle;

public abstract class BlockOverlay
        extends _Overlay {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public BlockOverlay(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public BlockOverlay(Rectangle rect) {
        this(rect.x, rect.y, rect.width, rect.height);
    }

    @Override
    public Rectangle getDefinedRegion() {
        return new Rectangle(x, y, width, height);
    }

}
