package net.bodz.bas.potato;

import net.bodz.bas.lang.Ref;

public class PotatoPropertyRef
        implements Ref<Object> {

    private final IPotatoProperty potatoProperty;
    private final Object potato;

    /**
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    public PotatoPropertyRef(IPotatoProperty potatoProperty, Object potato) {
        if (potatoProperty == null)
            throw new NullPointerException("potatoProperty");
        if (potato == null)
            throw new NullPointerException("potato");
        this.potatoProperty = potatoProperty;
        this.potato = potato;
    }

    @Override
    public Object get() {
        try {
            return potatoProperty.get(potato);
        } catch (PotatoException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void set(Object value) {
        try {
            potatoProperty.set(potato, value);
        } catch (PotatoException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
