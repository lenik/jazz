package net.bodz.bas.cli.model;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.cli.xjdoc.ClassDocOptionParser;
import net.bodz.bas.cli.xjdoc.OptionGroupInheritance;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.err.ParseException;

public class OptionGroupFactory {

    // private static boolean cache = false;
    private static ClassLocal<IOptionGroup> clOptions;
    static {
        clOptions = ClassLocals.createMap(new IMapEntryLoader<Class<?>, IOptionGroup>() {

            ClassDocOptionParser parser = new ClassDocOptionParser();
            {
                parser.setInheritance(OptionGroupInheritance.reflective);
            }

            @Override
            public IOptionGroup loadValue(Class<?> clazz)
                    throws LazyLoadException {
                IOptionGroup options;
                try {
                    options = parser.parseTree(clazz);
                } catch (ParseException e) {
                    throw new LazyLoadException(e.getMessage(), e);
                }
                return options;
            }

        });
    }

    public static <T> IOptionGroup getClassOptions(Class<T> clazz) {
        IOptionGroup copt = clOptions.load(clazz);
        return copt;
    }

}
