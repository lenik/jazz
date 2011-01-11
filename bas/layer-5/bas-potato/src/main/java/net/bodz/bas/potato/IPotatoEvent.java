package net.bodz.bas.potato;

import java.util.Collection;

public interface IPotatoEvent
        extends IPotatoElement {

    IPotatoType<?> getDeclaringType();

    Class<?> getEventClass();

    Class<?> getListenerClass();

    /**
     * @return Empty collection if no observer registered.
     */
    Collection<?> getListeners(Object instance)
            throws PotatoException, PotatoTargetException;

    void addListener(Object instance, Object listener)
            throws PotatoException, PotatoTargetException;

    void removeListener(Object instance, Object listener)
            throws PotatoException, PotatoTargetException;

}
