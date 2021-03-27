package net.bodz.bas.fmt.api;

public interface ITextAttribute {
    /**
     * Get the attribute name.
     * 
     * @return Non-<code>null</code> attribute name.
     */
    String getName();

    /**
     * Get the attribute data.
     * 
     * @return Non-<code>null</code> attribute data.
     */
    String getData();

}
