package net.bodz.bas.loader.dev;

import net.bodz.bas.exceptions.UnexpectedException;

public class IncompletedLoadedClass {

    /**
     * XXX - Doesn't a Class is already loaded? check in more detail...
     */
    @Deprecated
    public static void load(Class<?> clazz) {
        String name = clazz.getName();
        ClassLoader loader = clazz.getClassLoader();
        try {
            Class.forName(name, true, loader);
        } catch (ClassNotFoundException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

}
