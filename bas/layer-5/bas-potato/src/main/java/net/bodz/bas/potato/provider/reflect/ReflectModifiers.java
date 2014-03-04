package net.bodz.bas.potato.provider.reflect;

import java.lang.reflect.Modifier;

import net.bodz.bas.i18n.dom1.IElement;

public class ReflectModifiers {

    public static int toVerboseLevel(int reflectModifiers) {
        int visibility = reflectModifiers & (Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE);
        switch (visibility) {
        case Modifier.PUBLIC:
            return IElement.PUBLIC_LEVEL;

        case Modifier.PROTECTED:
        case 0:
            return IElement.EXPERT_LEVEL;

        case Modifier.PRIVATE:
        default:
            return IElement.INTERNAL_LEVEL;
        }
    }

}
