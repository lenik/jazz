package net.bodz.jna.win32;

import net.bodz.jna.win32.GDI32.RECT;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * @test {@link User32Test}
 */
public interface User32 extends W32API {

    /** Defines the x- and y-coordinates of a point. */
    class POINT extends Structure {

        public int x, y;

        public POINT() {
        }

        public POINT(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    class POINTByValue extends Structure implements Structure.ByValue {

        public int x, y;

        public POINTByValue() {
        }

        public POINTByValue(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    /** Specifies the width and height of a rectangle. */
    class LPSIZE extends Structure {

        public int cx, cy;

        public LPSIZE() {
        }

        public LPSIZE(int w, int h) {
            this.cx = w;
            this.cy = h;
        }

    }

    interface WNDENUMPROC extends StdCallCallback {
        boolean callback(HWND hWnd, Pointer data);
    }

    HWND HWND_BROADCAST = new HWND() {
                            @Override
                            public void setPointer(Pointer p) {
                                throw new UnsupportedOperationException("Immutable reference");
                            }
                        };

    int EnumChildWindows(HWND hWndParent, WNDENUMPROC lpEnumFunc, Pointer data);

    int EnumWindows(WNDENUMPROC lpEnumFunc, Pointer data);

    /**
     * Retrieves the window handle to the active window attached to the calling
     * thread's message queue.
     */
    HWND GetActiveWindow();

    int GetDlgCtrlID(HWND hWnd);

    /**
     * Retrieves the cursor's position, in screen coordinates
     */
    boolean GetCursorPos(POINT point);

    HWND GetForegroundWindow();

    HWND GetParent(HWND hWnd);

    int GWL_EXSTYLE           = -20;
    int GWL_STYLE             = -16;
    int GWL_WNDPROC           = -4;
    int GWL_HINSTANCE         = -6;
    int GWL_ID                = -12;
    int GWL_USERDATA          = -21;

    int WS_OVERLAPPED         = 0x00000000;
    int WS_POPUP              = 0x80000000;
    int WS_CHILD              = 0x40000000;
    int WS_MINIMIZE           = 0x20000000;
    int WS_VISIBLE            = 0x10000000;
    int WS_DISABLED           = 0x08000000;
    int WS_CLIPSIBLINGS       = 0x04000000;
    int WS_CLIPCHILDREN       = 0x02000000;
    int WS_MAXIMIZE           = 0x01000000;
    int WS_CAPTION            = 0x00C00000;
    int WS_BORDER             = 0x00800000;
    int WS_DLGFRAME           = 0x00400000;
    int WS_VSCROLL            = 0x00200000;
    int WS_HSCROLL            = 0x00100000;
    int WS_SYSMENU            = 0x00080000;
    int WS_THICKFRAME         = 0x00040000;
    int WS_GROUP              = 0x00020000;
    int WS_TABSTOP            = 0x00010000;
    int WS_MINIMIZEBOX        = 0x00020000;
    int WS_MAXIMIZEBOX        = 0x00010000;
    int WS_TILED              = WS_OVERLAPPED;
    int WS_ICONIC             = WS_MINIMIZE;
    int WS_SIZEBOX            = WS_THICKFRAME;
    int WS_CHILDWINDOW        = (WS_CHILD);
    int WS_EX_DLGMODALFRAME   = 0x00000001;
    int WS_EX_NOPARENTNOTIFY  = 0x00000004;
    int WS_EX_TOPMOST         = 0x00000008;
    int WS_EX_ACCEPTFILES     = 0x00000010;
    int WS_EX_TRANSPARENT     = 0x00000020;
    int WS_EX_MDICHILD        = 0x00000040;
    int WS_EX_TOOLWINDOW      = 0x00000080;
    int WS_EX_WINDOWEDGE      = 0x00000100;
    int WS_EX_CLIENTEDGE      = 0x00000200;
    int WS_EX_CONTEXTHELP     = 0x00000400;
    int WS_EX_RIGHT           = 0x00001000;
    int WS_EX_LEFT            = 0x00000000;
    int WS_EX_RTLREADING      = 0x00002000;
    int WS_EX_LTRREADING      = 0x00000000;
    int WS_EX_LEFTSCROLLBAR   = 0x00004000;
    int WS_EX_RIGHTSCROLLBAR  = 0x00000000;
    int WS_EX_CONTROLPARENT   = 0x00010000;
    int WS_EX_STATICEDGE      = 0x00020000;
    int WS_EX_APPWINDOW       = 0x00040000;
    int WS_EX_LAYERED         = 0x00080000;
    int WS_EX_NOINHERITLAYOUT = 0x00100000;
    int WS_EX_LAYOUTRTL       = 0x00400000;
    int WS_EX_COMPOSITED      = 0x02000000;
    int WS_EX_NOACTIVATE      = 0x08000000;

    int GetWindowLong(HWND hWnd, int parameter);

    int SetWindowLong(HWND hWnd, int parameter, int newvalue);

    /**
     * The GetWindowRect function retrieves the dimensions of the bounding
     * rectangle of the specified window. The dimensions are given in screen
     * coordinates that are relative to the upper-left corner of the screen.
     */
    boolean GetWindowRect(HWND hWnd, RECT rect);

    /**
     * The GetWindowTextLength function retrieves the length, in characters, of
     * the specified window's title bar text (if the window has a title bar). If
     * the specified window is a control, the function retrieves the length of
     * the text within the control. However, GetWindowTextLength cannot retrieve
     * the length of the text of an edit control in another application.
     */
    int GetWindowTextLength(HWND hWnd);

    /**
     * The GetWindowText function copies the text of the specified window's
     * title bar (if it has one) into a buffer. If the specified window is a
     * control, the text of the control is copied. However, GetWindowText cannot
     * retrieve the text of a control in another application.
     * 
     * @param lpString
     *            Pointer to the buffer that will receive the text. If the
     *            string is as long or longer than the buffer, the string is
     *            truncated and terminated with a NULL character.
     * @param nMaxCount
     *            Specifies the maximum number of characters to copy to the
     *            buffer, including the NULL character. If the text exceeds this
     *            limit, it is truncated.
     * @return count of chars
     */
    int GetWindowText(HWND hWnd, byte[] buffer, int nMaxCount);

    boolean EnableWindow(HWND hWnd, boolean enabled);

    boolean IsWindowEnabled(HWND hWnd);

    boolean IsWindowVisible(HWND hWnd);

    boolean SetWindowText(HWND hWnd, String text);

    boolean ShowWindow(HWND hWnd, int nCmdShow);

    /**
     * @return selection.
     */
    int MessageBox(HWND hWnd, String text, String title, int style);

    /**
     * Determines which, if any, of the child windows belonging to a parent
     * window contains the specified point. The search is restricted to
     * immediate child windows. Grandchildren, and deeper descendant windows are
     * not searched.
     * <p>
     * To skip certain child windows, use the ChildWindowFromPointEx function.
     * 
     * @return The return value is a handle to the child window that contains
     *         the point, even if the child window is hidden or disabled. If the
     *         point lies outside the parent window, the return value is NULL.
     *         If the point is within the parent window but not within any child
     *         window, the return value is a handle to the parent window.
     */
    HWND ChildWindowFromPoint(HWND hWndParent, POINTByValue point);

    /** Does not skip any child windows */
    int CWP_ALL             = 0;
    /** Skips invisible child windows */
    int CWP_SKIPINVISIBLE   = 1;
    /** Skips disabled child windows */
    int CWP_SKIPDISABLED    = 2;
    /** Skips transparent child windows */
    int CWP_SKIPTRANSPARENT = 4;

    /**
     * Determines which, if any, of the child windows belonging to the specified
     * parent window contains the specified point. The function can ignore
     * invisible, disabled, and transparent child windows. The search is
     * restricted to immediate child windows. Grandchildren and deeper
     * descendants are not searched.
     */
    HWND ChildWindowFromPointEx(HWND hwndParent, POINTByValue pt, int uFlags);

    /**
     * RealChildWindowFromPoint treats HTTRANSPARENT areas of a standard control
     * differently from other areas of the control; it returns the child window
     * behind a transparent part of a control. In contrast, ChildWindowFromPoint
     * treats HTTRANSPARENT areas of a control the same as other areas. For
     * example, if the point is in a transparent area of a groupbox,
     * RealChildWindowFromPoint returns the child window behind a groupbox,
     * whereas ChildWindowFromPoint returns the groupbox. However, both APIs
     * return a static field, even though it, too, returns HTTRANSPARENT.
     */
    HWND RealChildWindowFromPoint(HWND hwndParent, POINTByValue ptParentClientCoords);

    /**
     * The return value is a handle to the window that contains the point. If no
     * window exists at the given point, the return value is NULL. If the point
     * is over a static text control, the return value is a handle to the window
     * under the static text control.
     */
    HWND WindowFromPoint(POINTByValue point);

    HWND RealWindowFromPoint(POINTByValue point);

    /** Vista and later */
    HWND WindowFromPhysicalPoint(POINTByValue point);

    boolean ScreenToClient(HWND hwnd, POINT point);

}
