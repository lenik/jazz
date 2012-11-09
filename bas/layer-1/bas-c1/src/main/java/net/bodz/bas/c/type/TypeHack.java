package net.bodz.bas.c.type;

import java.net.URL;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.UnexpectedException;

public class TypeHack {

    public static URL getContextURL(Class<?> clazz) {
        clazz = TypeHack.skipProxies(clazz);

        String className = clazz.getName();
        String baseName = StringPart.afterLast(className, '.');
        if (baseName == null)
            baseName = className;

        String fileName = baseName + ".class";
        URL resource = clazz.getResource(fileName);
        if (resource == null)
            throw new UnexpectedException("The .class file doesn't exist or can't be resolved: " + clazz);
        return resource;
    }

    static Class<?> cglibFactoryClass;
    static Class<?> javaassistProxyObjectClass;

    static {
        try {
            cglibFactoryClass = Class.forName("net.sf.cglib.proxy.Factory");
        } catch (ClassNotFoundException e) {
            cglibFactoryClass = null;
        }
        try {
            javaassistProxyObjectClass = Class.forName("javassist.util.proxy.ProxyObject");
        } catch (ClassNotFoundException e) {
            javaassistProxyObjectClass = null;
        }
    }

    public static Class<?> skipProxies(Class<?> clazz) {
        if (clazz == null)
            return null;

        // if (baseName.contains("$$"))
        // return getContextURL(clazz.getSuperclass());
        if (cglibFactoryClass != null)
            if (cglibFactoryClass.isAssignableFrom(clazz))
                return skipProxies(clazz.getSuperclass());

        if (javaassistProxyObjectClass != null)
            if (javaassistProxyObjectClass.isAssignableFrom(clazz))
                return skipProxies(clazz.getSuperclass());

        return clazz;
    }

}
