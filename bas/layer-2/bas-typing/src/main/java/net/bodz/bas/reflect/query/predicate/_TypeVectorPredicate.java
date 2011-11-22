package net.bodz.bas.reflect.query.predicate;

public abstract class _TypeVectorPredicate
        implements ITypeVectorPredicate {

    private final ITypeVectorPredicate next;

    public _TypeVectorPredicate() {
        this.next = null;
    }

    public _TypeVectorPredicate(ITypeVectorPredicate next) {
        this.next = next;
    }

    @Override
    public final boolean evaluate(Class<?>[] tv) {
        if (!_evaluate(tv))
            return false;
        if (next == null)
            return true;

        // int pc = getParameterCount();
        // Class<?>[] chopped = Arrays.copyOfRange(tv, pc, tv.length - pc);
        // tv=chopped;

        return next.evaluate(tv);
    }

    protected abstract boolean _evaluate(Class<?>[] tv);

    protected boolean compareType(Class<?> pattern, Class<?> t) {
        if (pattern == null || t == null)
            return true;
        return compareTypeNonNull(pattern, t);
    }

    protected boolean compareTypeNonNull(Class<?> pattern, Class<?> t) {
        if (pattern == null || t == null)
            return true;
        return pattern.equals(t);
    }

}
