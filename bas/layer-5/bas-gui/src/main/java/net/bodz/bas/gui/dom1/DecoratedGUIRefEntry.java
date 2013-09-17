package net.bodz.bas.gui.dom1;

import net.bodz.bas.rtx.QueryException;

public abstract class DecoratedGUIRefEntry<T>
        extends DecoratedGUIElement
        implements IGUIRefEntry<T> {

    private static final long serialVersionUID = 1L;

    protected DecoratedGUIRefEntry(IGUIRefEntry<T> _orig) {
        super(_orig);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IGUIRefEntry<T> getWrapped() {
        return (IGUIRefEntry<T>) _orig;
    }

    /** ⇱ Implementation Of {@link Ref}. */
    ;

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

    /** ⇱ Implementation Of {@link IRefEntry}. */
    ;

    @Override
    public Object query(Object specification)
            throws QueryException {
        return getWrapped().query(specification);
    }

    @Override
    public Object query(String specificationId)
            throws QueryException {
        return getWrapped().query(specificationId);
    }

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationType)
            throws QueryException {
        return getWrapped().query(specificationType);
    }

}
