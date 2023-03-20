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

    public static boolean isUpperCase(String s) {
        return isUpperCase(s, false);
    }

    public static boolean isUpperCase(String s, boolean defaultVal) {
        int n = s.length();
        int nLetter = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) {
                nLetter++;
                if (!Character.isUpperCase(ch))
                    return false;
            }
        }
        if (nLetter == 0)
            return defaultVal;
        else
            return true;
    }

    public static boolean isLowerCase(String s) {
        return isLowerCase(s, false);
    }

    public static boolean isLowerCase(String s, boolean defaultVal) {
        int n = s.length();
        int nLetter = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) {
                nLetter++;
                if (!Character.isLowerCase(ch))
                    return false;
            }
        }
        if (nLetter == 0)
            return defaultVal;
        else
            return true;
    }

}
