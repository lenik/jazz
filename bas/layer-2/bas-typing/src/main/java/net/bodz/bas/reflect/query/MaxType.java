package net.bodz.bas.reflect.query;

public class MaxType
        implements ITypePredicate {

    private final Class<?> maxType;
    private final ITypePredicate next;

    public MaxType(Class<?> maxType) {
        this(maxType, null);
    }

    public MaxType(Class<?> maxType, ITypePredicate next) {
        if (maxType == null)
            throw new NullPointerException("maxType");
        this.maxType = maxType;
        this.next = next;
    }

    @Override
    public ITypePredicate next() {
        return next;
    }

    @Override
    public boolean test(Class<?> type) {
        if (!type.isAssignableFrom(maxType))
            return false;
        if (next != null && !next.test(type))
            return false;
        return true;
    }

}
