package net.bodz.bas.ctx.scope;

import net.bodz.bas.ctx.scope.id.CurrentThreadScopeTeller;
import net.bodz.bas.ctx.scope.id.IScopeDescriptor;
import net.bodz.bas.t.ref.SimpleVar;

public class IndirectScopeTeller
        extends SimpleVar<IScopeTeller>
        implements IScopeTeller {

    private static final long serialVersionUID = 1L;

    public IndirectScopeTeller() {
        super(IScopeTeller.class, new CurrentThreadScopeTeller());
    }

    @Override
    public IScopeDescriptor tell() {
        IScopeTeller teller = get();
        return teller.tell();
    }

}
