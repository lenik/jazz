package net.bodz.bas.loader;

import net.bodz.bas.jdk6compat.jdk7emul.ClassNotFoundException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.loader.boot.BootInfo;

@BootInfo(configs = LoaderTest.Config1.class)
public class LoaderTest {

    private static class Config1
            extends _LoadConfig {
    }

    static {
        System.out.println("! loaded " + LoaderTest.class);
    }

    public static Class<?> findClass(String className)
            throws ClassNotFoundException {
        return Jdk7Reflect.forName(className);
    }

}
