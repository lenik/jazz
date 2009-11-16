package net.bodz.bas.types.chained;

import net.bodz.bas.lang.a.ChainUsage;
import net.bodz.bas.lang.a.OverrideOption;
import net.bodz.bas.nls.TypesNLS;

public abstract class _ChainedScope<T> implements ChainedScope<T> {

    protected T head;

    public _ChainedScope(T start) {
        this.head = start;
    }

    public _ChainedScope() {
        this.head = create();
    }

    @OverrideOption(chain = ChainUsage.FORBIDDEN)
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
            throw new IllegalStateException(TypesNLS.getString("_ChainedScope.endOfLink")); //$NON-NLS-1$
        @SuppressWarnings("unchecked")
        SLink<T> link = (SLink<T>) head;
        head = link.getNext();
    }

    @Override
    public String toString() {
        return String.valueOf(head);
    }

}
