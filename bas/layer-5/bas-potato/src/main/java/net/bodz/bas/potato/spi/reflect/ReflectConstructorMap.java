package net.bodz.bas.potato.spi.reflect;

import java.lang.reflect.Constructor;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.potato.traits.AbstractConstructorMap;

public class ReflectConstructorMap
        extends AbstractConstructorMap {

    private static final long serialVersionUID = 1L;

    public ReflectConstructorMap(Class<?> clazz) {
        this(clazz.getConstructors());
    }

    public ReflectConstructorMap(Constructor<?>... ctors) {
        for (Constructor<?> ctor : ctors) {
            MethodSignature signature = new MethodSignature(null, ctor.getParameterTypes());
            ReflectConstructor reflectCtor = new ReflectConstructor(ctor);
            put(signature, reflectCtor);
        }
    }

}
