package net.bodz.dist.pro.seals;

public class CodeSet {

    public static final int size = 32;
    public static final int bits = 5;
    public static final int mask = size - 1;

    static final char[]     chars;
    static final int[]      ordinals;

    static {
        String zerospec = "EORW"; //$NON-NLS-1$
        chars = "0123456789ABCDFGHIJKLMNPQSTUVXYZ".toCharArray(); //$NON-NLS-1$
        assert chars.length == size;
        ordinals = new int[256];
        for (int i = 0; i < 256; i++)
            ordinals[i] = -1;
        for (int i = 0; i < size; i++)
            ordinals[chars[i]] = i;
        for (int i = 0; i < zerospec.length(); i++)
            ordinals[zerospec.charAt(i)] = 0;
    }

    public static char chr(int ord) {
        return chars[ord];
    }

    public static int ord(char c) {
        return ordinals[c];
    }

    public static String encode(int n) {
        if (n == 0)
            return "R"; //$NON-NLS-1$
        StringBuffer buf = new StringBuffer(8);
        while (n != 0) {
            int r = n & mask;
            n >>>= bits;
            buf.append(chars[r]);
        }
        return buf.reverse().toString();
    }

    public static String encode(long n) {
        if (n == 0)
            return "R"; //$NON-NLS-1$
        StringBuffer buf = new StringBuffer(8);
        while (n != 0) {
            int r = (int) n & mask;
            n >>>= bits;
            buf.append(chars[r]);
        }
        return buf.reverse().toString();
    }

    public static int decode(String s) {
        s = s.toUpperCase();
        int len = s.length();
        int n = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c < ordinals.length) {
                int ord = ordinals[c];
                if (ord != -1) {
                    n <<= bits;
                    n += ord;
                }
            }
        }
        return n;
    }

    public static long decodeLong(String s) {
        s = s.toUpperCase();
        int len = s.length();
        long n = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c < ordinals.length) {
                int ord = ordinals[c];
                if (ord != -1) {
                    n <<= bits;
                    n += ord;
                }
            }
        }
        return n;
    }

}
