package net.bodz.swt.controls.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Scrollable;

/**
 * @test ControlsTest
 */
public class Controls {

    public static void center(Control control) {
        center(control, 0, 0);
    }

    public static void center(Control control, Control parent) {
        center(control, parent, 0, 0);
    }

    public static void center(Control control, double xOffset, double yOffset) {
        center(control, control.getParent(), xOffset, yOffset);
    }

    public static void center(Control control, Control parent, double xOffset, double yOffset) {
        Rectangle outer;
        if (parent == null || parent == control)
            outer = control.getDisplay().getBounds();
        else
            outer = parent.getBounds();
        Point size = control.getSize();
        int x = outer.x + (outer.width - size.x) / 2 + (int) (xOffset * outer.width);
        int y = outer.y + (outer.height - size.y) / 2 + (int) (yOffset * outer.height);
        control.setLocation(x, y);
    }

    static final Point defaultMinSize = new Point(1, 1);
    static final Point defaultMaxSize = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public static void resizeToPreferredSize(Control control) {
        resizeToPreferredSize(control, defaultMinSize, defaultMaxSize);
    }

    public static void resizeToPreferredSize(Control control, Point minSize, Point maxSize) {
        Point trimSize = control.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        if (minSize != null) {
            trimSize.x = Math.max(trimSize.x, minSize.x);
            trimSize.y = Math.max(trimSize.y, minSize.y);
        }
        if (maxSize != null) {
            trimSize.x = Math.min(trimSize.x, maxSize.x);
            trimSize.y = Math.min(trimSize.y, maxSize.y);
        }
        control.setSize(trimSize);
    }

    /**
     * @return <code>true</code> if size changed.
     */
    public static boolean resizeToFit(Scrollable scrollable, Point clientAreaSize, Point minSize,
            Point maxSize) {
        if (minSize == null)
            minSize = defaultMinSize;
        if (maxSize == null)
            maxSize = defaultMaxSize;
        Point size = scrollable.getSize();
        Rectangle area = scrollable.getClientArea();
        int padWidth = size.x - area.width;
        int padHeight = size.y - area.height;
        assert padWidth >= 0;
        assert padHeight >= 0;
        if (padWidth < 0)
            padWidth = 0;
        if (padHeight < 0)
            padHeight = 0;
        int width = Math.max(minSize.x, Math.min(clientAreaSize.x, maxSize.x));
        int height = Math.max(minSize.y, Math.min(clientAreaSize.y, maxSize.y));
        if (width == area.width && height == area.height)
            return false;
        size.x = width + padWidth;
        size.y = height + padHeight;
        scrollable.setSize(size);
        return true;
    }

    public static void setFontName(Control control, String fontName) {
        Font font = control.getFont();
        FontData[] fontData = font.getFontData();
        for (FontData fd : fontData)
            fd.setName(fontName);
        font = new Font(control.getDisplay(), fontData);
        control.setFont(font);
    }

    public static void setFontHeight(Control control, int fontHeight) {
        Font font = control.getFont();
        FontData[] fontData = font.getFontData();
        for (FontData fd : fontData)
            fd.setHeight(fontHeight);
        font = new Font(control.getDisplay(), fontData);
        control.setFont(font);
    }

    public static void setFontStyle(Control control, int style) {
        setFontStyle(control, style, -1);
    }

    public static void setFontStyle(Control control, int style, int styleMask) {
        style &= styleMask;
        Font font = control.getFont();
        FontData[] fontData = font.getFontData();
        for (FontData fd : fontData) {
            int oldStyle = fd.getStyle();
            int newStyle = (oldStyle & ~styleMask) | style;
            fd.setStyle(newStyle);
        }
        font = new Font(control.getDisplay(), fontData);
        control.setFont(font);
    }

    public static void setFontLocale(Control control, String locale) {
        Font font = control.getFont();
        FontData[] fontData = font.getFontData();
        for (FontData fd : fontData)
            fd.setLocale(locale);
        font = new Font(control.getDisplay(), fontData);
        control.setFont(font);
    }

}
