package net.bodz.bas.ui.layout;

import java.awt.Point;

public class SizeInfo {

    public int width;
    public int height;

    public int preferredWidth;
    public int preferredHeight;

    public int minWidth;
    public int minHeight;

    public int maxWidth = Integer.MAX_VALUE;
    public int maxHeight = Integer.MAX_VALUE;

    public boolean fillWidth;
    public boolean fillHeight;

    public static SizeInfo size(int width, int height) {
        SizeInfo size = new SizeInfo();
        size.width = width;
        size.height = height;
        return size;
    }

    public static SizeInfo preferred(int width, int height) {
        SizeInfo size = new SizeInfo();
        size.preferredWidth = width;
        size.preferredHeight = height;
        return size;
    }

    public Point getSize(BoundType boundType) {
        int w, h;
        switch (boundType) {
        case SIZE:
            w = width;
            h = height;
            break;
        case PREFERRED:
            w = preferredWidth;
            h = preferredHeight;
            break;
        case MIN:
            w = minWidth;
            h = minHeight;
            break;
        case MAX:
            w = maxWidth;
            h = maxHeight;
            break;
        default:
            w = h = 0;
        }
        return new Point(w, h);
    }

    public void add(int dw, int dh) {
        width += dw;
        preferredWidth += dw;
        minWidth += dw;
        maxWidth += dw;

        height += dh;
        preferredHeight += dh;
        minHeight += dh;
        maxHeight += dh;
    }

    public void add(SizeInfo o) {
        addWidth(o);
        addHeight(o);
    }

    public void addWidth(SizeInfo o) {
        width += o.width;
        preferredWidth += o.preferredWidth;
        minWidth += o.minWidth;
        maxWidth += o.maxWidth;
    }

    public void addHeight(SizeInfo o) {
        height += o.height;
        preferredHeight += o.preferredHeight;
        minHeight += o.minHeight;
        maxHeight += o.maxHeight;
    }

    public void max(SizeInfo o) {
        maxWidth(o);
        maxHeight(o);
    }

    public void maxWidth(SizeInfo o) {
        width = Math.max(width, o.width);
        preferredWidth = Math.max(preferredWidth, o.preferredWidth);
        minWidth = Math.max(minWidth, o.minWidth);
        maxWidth = Math.max(maxWidth, o.maxWidth);
    }

    public void maxHeight(SizeInfo o) {
        height = Math.max(height, o.height);
        preferredHeight = Math.max(preferredHeight, o.preferredHeight);
        minHeight = Math.max(minHeight, o.minHeight);
        maxHeight = Math.max(maxHeight, o.maxHeight);
    }

    public void min(SizeInfo o) {
        minWidth(o);
        minHeight(o);
    }

    public void minWidth(SizeInfo o) {
        width = Math.min(width, o.width);
        preferredWidth = Math.min(preferredWidth, o.preferredWidth);
        minWidth = Math.min(minWidth, o.minWidth);
        maxWidth = Math.min(maxWidth, o.maxWidth);
    }

    public void minHeight(SizeInfo o) {
        height = Math.min(height, o.height);
        preferredHeight = Math.min(preferredHeight, o.preferredHeight);
        minHeight = Math.min(minHeight, o.minHeight);
        maxHeight = Math.min(maxHeight, o.maxHeight);
    }

}
