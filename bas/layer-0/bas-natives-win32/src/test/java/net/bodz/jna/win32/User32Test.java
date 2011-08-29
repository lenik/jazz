package net.bodz.jna.win32;

import static net.bodz.jna.win32.Win32.user32;
import net.bodz.jna.win32.GDI32.RECT;
import net.bodz.jna.win32.User32.POINT;
import net.bodz.jna.win32.User32.WNDENUMPROC;
import net.bodz.jna.win32.W32API.HWND;

import org.junit.Assert;
import org.junit.Test;

import com.sun.jna.Pointer;

public class User32Test
        extends Assert {

    @Test
    public void testGetCursorPos()
            throws Exception {
        POINT cursor = new POINT();
        for (int i = 0; i < 10; i++) {
            if (user32.GetCursorPos(cursor))
                output("Got cursor position: " + cursor);
            else
                output("Can't get cursor position.");
            Thread.sleep(100);
        }
    }

    @Test
    public void testGetWindow()
            throws Exception {
        HWND active = user32.GetActiveWindow();
        HWND fg = user32.GetForegroundWindow();

        output("Active window: " + active);
        output("Foreground window: " + fg);
    }

    @Test
    public void testEnumWindows() {
        user32.EnumWindows(new WNDENUMPROC() {

            @Override
            public boolean callback(HWND hWnd, Pointer data) {
                if (hWnd == null)
                    output("null hwnd.");
                else {
                    output("window: %s" + hWnd);
                    user32.EnumChildWindows(hWnd, new WNDENUMPROC() {

                        @Override
                        public boolean callback(HWND hWnd, Pointer data) {
                            if (hWnd == null)
                                output("    null child");
                            else {
                                RECT rect = new RECT();
                                user32.GetWindowRect(hWnd, rect);
                                output("    child: " + hWnd + " rect=" + rect);
                            }
                            return true;
                        }

                    }, null);
                }
                return true;
            }

        }, null);
    }

    void output(String s) {
        // System.out.println(s);
    }

}
