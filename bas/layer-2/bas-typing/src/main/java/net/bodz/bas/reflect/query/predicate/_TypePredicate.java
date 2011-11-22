package net.bodz.bas.reflect.query.predicate;

public abstract class _TypePredicate
        implements ITypePredicate {

    private final ITypePredicate next;

    public _TypePredicate() {
        this.next = null;
    }

    public _TypePredicate(ITypePredicate next) {
        this.next = next;
    }

    @Override
    public final boolean evaluate(Class<?> t) {
        if (!_evaluate(t))
            return false;
        if (next == null)
            return true;
        return next.evaluate(t);
    }

    protected abstract boolean _evaluate(Class<?> t);

}
