package net.bodz.bas.rtx;

public interface IMutableAttributes
        extends
            IAttributes,
            IAttributed {

    void setAttribute(String name, Object value);

    void removeAttribute(String name);

}