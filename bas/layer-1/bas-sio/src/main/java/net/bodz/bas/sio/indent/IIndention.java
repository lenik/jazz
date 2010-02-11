package net.bodz.bas.sio.indent;

public interface IIndention {

    int getIndentLevel();

    /**
     * @throws IllegalArgumentException
     *             If <code>indentLevel</code> is negative.
     */
    void setIndentLevel(int indentLevel);

    void increaseIndentLevel();

    /**
     * @throws IllegalStateException
     *             If can't unindent any more.
     */
    void decreaseIndentLevel();

}
