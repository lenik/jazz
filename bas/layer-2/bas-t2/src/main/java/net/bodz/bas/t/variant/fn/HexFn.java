package net.bodz.bas.t.variant.fn;

import java.io.IOException;

public class HexFn {

    public static String encode(String name, byte value)
            throws IOException {
        String hex = Integer.toHexString(value);
        if (hex.length() > 2)
            hex = hex.substring(hex.length() - 2);
        return "0x" + hex;
    }

    public static String encode(String name, short value)
            throws IOException {
        String hex = Integer.toHexString(value);
        if (hex.length() > 4)
            hex = hex.substring(hex.length() - 4);
        return "0x" + hex;
    }

    public static String encode(String name, int value)
            throws IOException {
        return "0x" + Integer.toHexString(value);
    }

    public static String encode(String name, long value)
            throws IOException {
        return "0x" + Long.toHexString(value);
    }

}
