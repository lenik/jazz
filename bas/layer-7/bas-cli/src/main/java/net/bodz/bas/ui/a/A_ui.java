package net.bodz.bas.ui.a;

import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.type.util.ClassInstance;
import net.bodz.bas.util.Factory;

public class A_ui {

    public static Factory<?> getIconFactory(Icon aicon) {
        if (aicon == null)
            return null;
        Class<? extends Factory<?>> factoryClass = aicon.factory();
        if (factoryClass == Factory.class)
            return new Factory.Static<Object>(aicon.value());
        try {
            return ClassInstance.getClassInstance(factoryClass);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static Factory<String> getLabelFactory(Label alabel) {
        if (alabel == null)
            return null;
        Class<? extends Factory<String>> factoryClass = alabel.factory();
        if (factoryClass == Factory.class)
            return new Factory.Static<String>(alabel.value());
        try {
            return ClassInstance.getClassInstance(factoryClass);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

}
