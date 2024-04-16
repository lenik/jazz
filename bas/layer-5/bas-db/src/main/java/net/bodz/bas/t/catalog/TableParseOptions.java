package net.bodz.bas.t.catalog;

public class TableParseOptions {

    public boolean renameConflictColumns = true;
    public int renameTryMax = 100;

    public boolean autoType;
    public int maxSamples; // scan from the top

    public boolean trimText = true;

    public String trimText(String s) {
        if (s == null)
            return null;
        s = s.trim();
        return s;
    }

}
