package net.bodz.bas.program.meta;

import net.bodz.bas.meta.build.ProgramName;

public class ProgramNames {

    public static String getValue(Class<?> type) {
        return getValue(type, null);
    }

    public static String getValue(Class<?> type, Boolean toUpperCase) {
        ProgramName pn = type.getAnnotation(ProgramName.class);
        if (pn != null)
            return pn.value();
        String name = type.getSimpleName();
        if (toUpperCase != null)
            name = toUpperCase ? name.toUpperCase() : name.toLowerCase();
        return name;
    }

}
