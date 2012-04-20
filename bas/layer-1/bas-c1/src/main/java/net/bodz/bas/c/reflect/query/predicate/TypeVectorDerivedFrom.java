package net.bodz.bas.c.reflect.query.predicate;

public class TypeVectorDerivedFrom
        extends TypeVectorEquals {

    public TypeVectorDerivedFrom(Class<?>[] ancestorTypeVectorPattern, boolean matchPrefixOnly) {
        super(ancestorTypeVectorPattern, matchPrefixOnly);
    }

    public TypeVectorDerivedFrom(Class<?>[] ancestorTypeVectorPattern, boolean matchPrefixOnly,
            ITypeVectorPredicate next) {
        super(ancestorTypeVectorPattern, matchPrefixOnly, next);
    }

    @Override
    protected boolean compareTypeNonNull(Class<?> ancestorTypePattern, Class<?> tShouldBeChild) {
        return ancestorTypePattern.isAssignableFrom(tShouldBeChild);
    }

}