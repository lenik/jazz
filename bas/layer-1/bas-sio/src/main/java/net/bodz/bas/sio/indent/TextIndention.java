package net.bodz.bas.sio.indent;

public class TextIndention
        implements ITextIndention {

    private int indentSize = 4;
    private int tabSize = 8;
    private boolean mixedMode = false;

    @Override
    public int getIndentSize() {
        return indentSize;
    }

    /**
     * @throws IllegalArgumentException
     *             If <code>indentSize</code> is negative.
     */
    @Override
    public void setIndentSize(int indentSize) {
        if (indentSize < 0)
            throw new IllegalArgumentException("indentSize is negative: " + indentSize);
        this.indentSize = indentSize;
    }

    @Override
    public int getTabSize() {
        return tabSize;
    }

    /**
     * @throws IllegalArgumentException
     *             If <code>tabSize</code> is negative.
     */
    @Override
    public void setTabSize(int tabSize) {
        if (tabSize < 0)
            throw new IllegalArgumentException("tabSize is negative: " + tabSize);
        this.tabSize = tabSize;
    }

    @Override
    public boolean isMixedMode() {
        return mixedMode;
    }

    @Override
    public void setMixedMode(boolean mixedMode) {
        this.mixedMode = mixedMode;
    }

    private int indentLevel;
    private String linePrefix = "";

    @Override
    public int getIndentLevel() {
        return indentLevel;
    }

    /**
     * @throws IllegalArgumentException
     *             If <code>indentLevel</code> is negative.
     */
    @Override
    public void setIndentLevel(int indentLevel) {
        if (indentLevel < 0)
            throw new IllegalArgumentException("indentLevel is negative: " + indentLevel);
        this.indentLevel = indentLevel;
    }

    @Override
    public void increaseIndentLevel() {
        indentLevel++;
    }

    @Override
    public void decreaseIndentLevel() {
        indentLevel--;
    }

    @Override
    public String getCurrentLinePrefix() {
        return linePrefix;
    }

    @Override
    public void setCurrentLinePrefix(String linePrefix) {
        this.linePrefix = linePrefix;
    }

}
