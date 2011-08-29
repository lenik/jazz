package net.bodz.bas.loader.dev;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.jdk6compat.jdk7emul.ClassNotFoundException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;

public class IncompletedLoadedClass {

    /**
     * XXX - Doesn't a Class is already loaded? check in more detail...
     */
    @Deprecated
    public static void load(Class<?> clazz) {
        String name = clazz.getName();
        ClassLoader loader = clazz.getClassLoader();
        try {
            Jdk7Reflect.forName(name, true, loader);
        } catch (ClassNotFoundException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

}
