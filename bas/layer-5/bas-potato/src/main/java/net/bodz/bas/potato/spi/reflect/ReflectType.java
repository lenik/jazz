package net.bodz.bas.potato.spi.reflect;

import net.bodz.bas.potato.spi.builtin.DefaultConstructorMap;
import net.bodz.bas.potato.spi.builtin.DefaultMethodMap;
import net.bodz.bas.potato.spi.builtin.DefaultPropertyMap;
import net.bodz.bas.potato.spi.builtin.NullEventMap;
import net.bodz.bas.potato.traits.*;

public class ReflectType
        extends AbstractType {

    IPropertyMap propertyMap;
    IMethodMap methodMap;
    IConstructorMap constructorMap;
    IEventMap eventMap;

    public ReflectType(Class<?> clazz) {
        super(clazz.getDeclaringClass(), clazz.getName());
        this.propertyMap = new DefaultPropertyMap().addClassFields(clazz);
        this.methodMap = new DefaultMethodMap().addClassMethods(clazz);
        this.constructorMap = new DefaultConstructorMap().addClassConstructors(clazz);
        this.eventMap = NullEventMap.getInstance();
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
