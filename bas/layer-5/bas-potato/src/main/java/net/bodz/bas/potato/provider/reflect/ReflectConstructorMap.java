package net.bodz.bas.potato.provider.reflect;

import java.lang.reflect.Constructor;

import net.bodz.bas.potato.traits.AbstractConstructorMap;
import net.bodz.bas.potato.traits.ConstructorKey;

public class ReflectConstructorMap
        extends AbstractConstructorMap {

    private static final long serialVersionUID = 1L;

    public ReflectConstructorMap(Class<?> clazz) {
        this(clazz.getConstructors());
    }

    public ReflectConstructorMap(Constructor<?>... ctors) {
        for (Constructor<?> ctor : ctors) {
            ConstructorKey ctorKey = new ConstructorKey(ctor.getParameterTypes());
            ReflectConstructor reflectCtor = new ReflectConstructor(ctor);
            put(ctorKey, reflectCtor);
        }
    }

}
