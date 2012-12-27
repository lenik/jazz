package net.bodz.bas.potato.spi.reflect;

import java.lang.reflect.Modifier;

import net.bodz.bas.i18n.dom1.ElementModifier;
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

    public static int toElementModifiers(int reflectModifiers) {
        int elementModifiers = 0;

        if (Modifier.isFinal(reflectModifiers))
            elementModifiers |= ElementModifier.READ_ONLY;

        if (Modifier.isStatic(reflectModifiers))
            elementModifiers |= ElementModifier.SHARED;

        return elementModifiers;
    }

}
