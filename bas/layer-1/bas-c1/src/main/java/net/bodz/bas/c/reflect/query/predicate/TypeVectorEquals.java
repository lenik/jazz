package net.bodz.bas.c.reflect.query.predicate;

public class TypeVectorEquals
        extends _TypeVectorPredicate {

    private final Class<?>[] pattern;
    private final boolean matchPrefixOnly;

    public TypeVectorEquals(Class<?>[] pattern, boolean matchPrefixOnly) {
        this(pattern, matchPrefixOnly, null);
    }

    public TypeVectorEquals(Class<?>[] pattern, boolean matchPrefixOnly, ITypeVectorPredicate next) {
        super(next);
        if (pattern == null)
            throw new NullPointerException("pattern");
        this.pattern = pattern;
        this.matchPrefixOnly = matchPrefixOnly;
    }

    @Override
    protected boolean _evaluate(Class<?>[] tv) {
        if (tv.length < pattern.length)
            return false;

        if (tv.length != pattern.length && !matchPrefixOnly)
            return false;

        for (int i = 0; i < pattern.length; i++) {
            if (!compareType(pattern[i], tv[i]))
                return false;
        }

        return true;
    }

}
