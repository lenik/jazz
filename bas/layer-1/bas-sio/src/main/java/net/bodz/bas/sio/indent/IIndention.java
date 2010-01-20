package net.bodz.bas.sio.indent;

public interface IIndention {

    int getIndentLevel();

    void setIndentLevel(int indentLevel);

    void increaseIndentLevel();

    void decreaseIndentLevel();

}
