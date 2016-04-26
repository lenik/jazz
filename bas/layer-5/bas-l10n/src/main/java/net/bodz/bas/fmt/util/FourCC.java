package net.bodz.bas.fmt.util;

public class FourCC {

    public static int littleEndian(String fourCC) {
        int c0 = fourCC.charAt(0);
        int c1 = fourCC.charAt(1);
        int c2 = fourCC.charAt(2);
        int c3 = fourCC.charAt(3);
        int val = c3 << 24 | c2 << 16 | c1 << 8 | c0;
        return val;
    }

    public static String littleEndian(int val) {
        char c0 = (char) (val & 0xff);
        val >>= 8;
        char c1 = (char) (val & 0xff);
        val >>= 8;
        char c2 = (char) (val & 0xff);
        val >>= 8;
        char c3 = (char) (val & 0xff);
        StringBuilder fourCC = new StringBuilder(4);
        fourCC.append(c0);
        fourCC.append(c1);
        fourCC.append(c2);
        fourCC.append(c3);
        return fourCC.toString();
    }

}
