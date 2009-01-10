package net.bodz.swt.controls.util;

 import net.bodz.swt.os_ann.Win32;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class Shells {

    public static void center(Shell shell) {
        center(shell, 0, 0);
    }

    public static void center(Shell shell, double xOffset, double yOffset) {
        Composite parent = shell.getParent();
        Rectangle outer;
        if (parent == null || parent == shell)
            outer = shell.getDisplay().getBounds();
        else
            outer = parent.getBounds();
        Point size = shell.getSize();
        int x = outer.x + (outer.width - size.x) / 2
                + (int) (xOffset * outer.width);
        int y = outer.y + (outer.height - size.y) / 2
                + (int) (yOffset * outer.height);
        shell.setLocation(x, y);
    }

    /**
     * @return <code>true</code> if size changed.
     */
    public static boolean fitToClientAreaSize(Shell shell,
            Point clientAreaSize, Point minSize, Point maxSize) {
        Point shellSize = shell.getSize();
        Rectangle area = shell.getClientArea();
        int padWidth = shellSize.x - area.width;
        int padHeight = shellSize.y - area.height;
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
        shellSize.x = width + padWidth;
        shellSize.y = height + padHeight;
        shell.setSize(shellSize);
        return true;
    }

//    public static final int TOPMOST   = OS.HWND_TOPMOST;
//    public static final int NOTOPMOST = OS.HWND_NOTOPMOST;
//    public static final int TOP       = OS.HWND_TOP;
//    public static final int BOTTOM    = OS.HWND_BOTTOM;

    @Win32
    public static void setTopmost(Shell shell, int position) {
//        OS.SetWindowPos(shell.handle, position, 0, 0, 0, 0, OS.SWP_NOMOVE
//                | OS.SWP_NOSIZE);
    }

    @Win32
    public static void setTopmost(Shell shell) {
//        setTopmost(shell, TOPMOST);
    }

}
