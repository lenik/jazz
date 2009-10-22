package net.bodz.jna.win32;

import static net.bodz.jna.win32.Win32.user32;
import junit.framework.TestCase;
import net.bodz.jna.win32.GDI32.RECT;
import net.bodz.jna.win32.User32.POINT;
import net.bodz.jna.win32.User32.WNDENUMPROC;
import net.bodz.jna.win32.W32API.HWND;

import org.junit.Test;

import com.sun.jna.Pointer;

public class User32Test extends TestCase {

    @Test
    public void testGetCursorPos() throws Exception {
        POINT cursor = new POINT();
        for (int i = 0; i < 10; i++) {
            if (user32.GetCursorPos(cursor))
                System.out.println("Got cursor position: " + cursor);
            else
                System.out.println("Can't get cursor position.");
            Thread.sleep(100);
        }
    }

    @Test
    public void testGetWindow() throws Exception {
        HWND active = user32.GetActiveWindow();
        HWND fg = user32.GetForegroundWindow();

        System.out.println("Active window: " + active);
        System.out.println("Foreground window: " + fg);
    }

    @Test
    public void testEnumWindows() {
        user32.EnumWindows(new WNDENUMPROC() {

            @Override
            public boolean callback(HWND hWnd, Pointer data) {
                if (hWnd == null)
                    System.out.println("null hwnd.");
                else {
                    System.out.printf("window: %s\n", hWnd);
                    user32.EnumChildWindows(hWnd, new WNDENUMPROC() {

                        @Override
                        public boolean callback(HWND hWnd, Pointer data) {
                            if (hWnd == null)
                                System.out.println("    null child");
                            else {
                                RECT rect = new RECT();
                                user32.GetWindowRect(hWnd, rect);
                                System.out.printf("    child: %s, rect=%s\n", hWnd, rect);
                            }
                            return true;
                        }

                    }, null);
                }
                return true;
            }

        }, null);
    }

}
