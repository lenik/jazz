package net.bodz.bas.ctx.scope;

public interface IScopeTeller {

    /**
     * The same as {@link #tell(IScopeInstance)} with fallback set to <code>null</code>.
     */
    IScopeInstance tell();

    String tellId();

    StaticScopeTeller STATIC = new StaticScopeTeller();

}
