package net.bodz.bas.cli.model;

import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;

public class OptionGroupFactory {

    // private static boolean cache = false;
    private static ClassLocal<IOptionGroup> clOptions = ClassLocals.createMap(IOptionGroup.class);

    public static <T> IOptionGroup getClassOptions(Class<T> clazz) {
        IOptionGroup copt = clOptions.load(clazz);
        return copt;
    }

}
