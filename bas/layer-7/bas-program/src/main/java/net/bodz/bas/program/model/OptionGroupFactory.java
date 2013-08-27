package net.bodz.bas.program.model;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.program.xjdoc.ClassDocToOptionsConverter;
import net.bodz.bas.program.xjdoc.OptionGroupInheritance;

public class OptionGroupFactory {

    // private static boolean cache = false;
    private static LazyTypeMap<IOptionGroup> clsOptions;
    static {
        clsOptions = TypeMapRegistry.createMap(new IMapEntryLoader<Class<?>, IOptionGroup>() {

            ClassDocToOptionsConverter converter = new ClassDocToOptionsConverter();
            {
                converter.setInheritance(OptionGroupInheritance.reflective);
                converter.setIncludeNonPublic(true);
            }

            @Override
            public IOptionGroup loadValue(Class<?> clazz)
                    throws LazyLoadException {
                IOptionGroup options;
                try {
                    options = converter.convertTree(clazz);
                } catch (ParseException e) {
                    throw new LazyLoadException(e.getMessage(), e);
                }
                return options;
            }

        });
    }

    public static <T> IOptionGroup getClassOptions(Class<T> clazz) {
        IOptionGroup copt = clsOptions.getOrLoad(clazz);
        return copt;
    }

}
