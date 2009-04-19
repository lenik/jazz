package net.bodz.bas.gui.a;

import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.types.util.Types;
import net.bodz.bas.util.Factory;

public class A_gui {

    public static Factory<?> getIconFactory(Icon aicon) {
        if (aicon == null)
            return null;
        Class<? extends Factory<?>> factoryClass = aicon.factory();
        if (factoryClass == Factory.class)
            return new Factory.Static<Object>(aicon.value());
        try {
            return Types.getClassInstance(factoryClass);
        } catch (CreateException e) {
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
            return Types.getClassInstance(factoryClass);
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

}
