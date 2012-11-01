package net.bodz.bas.potato.spi.builtin;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.c.reflect.MethodSignatureComparator;
import net.bodz.bas.potato.model.IConstructor;
import net.bodz.bas.potato.model.IConstructorMap;
import net.bodz.bas.potato.spi.reflect.ReflectConstructor;

public class DefaultConstructorMap
        implements IConstructorMap {

    Map<MethodSignature, IConstructor> map;

    public DefaultConstructorMap() {
        map = new TreeMap<MethodSignature, IConstructor>(MethodSignatureComparator.getInstance());
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Collection<IConstructor> getConstructors() {
        return map.values();
    }

    @Override
    public IConstructor getConstructor(MethodSignature signature) {
        return map.get(signature);
    }

    public DefaultConstructorMap addClassConstructors(Class<?> clazz) {
        addConstructors(clazz.getConstructors());
        return this;
    }

    public DefaultConstructorMap addConstructors(Constructor<?>... ctors) {
        for (Constructor<?> ctor : ctors) {
            MethodSignature signature = new MethodSignature(null, ctor.getParameterTypes());
            ReflectConstructor reflectCtor = new ReflectConstructor(ctor);
            map.put(signature, reflectCtor);
        }
        return this;
    }

}
