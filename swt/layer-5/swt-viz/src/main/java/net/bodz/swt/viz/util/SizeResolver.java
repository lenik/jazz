package net.bodz.swt.viz.util;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import net.bodz.bas.gui.css3.ICss3Length;
import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;

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
        ICss3Length width = styleDecl.getWidth();

        if (width == null) {
            IGUIElementStyleDeclaration parentStyle = styleDecl.getParent();
            if (parentStyle == null)
                return null;
            else
                return resolveWidth(parentStyle);
        }

        assert width != null;

        switch (width.getType()) {
        case ICss3Length.NONE:
        default:
            // 'none' should be only on max-wdith, max-height.
            return null;

        case ICss3Length.AUTO:
            return null;

        case ICss3Length.LENGTH:
            return width.pixels(xppi);

        case ICss3Length.PERCENTAGE:
            return (int) (parentWidth * width.getValue()); // truncate, no round.
        }
    }

    public Integer resolveHeight(IGUIElementStyleDeclaration styleDecl) {
        ICss3Length height = styleDecl.getHeight();

        if (height == null) {
            IGUIElementStyleDeclaration parentStyle = styleDecl.getParent();
            if (parentStyle == null)
                return null;
            else
                return resolveHeight(parentStyle);
        }

        assert height != null;

        switch (height.getType()) {
        case ICss3Length.NONE:
        default:
            // 'none' should be only on max-wdith, max-height.
            return null;

        case ICss3Length.AUTO:
            return null;

        case ICss3Length.LENGTH:
            return height.pixels(yppi);

        case ICss3Length.PERCENTAGE:
            return (int) (parentHeight * height.getValue()); // truncate, no round.
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
