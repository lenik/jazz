package net.bodz.bas.c.string;

public class StringPred {

    public static boolean isInteger(String str) {
        int i = str.length();
        while (--i >= 0) {
            char c = str.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

}
