package net.bodz.jna.win32;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

public class Win32 {

    static final boolean ascii;
    static final Charset unicodeCharset;
    static final Map<String, Object> _options;
    static {
        ascii = java.lang.Boolean.getBoolean("w32.ascii");
        unicodeCharset = Charset.forName("utf-16le");
        _options = new HashMap<String, Object>();
        if (ascii) {
            _options.put(Library.OPTION_TYPE_MAPPER, W32APITypeMapper.ASCII);
            _options.put(Library.OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.ASCII);
        } else {
            _options.put(Library.OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
            _options.put(Library.OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
        }
    }

    public static int charSize(int charCount) {
        if (ascii)
            return charCount;
        else
            return charCount * Native.WCHAR_SIZE;
    }

    public static String toString(byte[] buf, int off, int len) {
        if (off != 0 || len != buf.length)
            buf = Arrays.copyOfRange(buf, off, len);
        return toString(buf);
    }

    public static String toString(byte[] buf) {
        if (ascii)
            return Native.toString(buf);
        return new String(buf, unicodeCharset);
    }

    public static GDI32 gdi32;
    public static Kernel32 kernel32;
    public static User32 user32;

    static {
        gdi32 = (GDI32) Native.loadLibrary("gdi32", GDI32.class, _options);
        kernel32 = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class, _options);
        user32 = (User32) Native.loadLibrary("user32", User32.class, _options);
    }

}
