package net.bodz.bas.potato.adapter.bean;

import java.beans.EventSetDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.potato.AbstractPotatoEvent;
import net.bodz.bas.potato.IPotatoType;
import net.bodz.bas.potato.PotatoException;
import net.bodz.bas.potato.PotatoTargetException;

public class BeanPotatoEvent
        extends AbstractPotatoEvent {

    private final BeanPotatoType<?> declaringPotatoType;
    private final EventSetDescriptor eventSetDescriptor;

    /**
     * @throws NullPointerException
     *             If <code>declaringPotatoType</code> or <code>eventSetDescriptor</code> is
     *             <code>null</code>.
     */
    public BeanPotatoEvent(BeanPotatoType<?> declaringPotatoType, EventSetDescriptor eventSetDescriptor) {
        super(eventSetDescriptor.getName());

        if (declaringPotatoType == null)
            throw new NullPointerException("declaringPotatoType");
        this.declaringPotatoType = declaringPotatoType;

        this.eventSetDescriptor = eventSetDescriptor;
    }

    @Override
    public IPotatoType<?> getDeclaringType() {
        return declaringPotatoType;
    }

    @Override
    public Class<?> getEventClass() {
        // return EventObject.class;
        return Object.class;
    }

    @Override
    public Class<?> getListenerClass() {
        return eventSetDescriptor.getListenerType();
    }

    /**
     * @return Empty {@link Collection} if no get listener method, or all listeners returned by the
     *         get listener methods are joined into a single collection.
     */
    @Override
    public Collection<?> getListeners(Object instance)
            throws PotatoTargetException {
        Method[] getListenerMethods = eventSetDescriptor.getListenerMethods();
        if (getListenerMethods.length == 0)
            return Collections.emptySet();

        List<Object> allListeners = new ArrayList<Object>();
        for (Method getListenerMethod : getListenerMethods) {
            try {
                Collection<?> listeners = (Collection<?>) Jdk7Reflect.invoke(getListenerMethod, instance);
                allListeners.addAll(listeners);
            } catch (ReflectiveOperationException e) {
                throw new PotatoTargetException(e.getMessage(), e);
            }
        }
        return allListeners;
    }

    @Override
    public void addListener(Object instance, Object listener)
            throws PotatoException, PotatoTargetException {
        Method addListenerMethod = eventSetDescriptor.getAddListenerMethod();
        if (addListenerMethod == null)
            throw new PotatoException("no add listener method");
        try {
            Jdk7Reflect.invoke(addListenerMethod, instance, listener);
        } catch (ReflectiveOperationException e) {
            throw new PotatoTargetException(e.getMessage(), e);
        }
    }

    @Override
    public void removeListener(Object instance, Object listener)
            throws PotatoException, PotatoTargetException {
        Method removeListenerMethod = eventSetDescriptor.getRemoveListenerMethod();
        if (removeListenerMethod == null)
            throw new PotatoException("no remove listener method");
        try {
            Jdk7Reflect.invoke(removeListenerMethod, instance, listener);
        } catch (ReflectiveOperationException e) {
            throw new PotatoTargetException(e.getMessage(), e);
        }
    }

}
