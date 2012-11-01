package net.bodz.bas.potato.model;

import java.util.Collection;

public interface IEvent
        extends IElement {

    Class<?> getEventClass();

    Class<?> getListenerClass();

    // Collection<? extends IMethod> getListenerMethods();

    /**
     * @return <code>null</code> If can't get the listeners.
     */
    Collection<?> getListeners(Object instance)
            throws ReflectiveOperationException;

    /**
     * @return <code>false</code> If can't add the listener.
     */
    boolean addListener(Object instance, Object listener)
            throws ReflectiveOperationException;

    /**
     * @return <code>false</code> If can't remove the listener.
     */
    boolean removeListener(Object instance, Object listener)
            throws ReflectiveOperationException;

}
