package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.c.type.TypeArray;
import net.bodz.mda.xjdoc.util.MethodId;

public abstract class AbstractType
        extends AbstractPotatoElement
        implements IType {

    public AbstractType(String name) {
        super(null, name);
    }

    public AbstractType(Class<?> declaringClass, String name) {
        super(declaringClass, name);
    }

    @Override
    public Iterable<IProperty> getProperties() {
        return getPropertyMap().getProperties();
    }

    @Override
    public Iterable<IMethod> getMethods() {
        return getMethodMap().getMethods();
    }

    @Override
    public Iterable<IConstructor> getConstructors() {
        return getConstructorMap().getConstructors();
    }

    @Override
    public Iterable<IEvent> getEvents() {
        return getEventMap().getEvents();
    }

    @Override
    public IProperty getProperty(String propertyName) {
        IPropertyMap propertyMap = getPropertyMap();
        // if (propertyMap == null) return null;
        return propertyMap.getProperty(propertyName);
    }

    @Override
    public IMethod getMethod(String methodName, Class<?>... parameterTypes) {
        IMethodMap methodMap = getMethodMap();
        // if (methodMap == null) return null;
        MethodSignature methodKey = new MethodSignature(methodName, parameterTypes);
        return methodMap.getMethod(methodKey);
    }

    @Override
    public IConstructor getConstructor(Class<?>... parameterTypes) {
        IConstructorMap constructorMap = getConstructorMap();
        // if (constructorMap == null) return null;
        MethodSignature constructorKey = new MethodSignature(null, parameterTypes);
        return constructorMap.getConstructor(constructorKey);
    }

    @Override
    public IEvent getEvent(String eventName) {
        IEventMap eventMap = getEventMap();
        // if (eventMap == null) return null;
        return eventMap.getEvent(eventName);
    }

    @Override
    public <T> T get(Object instance, String propertyName)
            throws ReflectiveOperationException {
        IProperty property = getProperty(propertyName);
        if (property == null) {
            throw new NoSuchPropertyException(propertyName);
        }
        T value = (T) property.getValue(instance);
        return value;
    }

    @Override
    public void set(Object instance, String propertyName, Object value)
            throws ReflectiveOperationException {
        IProperty property = getProperty(propertyName);
        if (property == null) {
            throw new NoSuchPropertyException(propertyName);
        }
        property.setValue(instance, value);
    }

    @Override
    public Object invoke(Object instance, String methodName, Class<?>[] parameterTypes, Object... parameters)
            throws ReflectiveOperationException {
        IMethod method = getMethod(methodName, parameterTypes);
        if (method == null) {
            MethodSignature signature = new MethodSignature(methodName, parameterTypes);
            throw new NoSuchMethodException(signature.toString());
        }
        Object returnValue = method.invoke(instance, parameters);
        return returnValue;
    }

    @Override
    public Object invoke(Object instance, String methodName, Object... parameters)
            throws ReflectiveOperationException {
        Class<?>[] parameterTypes = TypeArray.getClasses(null, parameters);

        IMethod method = getMethod(methodName, parameterTypes);
        if (method == null) {
            MethodId methodId = new MethodId(methodName, parameterTypes);
            throw new NoSuchMethodException(methodId.toString());
        }

        Object returnValue = method.invoke(instance, parameters);
        return returnValue;
    }

    @Override
    public Object invokeStatic(String methodName, Class<?>[] parameterTypes, Object... parameters)
            throws ReflectiveOperationException {
        IMethod method = getMethod(methodName, parameterTypes);
        if (method == null) {
            MethodSignature signature = new MethodSignature(methodName, parameterTypes);
            throw new NoSuchMethodException(signature.toString());
        }
        Object returnValue = method.invokeStatic(parameters);
        return returnValue;
    }

    @Override
    public Object newInstance(Class<?>[] parameterTypes, Object... parameters)
            throws ReflectiveOperationException {
        IConstructor ctor = getConstructor(parameterTypes);
        if (ctor == null) {
            MethodSignature signature = new MethodSignature("~ctor", parameterTypes);
            throw new NoSuchMethodException(signature.toString());
        }
        Object instance = ctor.newInstance(parameters);
        return instance;
    }

}
