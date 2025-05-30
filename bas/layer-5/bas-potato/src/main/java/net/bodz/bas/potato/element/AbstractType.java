package net.bodz.bas.potato.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.c.type.TypeArray;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.util.MethodId;

public abstract class AbstractType
        extends AbstractPotatoElement
        implements IType {

    ITypeProvider provider;

    IType superType;
    volatile boolean superTypeLoaded;

    volatile Map<String, IMethod> overloadedMethodMap;

    public AbstractType(ITypeProvider provider, IType declaringType, String name, IElementDoc doc) {
        super(declaringType, name, doc);
        this.provider = provider;
    }

    @Override
    public ITypeProvider getProvider() {
        return provider;
    }

    @Override
    public final Class<?> getDeclaringClass() {
        return getJavaClass().getDeclaringClass();
    }

    @Override
    public IType getSuperType() {
        if (!superTypeLoaded) {
            synchronized (this) {
                if (!superTypeLoaded) {
                    superType = loadSuperType();
                    superTypeLoaded = true;
                }
            }
        }
        return superType;
    }

    protected abstract IType loadSuperType();

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
    public List<IProperty> resolvePropertyVector(String pathProperty)
            throws NoSuchPropertyException {
        if (pathProperty == null)
            throw new NullPointerException("pathProperty");
        IType type = this;
        List<IProperty> properties = new ArrayList<>(2);
        IProperty lastProperty = null;

        StringTokenizer tokens = new StringTokenizer(pathProperty, ".");
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();

            if (type == null) {
                Class<?> propertyType = lastProperty.getPropertyClass();
                type = PotatoTypes.getInstance().loadType(propertyType);
            }

            lastProperty = type.getProperty(token);
            if (lastProperty == null)
                throw new NoSuchPropertyException(token + " in " + type.getName());
            else
                properties.add(lastProperty);
            type = null;
        }
        return properties;
    }

    @Override
    public IMethod getMethod(String methodName, Class<?>... parameterTypes) {
        IMethodMap methodMap = getMethodMap();
        // if (methodMap == null) return null;
        MethodSignature methodKey = new MethodSignature(methodName, parameterTypes);
        return methodMap.getMethod(methodKey);
    }

    @Override
    public IMethod getOverloadedMethod(String methodName) {
        if (overloadedMethodMap == null)
            synchronized (this) {
                if (overloadedMethodMap == null)
                    overloadedMethodMap = loadOverloadedMethods();
            }
        return overloadedMethodMap.get(methodName);
    }

    private Map<String, IMethod> loadOverloadedMethods() {
        Map<String, List<IMethod>> nameListMap = new HashMap<>();
        for (IMethod method : getMethods()) {
            String name = method.getName();
            List<IMethod> list = nameListMap.computeIfAbsent(name, //
                    k -> new ArrayList<IMethod>());
            list.add(method);
        }

        Map<String, IMethod> map = new HashMap<String, IMethod>();
        for (Entry<String, List<IMethod>> entry : nameListMap.entrySet()) {
            String name = entry.getKey();
            List<IMethod> list = entry.getValue();
            IMethod method0 = list.get(0);
            switch (list.size()) {
                case 0:
                    throw new UnexpectedException("empty list");
                case 1:
                    map.put(name, method0);
                default:
                    IElementDoc doc0 = method0.getXjdoc();

                    Class<?> declaringClass = method0.getDeclaringClass();
                    IType declaringType = null;
                    if (declaringClass != null)
                        declaringType = getProvider().loadType(declaringClass);

                    map.put(name, new OverloadedMethod(declaringType, list, doc0));
            }
        }
        return map;
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
            throws PropertyReadException {
        IProperty property = getProperty(propertyName);
        if (property == null) {
            throw new NoSuchPropertyException(propertyName);
        }
        @SuppressWarnings("unchecked")
        T value = (T) property.read(instance);
        return value;
    }

    @Override
    public void set(Object instance, String propertyName, Object value)
            throws PropertyWriteException {
        IProperty property = getProperty(propertyName);
        if (property == null) {
            throw new NoSuchPropertyException(propertyName);
        }
        property.write(instance, value);
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
