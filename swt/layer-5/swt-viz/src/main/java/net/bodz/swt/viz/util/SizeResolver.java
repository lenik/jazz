package net.bodz.swt.viz.util;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import net.bodz.bas.gui.css3.property.OffsetType;
import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public class SizeResolver {

    private int parentWidth;
    private int parentHeight;
    private int xppi;
    private int yppi;

    public SizeResolver(Point parentSize, Point ppi) {
        parentWidth = parentSize.x;
        parentHeight = parentSize.y;
        xppi = ppi.x;
        yppi = ppi.y;
    }

    public SizeResolver(int parentWidth, int parentHeight, int xppi, int yppi) {
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        this.xppi = xppi;
        this.yppi = yppi;
    }

    public Integer resolveWidth(IGUIElementStyleDeclaration styleDecl) {
        OffsetType offsetType = styleDecl.getWidthType();
        LengthMeasure length = styleDecl.getWidth();

        if (offsetType == null) {
            IGUIElementStyleDeclaration parentStyle = styleDecl.getParent();
            if (parentStyle == null)
                return null;
            else
                return resolveWidth(parentStyle);
        }

        assert length != null;

        switch (offsetType) {
        case none:
        default:
            // 'none' should be only on max-wdith, max-height.
            return null;

        case auto:
            return null;

        case length:
            return length.pixels(xppi);

        case percentage:
            return (int) (parentWidth * length.getValue()); // truncate, no round.
        }
    }

    public Integer resolveHeight(IGUIElementStyleDeclaration styleDecl) {
        OffsetType offsetType = styleDecl.getHeightType();
        LengthMeasure length = styleDecl.getHeight();

        if (offsetType == null) {
            IGUIElementStyleDeclaration parentStyle = styleDecl.getParent();
            if (parentStyle == null)
                return null;
            else
                return resolveHeight(parentStyle);
        }

        assert length != null;

        switch (offsetType) {
        case none:
        default:
            // 'none' should be only on max-wdith, max-height.
            return null;

        case auto:
            return null;

        case length:
            return length.pixels(yppi);

        case percentage:
            return (int) (parentHeight * length.getValue()); // truncate, no round.
        }
    }

    public Point resolveSize(IGUIElementStyleDeclaration styleDecl) {
        Integer width = resolveWidth(styleDecl);
        Integer height = resolveHeight(styleDecl);
        if (width == null || height == null)
            return null;
        else
            return new Point(width, height);
    }

    public static Point resolveSize(IGUIElementStyleDeclaration styleDecl, Control controlReference) {
        Display device = controlReference.getDisplay();
        Point ppi = device.getDPI();

        Composite parent = controlReference.getParent();
        SizeResolver resolver;
        if (parent != null) {
            resolver = new SizeResolver(parent.getSize(), ppi);
        } else {
            Rectangle clientArea = controlReference.getShell().getClientArea();
            resolver = new SizeResolver(clientArea.width, clientArea.height, ppi.x, ppi.y);
        }

        return resolver.resolveSize(styleDecl);
    }

}
