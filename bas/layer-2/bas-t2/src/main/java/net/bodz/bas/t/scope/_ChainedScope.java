package net.bodz.bas.t.scope;

public abstract class _ChainedScope<T>
        implements ChainedScope<T> {

    protected T head;

    public _ChainedScope(T start) {
        this.head = start;
    }

    public _ChainedScope() {
        this.head = create();
    }

    protected abstract T create();

    protected abstract T link(T _this, T next);

    @Override
    public T current() {
        return head;
    }

    @Override
    public void enterNew() {
        enter(create());
    }

    @Override
    public final void enter(T scope) {
        head = link(scope, head);
    }

    @Override
    public void leave() {
        if (!(head instanceof SLink<?>))
            throw new IllegalStateException("end of link");
        @SuppressWarnings("unchecked")
        SLink<T> link = (SLink<T>) head;
        head = link.getNext();
    }

    @Override
    public String toString() {
        return String.valueOf(head);
    }

}
