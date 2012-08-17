package net.bodz.jna.win32;

import java.awt.Rectangle;

import net.bodz.jna.win32.User32.POINT;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public interface GDI32
        extends W32API {

    public class RECT
            extends Structure {
        public int left;
        public int top;
        public int right;
        public int bottom;

        public Rectangle toRectangle() {
            return new Rectangle(left, top, right - left, bottom - top);
        }

        @Override
        public String toString() {
            return "[(" + left + "," + top + ")(" + right + "," + bottom + ")]";
        }
    }

    int RDH_RECTANGLES = 1;

    public class RGNDATAHEADER
            extends Structure {
        public int dwSize = size();
        public int iType = RDH_RECTANGLES; // required
        public int nCount;
        public int nRgnSize;
        public RECT rcBound;
    }

    public class RGNDATA
            extends Structure {
        public RGNDATAHEADER rdh;
        public byte[] Buffer;

        public RGNDATA(int bufferSize) {
            Buffer = new byte[bufferSize];
            allocateMemory();
        }
    }

    public HRGN ExtCreateRegion(Pointer lpXform, int nCount, RGNDATA lpRgnData);

    int RGN_AND = 1;
    int RGN_OR = 2;
    int RGN_XOR = 3;
    int RGN_DIFF = 4;
    int RGN_COPY = 5;

    int ERROR = 0;
    int NULLREGION = 1;
    int SIMPLEREGION = 2;
    int COMPLEXREGION = 3;

    int CombineRgn(HRGN hrgnDest, HRGN hrgnSrc1, HRGN hrgnSrc2, int fnCombineMode);

    HRGN CreateRectRgn(int nLeftRect, int nTopRect, int nRightRect, int nBottomRect);

    HRGN CreateRoundRectRgn(int nLeftRect, int nTopRect, int nRightRect, int nBottomRect, int nWidthEllipse,
            int nHeightEllipse);

    int ALTERNATE = 1;
    int WINDING = 2;

    HRGN CreatePolyPolygonRgn(POINT[] lppt, int[] lpPolyCounts, int nCount, int fnPolyFillMode);

    boolean SetRectRgn(HRGN hrgn, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect);

    int SetPixel(HDC hDC, int x, int y, int crColor);

    HDC CreateCompatibleDC(HDC hDC);

    boolean DeleteDC(HDC hDC);

    HANDLE SelectObject(HDC hDC, HANDLE hGDIObj);

    boolean DeleteObject(HANDLE p);

}
