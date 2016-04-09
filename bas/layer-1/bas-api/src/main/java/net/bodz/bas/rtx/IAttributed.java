package net.bodz.bas.rtx;

public interface IAttributed {

    <T> T getAttribute(String name);

    void setAttribute(String name, Object value);

}
