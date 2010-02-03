package net.bodz.bas.potato.adapter.reflect;

import java.util.Collection;
import java.util.Map;

import net.bodz.bas.potato.AbstractPotatoType;
import net.bodz.bas.potato.IPotatoEvent;
import net.bodz.bas.potato.IPotatoMethod;
import net.bodz.bas.potato.IPotatoProperty;
import net.bodz.bas.reflect.MethodSignature;

public class ReflectPotatoType<T>
        extends AbstractPotatoType<T> {

    Map<String, ReflectPotatoProperty> propertyMap;
    Map<MethodSignature, ReflectPotatoMethod> methodMap;
    Map<String, ReflectPotatoEvent> eventMap;

    public ReflectPotatoType(Class<T> javaType) {
        super(javaType);
    }

    @Override
    public Collection<? extends IPotatoProperty> getProperties() {
        return propertyMap.values();
    }

    @Override
    public Collection<? extends IPotatoMethod> getMethods() {
        return methodMap.values();
    }

    @Override
    public Collection<? extends IPotatoEvent> getEvents() {
        return eventMap.values();
    }

    public static <T> ReflectPotatoType<T> getPotatoType(Class<T> clazz) {
        return new ReflectPotatoType<T>(clazz);
    }

}
