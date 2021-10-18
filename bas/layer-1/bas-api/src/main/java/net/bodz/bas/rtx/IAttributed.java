package net.bodz.bas.rtx;

public interface IAttributed {

    default <T> T getAttribute(String name) {
        return getAttribute(name, null);
    }

    <T> T getAttribute(String name, T defaultValue);

    void setAttribute(String name, Object value);

}
