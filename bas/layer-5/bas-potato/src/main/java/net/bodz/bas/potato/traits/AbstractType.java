package net.bodz.bas.potato.traits;

import java.util.Collection;

public abstract class AbstractType
        extends AbstractElement
        implements IType {

    public AbstractType(String name) {
        super(null, name);
    }

    public AbstractType(Class<?> declaringType, String name) {
        super(declaringType, name);
    }

    @Override
    public Collection<IProperty> getProperties() {
        return getPropertyMap().values();
    }

    @Override
    public Collection<IMethod> getMethods() {
        return getMethodMap().values();
    }

    @Override
    public Collection<IConstructor> getConstructors() {
        return getConstructorMap().values();
    }

    @Override
    public Collection<IEvent> getEvents() {
        return getEventMap().values();
    }

    @Override
    public IProperty getProperty(String propertyName) {
        IPropertyMap propertyMap = getPropertyMap();
        // if (propertyMap == null) return null;
        return propertyMap.get(propertyName);
    }

    @Override
    public IMethod getMethod(String methodName, Class<?>... parameterTypes) {
        IMethodMap methodMap = getMethodMap();
        // if (methodMap == null) return null;
        MethodKey methodKey = new MethodKey(methodName, parameterTypes);
        return methodMap.getMethod(methodKey);
    }

    @Override
    public IConstructor getConstructor(Class<?>... parameterTypes) {
        IConstructorMap constructorMap = getConstructorMap();
        // if (constructorMap == null) return null;
        ConstructorKey constructorKey = new ConstructorKey(parameterTypes);
        return constructorMap.getConstructor(constructorKey);
    }

    @Override
    public IEvent getEvent(String eventName) {
        IEventMap eventMap = getEventMap();
        // if (eventMap == null) return null;
        return eventMap.get(eventName);
    }

}
