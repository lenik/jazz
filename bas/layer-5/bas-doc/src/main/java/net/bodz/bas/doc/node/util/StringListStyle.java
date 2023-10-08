package net.bodz.bas.doc.node.util;

public class StringListStyle
        implements
            IListStyle {

    final String chars;

    public StringListStyle(String chars) {
        if (chars == null)
            throw new NullPointerException("chars");
        this.chars = chars;
    }

    @Override
    public String format(int number, int max) {
        return chars;
    }

    @Override
    public boolean isOrdered() {
        return false;
    }

}
