package net.bodz.bas.c.loader.scan;

@FunctionalInterface
public interface ITypeCallback {

    boolean type(Class<?> clazz);

}
