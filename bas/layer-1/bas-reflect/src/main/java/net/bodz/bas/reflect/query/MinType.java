package net.bodz.bas.reflect.query;

public class MinType
        implements ITypePredicate {

    private final Class<?> minType;
    private final ITypePredicate next;

    public MinType(Class<?> minType, ITypePredicate next) {
        if (minType == null)
            throw new NullPointerException("minType");
        this.minType = minType;
        this.next = next;
    }

    @Override
    public ITypePredicate next() {
        return next;
    }

    @Override
    public boolean test(Class<?> type) {
        if (!minType.isAssignableFrom(type))
            return false;
        if (next != null && !next.test(type))
            return false;
        return true;
    }

}
