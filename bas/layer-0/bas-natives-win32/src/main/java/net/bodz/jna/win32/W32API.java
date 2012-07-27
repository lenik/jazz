package net.bodz.jna.win32;

import static net.bodz.jna.win32.Win32.user32;

import java.awt.Component;

import com.sun.jna.FromNativeContext;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.ptr.ByReference;
import com.sun.jna.win32.StdCallLibrary;

public interface W32API
        extends StdCallLibrary {

    class HANDLE
            extends PointerType {

        public HANDLE() {
        }

        public HANDLE(Pointer pointer) {
            setPointer(pointer);
        }

        public HANDLE(long peer) {
            setPointer(Pointer.createConstant(peer));
        }

        @Override
        public Object fromNative(Object nativeValue, FromNativeContext context) {
            Object o = super.fromNative(nativeValue, context);
            if (INVALID_HANDLE_VALUE.equals(o))
                return INVALID_HANDLE_VALUE;
            return o;
        }

    }

    HANDLE INVALID_HANDLE_VALUE = new HANDLE() {
        @Override
        public void setPointer(Pointer p) {
            throw new UnsupportedOperationException("Immutable reference");
        }
    };

    class HANDLEByReference
            extends ByReference {

        public HANDLEByReference() {
            this(null);
        }

        public HANDLEByReference(HANDLE h) {
            super(Pointer.SIZE);
            setValue(h);
        }

        public void setValue(HANDLE h) {
            getPointer().setPointer(0L, (h != null) ? h.getPointer() : null);
        }

        public HANDLE getValue() {
            Pointer p = getPointer().getPointer(0L);
            if (p == null)
                return null;
            if (INVALID_HANDLE_VALUE.getPointer().equals(p))
                return INVALID_HANDLE_VALUE;
            HANDLE h = new HANDLE();
            h.setPointer(p);
            return h;
        }

    }

    class HBITMAP
            extends HANDLE {
    }

    class HDC
            extends HANDLE {
    }

    class HICON
            extends HANDLE {
    }

    class HINSTANCE
            extends HANDLE {
    }

    class HMODULE
            extends HINSTANCE {
    }

    class HRGN
            extends HANDLE {
    }

    class HWND
            extends HANDLE {

        public HWND() {
            super();
        }

        public HWND(Pointer pointer) {
            super(pointer);
        }

        public HWND(long peer) {
            super(peer);
        }

        public HWND(Component c) {
            this(Native.getComponentPointer(c));
        }

        public String getText() {
            String text = "(n/a)";
            int len = user32.GetWindowTextLength(this);
            if (len != 0) {
                int cb = Win32.charSize(len + 1);
                byte[] buf = new byte[cb];
                len = user32.GetWindowText(this, buf, len + 1);
                cb = Win32.charSize(len);
                text = Win32.toString(buf, 0, cb);
            }
            return text;
        }

        @Override
        public String toString() {
            return super.toString() + ": " + getText();
        }

    }

}
