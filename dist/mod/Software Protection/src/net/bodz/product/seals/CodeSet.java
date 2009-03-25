package net.bodz.product.seals;

public class CodeSet {

    public static final int size = 32;
    public static final int bits = 5;
    public static final int mask = size - 1;

    static final char[]     chars;
    static final int[]      ordinals;

    static {
        // removed: E O R W
        chars = "0123456789ABCDFGHIJKLMNPQSTUVXYZ".toCharArray();
        assert chars.length == size;
        ordinals = new int[256];
        // all unknown chars are mapped to 0.
        for (int i = 0; i < size; i++)
            ordinals[chars[i]] = i;
    }

    public static char chr(int ord) {
        return chars[ord];
    }

    public static int ord(char c) {
        return ordinals[c];
    }

    public static String encode(int n) {
        if (n == 0)
            return "R";
        StringBuffer buf = new StringBuffer(8);
        while (n != 0) {
            int r = n & mask;
            n >>= bits;
            buf.append(chars[r]);
        }
        return buf.reverse().toString();
    }

    public static String encode(long n) {
        if (n == 0)
            return "R";
        StringBuffer buf = new StringBuffer(8);
        while (n != 0) {
            int r = (int) n & mask;
            n >>= bits;
            buf.append(chars[r]);
        }
        return buf.reverse().toString();
    }

    public static int decode(String s) {
        int len = s.length();
        int n = 0;
        for (int i = 0; i < len; i++) {
            n <<= bits;
            char c = s.charAt(i);
            if (c < ordinals.length) {
                int ord = ordinals[c];
                n += ord;
            }
        }
        return n;
    }

    public static long decodeLong(String s) {
        int len = s.length();
        long n = 0;
        for (int i = 0; i < len; i++) {
            n <<= bits;
            char c = s.charAt(i);
            if (c < ordinals.length) {
                int ord = ordinals[c];
                n += ord;
            }
        }
        return n;
    }

}
