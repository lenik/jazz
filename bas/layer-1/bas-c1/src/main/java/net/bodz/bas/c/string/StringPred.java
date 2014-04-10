package net.bodz.bas.c.string;

public class StringPred {

    public static boolean isDecimal(String str) {
        if (str == null)
            return false;
        if (str.isEmpty())
            return false;

        if (str.charAt(0) == '-') {
            str = str.substring(1);
            if (str.isEmpty())
                return false;
        }

        int i = str.length();
        while (--i >= 0) {
            char c = str.charAt(i);
            if (!(c >= '0' && c <= '9'))
                return false;
        }
        return true;
    }

    static byte[] c2n = CharFeature.c2n;

    public static boolean isHexadecimal(String str) {
        if (str == null)
            return false;
        if (str.isEmpty())
            return false;

        if (str.charAt(0) == '-') {
            str = str.substring(1);
            if (str.isEmpty())
                return false;
        }

        int i = str.length();
        while (--i >= 0) {
            char c = str.charAt(i);
            if (c >= c2n.length)
                return false;
            int n = c2n[c];
            if (!(n >= 0 && n < 16))
                return false;
        }
        return true;
    }

}
