package net.bodz.bas.reflect.query.predicate;

public abstract class _StringPredicate
        implements IStringPredicate {

    private final IStringPredicate next;

    public _StringPredicate() {
        this.next = null;
    }

    public _StringPredicate(IStringPredicate next) {
        this.next = next;
    }

    @Override
    public final boolean evaluate(String s) {
        if (!_evaluate(s))
            return false;
        if (next == null)
            return true;
        return next.evaluate(s);
    }

    protected abstract boolean _evaluate(String s);

}
