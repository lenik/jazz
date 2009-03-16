package net.bodz.swt.gui.pfl;

public class PagePath {

    public static boolean isAbsolute(String path) {
        if (path != null && path.startsWith("/"))
            return true;
        return false;
    }

    public static String join(String a, String b) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        if (b.startsWith("/"))
            return b;
        return a + "/" + b;
    }

}
