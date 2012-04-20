package net.bodz.bas.c.reflect.query.predicate;

/**
 * The actual type should be derived from the pattern.
 */
public class TypeDerivedFrom
        extends _TypePredicate {

    private final Class<?> pattern;

    public TypeDerivedFrom(Class<?> parentTypePattern, ITypePredicate next) {
        super(next);
        if (parentTypePattern == null)
            throw new NullPointerException("pattern");
        this.pattern = parentTypePattern;
    }

    @Override
    protected boolean _evaluate(Class<?> tShouldBeChild) {
        return pattern.isAssignableFrom(tShouldBeChild);
    }

}
