package net.bodz.bas.context;

import net.bodz.bas.t.ref.Var;

public class IndirectContextTeller
        extends Var<IContextTeller>
        implements IContextTeller {

    private static final long serialVersionUID = 1L;

    public IndirectContextTeller() {
        super(IContextTeller.class, new CurrentThreadContextTeller());
    }

    @Override
    public IContext tell() {
        IContextTeller teller = get();
        return teller.tell();
    }

}
