package net.bodz.bas.flow.util;

import net.bodz.bas.flow.IPort;
import net.bodz.bas.flow.IUnit;
import net.bodz.bas.string.Strings;

public class Naming {

    public static String getDefaultName(IUnit unit) {
        String name = unit.getClass().getSimpleName();
        if (name.endsWith("unit"))
            name = name.substring(0, name.length() - 4);
        return Strings.lcfirst(name);
    }

    public static String getDefaultName(IPort port) {
        String name = port.getClass().getSimpleName();
        if (name.endsWith("port"))
            name = name.substring(0, name.length() - 4);
        return Strings.lcfirst(name);
    }

    public static String sigName(String readable) {
        // ArrayOf
        return null;
    }

    public static String readName(String signature) {
        return null;
    }

}
