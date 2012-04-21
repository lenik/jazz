package net.bodz.bas.context;

public interface IContextResolver {

    /**
     * The same as {@link #resolve(IContext)} with fallback set to <code>null</code>.
     */
    IContext resolve();

    IContext resolve(IContext fallback);

}
