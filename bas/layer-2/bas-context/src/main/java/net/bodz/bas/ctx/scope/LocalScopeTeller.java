package net.bodz.bas.ctx.scope;

public class LocalScopeTeller
        implements IScopeTeller {

    @Override
    public IScopeInstance tell() {
        return new LocalScopeInstance();
    }

    public static LocalScopeTeller INSTANCE = new LocalScopeTeller();

}
