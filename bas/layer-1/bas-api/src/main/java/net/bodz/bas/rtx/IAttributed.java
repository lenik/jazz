package net.bodz.bas.rtx;

public interface IAttributed {

    /**
     * @return <code>null</code> if specified attribute doesn't exist.
     */
    default <T> T getAttribute(String name) {
        return getAttribute(name, null);
    }

    <T> T getAttribute(String name, T defaultValue);

//    void setAttribute(String name, Object value);

}
