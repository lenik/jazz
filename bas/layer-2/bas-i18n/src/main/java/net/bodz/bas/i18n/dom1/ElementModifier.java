package net.bodz.bas.i18n.dom1;

public class ElementModifier {

    public static final int READ_ONLY = 1; // final
    public static final int SHARED = 2; // static

    public static boolean isReadOnly(int modifiers) {
        return (modifiers & READ_ONLY) != 0;
    }

    public static boolean isShared(int modifiers) {
        return (modifiers & SHARED) != 0;
    }

}
