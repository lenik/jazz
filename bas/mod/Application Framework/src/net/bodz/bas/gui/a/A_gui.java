package net.bodz.bas.gui.a;

import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.mod.Factory;
import net.bodz.bas.types.util.Types;

public class A_gui {

    public static Factory getIconFactory(Icon icon) {
        if (icon == null)
            return null;
        Class<? extends Factory> factoryClass = icon.factory();
        if (factoryClass == Factory.class)
            return new Factory.Static(icon.value());
        try {
            return Types.getClassInstance(factoryClass);
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

    public static Factory getLabelFactory(Label label) {
        if (label == null)
            return null;
        Class<? extends Factory> factoryClass = label.factory();
        if (factoryClass == Factory.class)
            return new Factory.Static(label.value());
        try {
            return Types.getClassInstance(factoryClass);
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

}
