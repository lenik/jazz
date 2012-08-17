package net.bodz.jna.win32;

import com.sun.jna.Native;

public interface IWin32 {

    public static GDI32 gdi32 = (GDI32) Native.loadLibrary("gdi32", GDI32.class, Win32Config._options);
    public static Kernel32 kernel32 = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class, Win32Config._options);
    public static User32 user32 = (User32) Native.loadLibrary("user32", User32.class, Win32Config._options);

}
