package net.bodz.bas.context;

public interface IContextResolver {

    /**
     * The same as {@link #resolve(IContextId)} with fallback set to <code>null</code>.
     */
    IContextId resolve();

    IContextId resolve(IContextId fallback);

}
