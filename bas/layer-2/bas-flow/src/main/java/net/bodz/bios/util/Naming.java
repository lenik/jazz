package net.bodz.bios.util;

import net.bodz.bas.text.util.Strings;
import net.bodz.bios.Port;
import net.bodz.bios.Unit;

public class Naming {

    public static String getDefaultName(Unit unit) {
        String name = unit.getClass().getSimpleName();
        if (name.endsWith("unit")) 
            name = name.substring(0, name.length() - 4);
        return Strings.lcfirst(name);
    }

    public static String getDefaultName(Port port) {
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
