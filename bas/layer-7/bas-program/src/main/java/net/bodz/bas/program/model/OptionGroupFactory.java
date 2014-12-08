package net.bodz.bas.program.model;

import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.program.xjdoc.ClassDocToOptionsConverter;
import net.bodz.bas.program.xjdoc.OptionGroupInheritance;

public class OptionGroupFactory {

    // private static boolean cache = false;
    private static LazyTypeMap<IOptionGroup> clsOptions;
    static {
        ClassDocToOptionsConverter converter = new ClassDocToOptionsConverter();
        {
            converter.setInheritance(OptionGroupInheritance.reflective);
            converter.setIncludeNonPublic(true);
        }
        clsOptions = TypeMapRegistry.createMap(converter);
    }

    public static <T> IOptionGroup getClassOptions(Class<T> clazz) {
        IOptionGroup copt = clsOptions.getOrLoad(clazz);
        return copt;
    }

}
