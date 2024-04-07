package net.bodz.bas.filetype.excel;

public class ExcelParseOptions {

    public boolean useRawText;

    public boolean ignoreEmptyRows = true;

    public boolean renameConflictColumns;
    public int renameTryMax = 100;

    public boolean autoType;
    public int maxSamples; // scan from the top

}
