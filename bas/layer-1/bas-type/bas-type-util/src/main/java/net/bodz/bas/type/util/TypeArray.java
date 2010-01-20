package net.bodz.bas.type.util;

public class TypeArray {

    public static Class<?>[] getClasses(Object... args) {
        if (args == null)
            throw new NullPointerException("args");
        Class<?>[] classes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null)
                classes[i] = Object.class;
            else
                classes[i] = args[i].getClass();
        }
        return classes;
    }

}
