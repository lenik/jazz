package net.bodz.bas.t.ref;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedRef<T>
        extends AbstractDecorator<Ref<T>>
        implements Ref<T> {

    private static final long serialVersionUID = 1L;

    public DecoratedRef(Ref<T> _orig) {
        super(_orig);
    }

    @Override
    public Class<? extends T> getValueType() {
        return getWrapped().getValueType();
    }

    @Override
    public T get() {
        return getWrapped().get();
    }

    @Override
    public void set(T value) {
        getWrapped().set(value);
    }

    @Override
    public void remove() {
        getWrapped().remove();
    }

}
