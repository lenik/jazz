package net.bodz.bas.potato;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.type.util.TypeArray;

public abstract class AbstractPotatoType<T>
        // extends AbstractTypeTraits<T>
        extends AbstractPotatoElement
        implements IPotatoType<T> {

    protected final Class<T> javaType;

    public AbstractPotatoType(Class<T> javaType) {
        super(javaType.getSimpleName());
        this.javaType = javaType;
    }

    @Override
    public Class<T> getJavaType() {
        return javaType;
    }

    @Override
    public Annotation[] getAnnotations() {
        return javaType.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return javaType.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return javaType.getAnnotation(annotationClass);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return javaType.isAnnotationPresent(annotationClass);
    }

    @Override
    public Collection<? extends IPotatoType<?>> getInheritedPotatos() {
        return Collections.emptyList();
    }

    @Override
    public IPotatoProperty getDefaultProperty() {
        return null; // getProperty("default");
    }

    /**
     * Search {@link #getProperties()} for a matching property.
     * 
     * @return A matched property or <code>null</code> if none is matched.
     */
    @Override
    public IPotatoProperty getProperty(String propertyName) {
        if (propertyName == null)
            throw new NullPointerException("propertyName");
        for (IPotatoProperty property : getProperties())
            if (propertyName.equals(property.getName()))
                return property;
        return null;
    }

    @Override
    public Object getPropertyValue(Object instance, String propertyName)
            throws PotatoException, NoSuchPotatoPropertyException, PotatoTargetException {
        if (propertyName == null)
            throw new NullPointerException("propertyName");
        IPotatoProperty property = getProperty(propertyName);
        if (property == null)
            throw new NoSuchPotatoPropertyException(propertyName);
        return property.get(instance);
    }

    @Override
    public void setPropertyValue(Object instance, String propertyName, Object propertyValue)
            throws PotatoException, PotatoTargetException {
        IPotatoProperty property = getProperty(propertyName);
        if (property == null)
            throw new NoSuchPotatoPropertyException(propertyName);
        property.set(instance, propertyValue);
    }

    /**
     * Search {@link #getMethods()} for a matching method.
     * 
     * @return A matched method or <code>null</code> if none is matched.
     * @see
     */
    @Override
    public IPotatoMethod getMethod(String methodName, Class<?>... parameterTypes) {
        if (methodName == null)
            throw new NullPointerException("methodName");
        if (parameterTypes == null)
            throw new NullPointerException("parameterTypes");
        for (IPotatoMethod method : getMethods())
            if (methodName.equals(method.getName())) { // XXX - fuzzy param types?
                Class<?>[] s = method.getParameterTypes();
                if (Arrays.equals(s, parameterTypes))
                    return method;
            }
        return null;
    }

    @Override
    public Object invoke(Object instance, String methodName, Object... parameters)
            throws PotatoException, NoSuchPotatoMethodException, PotatoTargetException {
        Class<?>[] parameterTypes = TypeArray.getClasses(parameters);
        IPotatoMethod method = getMethod(methodName, parameterTypes);
        if (method == null)
            throw new NoSuchPotatoMethodException(methodName);
        return method.invoke(instance, parameters);
    }

    @Override
    public IPotatoEvent getEvent(String eventName) {
        if (eventName == null)
            throw new NullPointerException("eventName");
        for (IPotatoEvent event : getEvents())
            if (eventName.equals(event.getName()))
                return event;
        return null;
    }

}
