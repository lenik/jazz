package net.bodz.bas.ctx.scope;

public interface IScopeTeller {

    /**
     * The same as {@link #tell(IScopeToken)} with fallback set to <code>null</code>.
     */
    IScopeToken tell();

}
