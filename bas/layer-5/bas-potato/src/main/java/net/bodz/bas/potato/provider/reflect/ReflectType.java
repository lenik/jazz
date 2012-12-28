package net.bodz.bas.potato.provider.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.potato.element.*;

public class ReflectType
        extends AbstractType {

    private MutablePropertyMap propertyMap = new MutablePropertyMap();
    private MutableMethodMap methodMap = new MutableMethodMap();
    private MutableConstructorMap constructorMap = new MutableConstructorMap();
    private IEventMap eventMap = NullEventMap.getInstance();

    private final int verboseLevel;

    public ReflectType(Class<?> clazz) {
        super(clazz.getDeclaringClass(), clazz.getName());

        // propertyMap.addClassFields(clazz);
        for (Field field : clazz.getFields()) {
            ReflectProperty reflectProperty = new ReflectProperty(field);
            propertyMap.addProperty(reflectProperty);
        }

        for (Method method : clazz.getMethods()) {
            ReflectMethod reflectMethod = new ReflectMethod(method);
            methodMap.addMethod(reflectMethod);
        }

        for (Constructor<?> ctor : clazz.getConstructors()) {
            ReflectConstructor reflectCtor = new ReflectConstructor(ctor);
            constructorMap.addConstructor(reflectCtor);
        }

        int _modifiers = clazz.getModifiers();
        this.verboseLevel = ReflectModifiers.toVerboseLevel(_modifiers);

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

    // -o IElement

    @Override
    public int getVerboseLevel() {
        return verboseLevel;
    }

}
