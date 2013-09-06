package net.bodz.bas.io;

interface IIndention {

    int getIndentLevel();

    /**
     * @throws IllegalArgumentException
     *             If <code>indentLevel</code> is negative.
     */
    void setIndentLevel(int indentLevel);

    /**
     * @return indent-level after increased.
     */
    int increaseIndentLevel();

    /**
     * @throws IllegalStateException
     *             If can't unindent any more.
     * @return indent-level after decreased.
     */
    int decreaseIndentLevel();

}

public interface ITextIndention
        extends IIndention {

    int getIndentSize();

    void setIndentSize(int indentSize);

    int getTabSize();

    void setTabSize(int tabSize);

    /**
     * Mix tabs and spaces, replace spaces by count of <code>tabSize</code> with a tab-stop.
     */
    boolean isMixedMode();

    /**
     * Set <code>true</code> to replace spaces by count of <code>tabSize</code> with a tab-stop.
     * 
     * @param mixedMode
     */
    void setMixedMode(boolean mixedMode);

    String getCurrentLinePrefix();

    void setCurrentLinePrefix(String currentLinePrefix);

    ITextIndention NULL = new NullTextIndention();

}

class NullTextIndention
        implements ITextIndention {

    @Override
    public int getIndentLevel() {
        return 0;
    }

    @Override
    public void setIndentLevel(int indentLevel) {
    }

    @Override
    public int increaseIndentLevel() {
        return 0;
    }

    @Override
    public int decreaseIndentLevel() {
        return 0;
    }

    @Override
    public int getIndentSize() {
        return 0;
    }

    @Override
    public void setIndentSize(int indentSize) {
    }

    @Override
    public int getTabSize() {
        return 0;
    }

    @Override
    public void setTabSize(int tabSize) {
    }

    @Override
    public boolean isMixedMode() {
        return false;
    }

    @Override
    public void setMixedMode(boolean mixedMode) {
    }

    @Override
    public String getCurrentLinePrefix() {
        return null;
    }

    @Override
    public void setCurrentLinePrefix(String currentLinePrefix) {
    }

}