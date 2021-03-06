package net.bodz.bas.ctx.scope;

public class StaticScopeTeller
        extends AbstractScopeTeller {

    @Override
    public IScopeInstance tell() {
        return IScopeInstance.STATIC;
    }

    @Override
    public String tellId() {
        return "<static>";
    }

}
