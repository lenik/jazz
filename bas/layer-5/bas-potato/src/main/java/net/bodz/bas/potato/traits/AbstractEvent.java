package net.bodz.bas.potato.traits;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.NoSuchMethodException;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.string.Strings;

public abstract class AbstractEvent
        extends AbstractMember
        implements IEvent {

    private final String ucfirstName;

    private final Class<?> listenerClass;
    private final Class<?> eventClass;

    public AbstractEvent(Class<?> declaringType, String eventName, Class<?> listenerClass, Class<?> eventClass) {
        super(declaringType, eventName);
        if (eventName == null)
            throw new NullPointerException("eventName");
        if (eventName.isEmpty())
            throw new IllegalArgumentException("eventName is empty");
        ucfirstName = Strings.ucfirst(eventName);

        if (listenerClass == null)
            throw new NullPointerException("listenerClass");
        if (eventClass == null)
            throw new NullPointerException("eventClass");
        this.listenerClass = listenerClass;
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
        Object returnValue = Jdk7Reflect.invoke(getListenersMethod, instance);
        return (Collection<?>) returnValue;
    }

    @Override
    public boolean addListener(Object instance, Object listener)
            throws ReflectiveOperationException {
        Method addListenerMethod = getAddListenerMethod();
        if (addListenerMethod == null)
            return false;
        Jdk7Reflect.invoke(addListenerMethod, instance, listener);
        return true;
    }

    @Override
    public boolean removeListener(Object instance, Object listener)
            throws ReflectiveOperationException {
        Method removeListenerMethod = getRemoveListenerMethod();
        if (removeListenerMethod == null)
            return false;
        Jdk7Reflect.invoke(removeListenerMethod, instance, listener);
        return true;
    }

    protected Method getAddListenerMethod()
            throws ReflectiveOperationException {
        String methodName = "add" + ucfirstName + "Listener";
        try {
            return Jdk7Reflect.getMethod(getDeclaringType(), methodName, getListenerClass());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    protected Method getRemoveListenerMethod()
            throws ReflectiveOperationException {
        String methodName = "remove" + ucfirstName + "Listener";
        try {
            return Jdk7Reflect.getMethod(getDeclaringType(), methodName, getListenerClass());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    protected Method getGetListenersMethod()
            throws ReflectiveOperationException {
        String methodName = "get" + ucfirstName + "Listeners";
        try {
            return Jdk7Reflect.getMethod(getDeclaringType(), methodName, getListenerClass());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

}
