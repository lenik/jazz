package net.bodz.bas.rtx;

public interface IAttributed {

    <T> T getAttribute(String name);

    <T> T getAttribute(String name, T defaultValue);

    void setAttribute(String name, Object value);

}
