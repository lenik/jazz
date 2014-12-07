package net.bodz.bas.typer.std;

public interface IMutableAttributes
        extends IAttributes {

    void setAttribute(String name, Object value);

    void removeAttribute(String name);

    void setAttributeTypers(String name, ITyperFamily<?> typers);

}
