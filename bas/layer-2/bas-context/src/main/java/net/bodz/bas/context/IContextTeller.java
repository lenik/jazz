package net.bodz.bas.context;

public interface IContextTeller {

    /**
     * The same as {@link #tell(IContext)} with fallback set to <code>null</code>.
     */
    IContext tell();

}
