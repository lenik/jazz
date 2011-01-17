package net.bodz.bas.meta.program;

import net.bodz.bas.annotation.util.AnnotationParseUtil;

public class ProgramNameUtil
        extends AnnotationParseUtil {

    public static String getProgramName(Class<?> type) {
        return getProgramName(type, null);
    }

    public static String getProgramName(Class<?> type, Boolean toUpperCase) {
        ProgramName pn = type.getAnnotation(ProgramName.class);
        if (pn != null)
            return pn.value();
        String name = type.getSimpleName();
        if (toUpperCase != null)
            name = toUpperCase ? name.toUpperCase() : name.toLowerCase();
        return name;
    }

}
