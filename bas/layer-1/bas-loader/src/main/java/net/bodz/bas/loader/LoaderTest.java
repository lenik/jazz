package net.bodz.bas.loader;


@BootInfo(configs = LoaderTest.Config1.class)
public class LoaderTest {

    private static class Config1 extends _LoadConfig {
    }

    static {
        System.out.println("! loaded " + LoaderTest.class); 
    }

    public static Class<?> findClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

}
