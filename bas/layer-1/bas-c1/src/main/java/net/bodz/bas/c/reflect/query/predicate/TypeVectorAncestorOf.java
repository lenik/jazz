package net.bodz.bas.c.reflect.query.predicate;

public class TypeVectorAncestorOf
        extends TypeVectorEquals {

    public TypeVectorAncestorOf(Class<?>[] endTypeVector, boolean matchPrefixOnly) {
        super(endTypeVector, matchPrefixOnly);
    }

    public TypeVectorAncestorOf(Class<?>[] endTypeVector, boolean matchPrefixOnly, ITypeVectorPredicate next) {
        super(endTypeVector, matchPrefixOnly, next);
    }

    @Override
    protected boolean compareTypeNonNull(Class<?> endTypePattern, Class<?> tShouldBeAncestor) {
        return tShouldBeAncestor.isAssignableFrom(endTypePattern);
    }

}
