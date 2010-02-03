package net.bodz.bas.potato.adapter.reflect;

import java.util.Collection;

import net.bodz.bas.potato.AbstractPotatoEvent;
import net.bodz.bas.potato.IPotatoType;

public class ReflectPotatoEvent
        extends AbstractPotatoEvent {

    private final ReflectPotatoType<?> declaringPotatoType;

    /**
     * @throws NullPointerException
     *             If <code>declaringPotatoType</code> or <code>eventSetDescriptor</code> is
     *             <code>null</code>.
     */
    public ReflectPotatoEvent(ReflectPotatoType<?> declaringPotatoType, String name) {
        super(name);

        if (declaringPotatoType == null)
            throw new NullPointerException("declaringPotatoType");
        this.declaringPotatoType = declaringPotatoType;
    }

    @Override
    public IPotatoType<?> getDeclaringType() {
        return declaringPotatoType;
    }

    @Override
    public void addListener(Object instance, Object listener) {
    }

    @Override
    public Class<?> getEventClass() {
        return null;
    }

    @Override
    public Class<?> getListenerClass() {
        return null;
    }

    @Override
    public Collection<?> getListeners(Object instance) {
        return null;
    }

    @Override
    public void removeListener(Object instance, Object listener) {
    }

}
