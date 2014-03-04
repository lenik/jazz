package net.bodz.bas.i18n.dom1;

import java.lang.reflect.Modifier;

public class ElementFlags {

    public static final int READ_ONLY = 1; // final
    public static final int SHARED = 2; // static

    public static boolean isReadOnly(int modifiers) {
        return (modifiers & READ_ONLY) != 0;
    }

    public static boolean isShared(int modifiers) {
        return (modifiers & SHARED) != 0;
    }

    public static int fromModifiers(int reflectModifiers) {
        int elementModifiers = 0;

        if (Modifier.isFinal(reflectModifiers))
            elementModifiers |= ElementFlags.READ_ONLY;

        if (Modifier.isStatic(reflectModifiers))
            elementModifiers |= ElementFlags.SHARED;

        return elementModifiers;
    }

}
