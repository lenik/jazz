package net.bodz.bas.loader;

import net.bodz.bas.a.BootInfo;

@BootInfo(configs = LoaderTest._LC.class)
public class LoaderTest {

    private static class _LC extends _LoadConfig {
    }

    static {
        System.out.println("! loaded " + LoaderTest.class);
    }

    public static Class<?> findClass(String className)
            throws ClassNotFoundException {
        return Class.forName(className);
    }

}
