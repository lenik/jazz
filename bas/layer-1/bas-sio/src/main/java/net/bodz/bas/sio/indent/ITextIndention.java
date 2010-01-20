package net.bodz.bas.sio.indent;

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

}
