package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;

public abstract class AbstractPotatoElementProvider
        implements IPotatoElementProvider {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public IProperty getProperty(Class<?> objType, Object obj, String propertyName) {
        return null;
    }

    @Override
    public IMethod getMethod(Class<?> objType, Object obj, MethodSignature signature) {
        return null;
    }

    @Override
    public IConstructor getConstructor(Class<?> objType, Object obj, MethodSignature signature) {
        return null;
    }

    @Override
    public IEvent getEvent(Class<?> objType, Object obj, String eventName) {
        return null;
    }

    @Override
    public void fillProperties(Class<?> objType, Object obj, MutablePropertyMap propertyMap) {
    }

    @Override
    public void fillMethods(Class<?> objType, Object obj, MutableMethodMap methodMap) {
    }

    @Override
    public void fillConstructor(Class<?> objType, Object obj, MutableConstructorMap constructorMap) {
    }

    @Override
    public void fillEventMap(Class<?> objType, Object obj, MutableEventMap eventMap) {
    }

}
