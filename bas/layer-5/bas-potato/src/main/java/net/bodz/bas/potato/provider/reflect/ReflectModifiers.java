package net.bodz.bas.potato.provider.reflect;

import java.lang.reflect.Modifier;

import net.bodz.bas.meta.bean.DetailLevel;

public class ReflectModifiers {

    public static int toDetailLevel(int reflectModifiers) {
        int visibility = reflectModifiers & (Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE);
        switch (visibility) {
        case Modifier.PUBLIC:
            return DetailLevel.NORMAL;

        case Modifier.PROTECTED:
            return DetailLevel.DETAIL;

        case 0: /* package-protected */
            return DetailLevel.EXPERT;

        case Modifier.PRIVATE:
        default:
            return DetailLevel.HIDDEN;
        }
    }

}
