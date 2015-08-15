package net.bodz.bas.potato.ref;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.t.tree.IPathInfo;

public class PropertyRefEntry<T>
        extends AbstractRefEntry<T>
        implements IValueChangeSource {

    private static final long serialVersionUID = 1L;

    private IRefEntry<?> instanceRef;
    private IProperty property;

    public PropertyRefEntry(IRefEntry<?> instanceRef, IProperty property) {
        super(property, property);
        this.instanceRef = instanceRef;
        this.property = property;
    }

    @Override
    public IProperty getWrapped() {
        return (IProperty) super.getWrapped();
    }

    public IProperty getProperty() {
        return property;
    }

    @Override
    public Class<? extends T> getValueType() {
        return (Class<? extends T>) property.getPropertyType();
    }

    static int callDepth = 0;

    @Override
    public T get() {
        if (!property.isReadable())
            throw new IllegalUsageException("Property isn't readable: " + property);

        if (callDepth > 10)
            throw new StackOverflowError();

        callDepth++;
        try {
            Object instance = instanceRef.get();
            T value = (T) property.getValue(instance);
            return value;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            callDepth--;
        }
    }

    @Override
    public void set(T value) {
        if (!property.isWritable())
            throw new IllegalUsageException("Property isn't writable: " + property);

        Object instance = instanceRef.get();
        try {
            property.setValue(instance, value);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /** ⇱ Implementation Of {@link IPathInfo}. */
    /* _____________________________ */static section.iface __PATH_INFO__;

    @Override
    public IPathInfo getParent() {
        return instanceRef;
    }

    @Override
    public String getLocalPath() {
        return getName();
    }

    /** ⇱ Implementation Of {@link IQueryable}. */
    /* _____________________________ */static section.iface __QUERYABLE__;

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationType)
            throws QueryException {
        if (specificationType == IValueChangeSource.class) {
            if (property.isPropertyChangeSource())
                return specificationType.cast(this);
            else
                return null;
        }
        return super.query(specificationType);
    }

    @Override
    public void addValueChangeListener(IValueChangeListener listener) {
        ValueOfPropertyChangeListener _listener = new ValueOfPropertyChangeListener(listener);
        property.addPropertyChangeListener(instanceRef.get(), property.getName(), _listener);
    }

    @Override
    public void removeValueChangeListener(IValueChangeListener listener) {
        ValueOfPropertyChangeListener _listener = new ValueOfPropertyChangeListener(listener);
        property.removePropertyChangeListener(listener, property.getName(), _listener);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String name = property.getName();
        Object value = get();
        sb.append(name);
        sb.append(" = ");
        sb.append(value);

        iString description = property.getDescription();
        if (!iString.fn.isEmpty(description)) {
            sb.append("  /* ");
            sb.append(description);
            sb.append(" */");
        }
        return sb.toString();
    }

}
