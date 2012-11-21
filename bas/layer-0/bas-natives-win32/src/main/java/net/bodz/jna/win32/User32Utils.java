package net.bodz.jna.win32;

import net.bodz.jna.win32.User32.POINT;
import net.bodz.jna.win32.User32.POINTByValue;
import net.bodz.jna.win32.W32API.HWND;

public class User32Utils
        implements IWin32 {

    public static HWND GetWindowAt(HWND parent, POINTByValue point) {
        if (parent == null) {
            parent = user32.WindowFromPoint(point);
            if (parent == null)
                return null;
            int style = user32.GetWindowLong(parent, User32.GWL_STYLE);
            if ((style & User32.WS_CHILDWINDOW) != 0) {
                // maybe static child window.
                HWND grandpa = user32.GetParent(parent);
                if (grandpa != null)
                    parent = grandpa;
            }
        }
        POINT client = new POINT(point.x, point.y);
        user32.ScreenToClient(parent, client);
        POINTByValue _client = new POINTByValue(client.x, client.y);

        // String prefix = "    ";
        while (true) {
            // System.out.println(prefix + "->" + parent);
            // prefix += "    ";
            HWND child = user32.RealChildWindowFromPoint(parent, _client);
            // HWND child = _CWP_Minsize(parent, point.x, point.y);
            if (child == null) {
                // System.out.println(prefix + "-> null");
                return parent;
            }
            if (child.equals(parent)) {
                // System.out.println(prefix + "-> term");
                return parent;
            }
            parent = child;
        }
    }

}
