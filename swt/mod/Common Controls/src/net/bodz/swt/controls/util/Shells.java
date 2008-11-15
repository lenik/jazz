package net.bodz.swt.controls.util;

import net.bodz.swt.os_ann.Win32;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Shell;

public class Shells {

    public static void center(Shell shell) {
        Rectangle bounds = shell.getDisplay().getBounds();
        Point size = shell.getSize();
        int x = bounds.x + (bounds.width - size.x) / 2;
        int y = bounds.y + (bounds.height - size.y) / 2;
        shell.setLocation(x, y);
    }

    public static final int TOPMOST   = OS.HWND_TOPMOST;
    public static final int NOTOPMOST = OS.HWND_NOTOPMOST;
    public static final int TOP       = OS.HWND_TOP;
    public static final int BOTTOM    = OS.HWND_BOTTOM;

    @Win32
    public static void setTopmost(Shell shell, int position) {
        OS.SetWindowPos(shell.handle, position, 0, 0, 0, 0, OS.SWP_NOMOVE
                | OS.SWP_NOSIZE);
    }

    @Win32
    public static void setTopmost(Shell shell) {
        setTopmost(shell, TOPMOST);
    }

}
