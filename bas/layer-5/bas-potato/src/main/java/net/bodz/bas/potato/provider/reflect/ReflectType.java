package net.bodz.bas.potato.provider.reflect;

import net.bodz.bas.potato.traits.AbstractType;
import net.bodz.bas.potato.traits.IConstructorMap;
import net.bodz.bas.potato.traits.IEventMap;
import net.bodz.bas.potato.traits.IMethodMap;
import net.bodz.bas.potato.traits.IPropertyMap;
import net.bodz.bas.potato.traits.util.NullEventMap;

public class ReflectType
        extends AbstractType {

    ReflectPropertyMap propertyMap;
    ReflectMethodMap methodMap;
    ReflectConstructorMap constructorMap;
    NullEventMap eventMap;

    public ReflectType(Class<?> clazz) {
        super(clazz.getDeclaringClass(), clazz.getName());
        this.propertyMap = new ReflectPropertyMap(clazz);
        this.methodMap = new ReflectMethodMap(clazz);
        this.constructorMap = new ReflectConstructorMap(clazz);
        this.eventMap = new NullEventMap();
    }

    @Override
    public IPropertyMap getPropertyMap() {
        return propertyMap;
    }

    @Override
    public IMethodMap getMethodMap() {
        return methodMap;
    }

    @Override
    public IConstructorMap getConstructorMap() {
        return constructorMap;
    }

    @Override
    public IEventMap getEventMap() {
        return eventMap;
    }

}
