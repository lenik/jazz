package net.bodz.bas.potato.provider.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.potato.element.*;

public class ReflectPotatoElementProvider
        extends AbstractPotatoElementProvider {

    public static final int PRIORITY = 200;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IProperty getProperty(Class<?> objType, Object obj, String propertyName) {
        try {
            Field field = objType.getField(propertyName);
            return new ReflectProperty(field);
        } catch (NoSuchFieldException | SecurityException e) {
            return null;
        }
    }

    @Override
    public IMethod getMethod(Class<?> objType, Object obj, MethodSignature signature) {
        Method method = signature.findMethod(objType);
        if (method == null)
            return null;
        else
            return new ReflectMethod(method);
    }

    @Override
    public IConstructor getConstructor(Class<?> objType, Object obj, MethodSignature signature) {
        Constructor<?> ctor = signature.findConstructor(objType);
        if (ctor == null)
            return null;
        else
            return new ReflectConstructor(ctor);
    }

    @Override
    public void fillProperties(Class<?> objType, Object obj, MutablePropertyMap propertyMap) {
        for (Field field : objType.getFields())
            if (!propertyMap.containsProperty(field.getName())) {
                ReflectProperty reflectProperty = new ReflectProperty(field);
                propertyMap.addProperty(reflectProperty);
            }
    }

    @Override
    public void fillMethods(Class<?> objType, Object obj, MutableMethodMap methodMap) {
        for (Method method : objType.getMethods()) {
            MethodSignature signature = new MethodSignature(method);
            if (!methodMap.containsMethod(signature)) {
                ReflectMethod reflectMethod = new ReflectMethod(method);
                methodMap.addMethod(reflectMethod);
            }
        }
    }

    @Override
    public void fillConstructor(Class<?> objType, Object obj, MutableConstructorMap constructorMap) {
        for (Constructor<?> ctor : objType.getConstructors()) {
            MethodSignature signature = new MethodSignature(ctor);
            if (!constructorMap.containsConstructor(signature)) {
                ReflectConstructor reflectCtor = new ReflectConstructor(ctor);
                constructorMap.addConstructor(reflectCtor);
            }
        }
    }

}
