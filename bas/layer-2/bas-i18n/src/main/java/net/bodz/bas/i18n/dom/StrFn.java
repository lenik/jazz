package net.bodz.bas.i18n.dom;

public class StrFn {

    void f() {
    }

    public static iString conv(Object obj) {
        if (obj == null)
            return iString.NULL;
        if (obj instanceof iString)
            return (iString) obj;
        else
            return wrap(obj.toString());
    }

    public static iString wrap(String plainString) {
        return new XiString(plainString);
    }

    public static boolean isEmpty(iString s) {
        if (s == null)
            return true;
        String str = s.toString();
        if (str == null)
            return true;
        if (str.trim().isEmpty())
            return true;
        return false;
    }

}
