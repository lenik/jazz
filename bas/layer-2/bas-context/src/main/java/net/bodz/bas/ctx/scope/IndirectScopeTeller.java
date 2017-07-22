package net.bodz.bas.ctx.scope;

import net.bodz.bas.t.ref.SimpleVar;

public class IndirectScopeTeller
        extends SimpleVar<IScopeTeller>
        implements IScopeTeller {

    private static final long serialVersionUID = 1L;

    public IndirectScopeTeller() {
        super(IScopeTeller.class, new ThreadScopeTeller());
    }

    @Override
    public IScopeInstance tell() {
        IScopeTeller teller = get();
        return teller.tell();
    }

    @Override
    public String tellId() {
        IScopeTeller teller = get();
        return teller.tellId();
    }

}
