package net.bodz.bas.reflect.query.predicate;

public class TypeAncestorOf
        extends _TypePredicate {

    private final Class<?> pattern;

    public TypeAncestorOf(Class<?> endTypePattern) {
        this(endTypePattern, null);
    }

    public TypeAncestorOf(Class<?> endTypePattern, ITypePredicate next) {
        super(next);
        if (endTypePattern == null)
            throw new NullPointerException("pattern");
        this.pattern = endTypePattern;
    }

    @Override
    protected boolean _evaluate(Class<?> tShouldBeAncestor) {
        return tShouldBeAncestor.isAssignableFrom(pattern);
    }

}
