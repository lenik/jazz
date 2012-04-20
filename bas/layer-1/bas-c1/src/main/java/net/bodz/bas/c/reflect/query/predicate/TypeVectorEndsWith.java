package net.bodz.bas.c.reflect.query.predicate;

public class TypeVectorEndsWith
        extends _TypeVectorPredicate {

    private final Class<?>[] pattern;

    public TypeVectorEndsWith(Class<?>[] pattern) {
        this(pattern, null);
    }

    public TypeVectorEndsWith(Class<?>[] pattern, ITypeVectorPredicate next) {
        super(next);
        if (pattern == null)
            throw new NullPointerException("suffix");
        this.pattern = pattern;
    }

    @Override
    protected boolean _evaluate(Class<?>[] tv) {
        int offset = tv.length - pattern.length;
        if (offset < 0)
            return false;

        for (int i = 0; i < pattern.length; i++) {
            if (!compareType(pattern[i], tv[offset + i]))
                return false;
        }
        return true;
    }

}
