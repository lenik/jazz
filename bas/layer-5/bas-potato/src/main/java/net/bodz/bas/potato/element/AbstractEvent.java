package net.bodz.bas.potato.element;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.c.string.Strings;
import net.bodz.mda.xjdoc.model.IElementDoc;

public abstract class AbstractEvent
        extends AbstractPotatoElement
        implements
            IEvent {

    private final String ucfirstName;

    private final Class<?> listenerClass;
    private final Class<?> eventClass;

    public AbstractEvent(IType declaringType, String eventName, Class<?> listenerClass, Class<?> eventClass,
            IElementDoc doc) {
        super(declaringType, eventName, doc);
        if (eventName == null)
            throw new NullPointerException("eventName");
        if (eventName.isEmpty())
            throw new IllegalArgumentException("eventName is empty");
        ucfirstName = Strings.ucfirst(eventName);

        if (listenerClass == null)
            throw new NullPointerException("listenerClass");
        this.listenerClass = listenerClass;

        // if (eventClass == null)
        // throw new NullPointerException("eventClass");
        this.eventClass = eventClass;
    }

    @Override
    public Class<?> getListenerClass() {
        return listenerClass;
    }

    @Override
    public Class<?> getEventClass() {
        return eventClass;
    }

    @Override
    public Collection<?> getListeners(Object instance)
            throws ReflectiveOperationException {
        Method getListenersMethod = getGetListenersMethod();
        if (getListenersMethod == null)
            return Collections.emptySet();
        // Class<?> returnType = getListenersMethod.getReturnType();
        // if (! Collection.class.isAssignableFrom(returnType))
        // return null;
        Object returnValue = getListenersMethod.invoke(instance);
        return (Collection<?>) returnValue;
    }

    @Override
    public boolean addListener(Object instance, Object listener)
            throws ReflectiveOperationException {
        Method addListenerMethod = getAddListenerMethod();
        if (addListenerMethod == null)
            return false;
        addListenerMethod.invoke(instance, listener);
        return true;
    }

    @Override
    public boolean removeListener(Object instance, Object listener)
            throws ReflectiveOperationException {
        Method removeListenerMethod = getRemoveListenerMethod();
        if (removeListenerMethod == null)
            return false;
        removeListenerMethod.invoke(instance, listener);
        return true;
    }

    protected Method getAddListenerMethod()
            throws ReflectiveOperationException {
        String methodName = "add" + ucfirstName + "Listener";
        try {
            return getDeclaringClass().getMethod(methodName, getListenerClass());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    protected Method getRemoveListenerMethod()
            throws ReflectiveOperationException {
        String methodName = "remove" + ucfirstName + "Listener";
        try {
            return getDeclaringClass().getMethod(methodName, getListenerClass());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    protected Method getGetListenersMethod()
            throws ReflectiveOperationException {
        String methodName = "get" + ucfirstName + "Listeners";
        try {
            return getDeclaringClass().getMethod(methodName, getListenerClass());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

}
