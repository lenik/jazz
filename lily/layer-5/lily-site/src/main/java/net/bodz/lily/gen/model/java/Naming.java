package net.bodz.lily.gen.model.java;

public class Naming {

    public static final String ID_SUFFIX = "_Id";

    /** _Foo_stuff */
    public static String stuff(String name) {
        return "_" + name + "_stuff";
    }

    public static String id(String name) {
        return name + ID_SUFFIX;
    }

    public static String mask(String name) {
        return name + "Mask";
    }

    public static String maskStuff(String name) {
        return stuff(mask(name));
    }

    public static String index(String name) {
        return name + "Index";
    }

    public static String mapper(String name) {
        return name + "Mapper";
    }

    public static String mapperTest(String name) {
        return name + "MapperTest";
    }

    public static String samples(String name) {
        return name + "Samples";
    }

}
