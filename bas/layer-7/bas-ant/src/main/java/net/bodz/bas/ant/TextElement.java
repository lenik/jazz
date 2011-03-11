package net.bodz.bas.ant;

/**
 * Value attribute will overwrite any text.
 */
public class TextElement {

    private String value;
    private StringBuilder buf;

    public TextElement() {
        buf = new StringBuilder();
    }

    public void setValue(String s) {
        this.value = s;
    }

    public void addText(String s) {
        buf.append(s);
    }

    public String getText() {
        if (value != null)
            return value;
        return buf.toString();
    }

    @Override
    public String toString() {
        return getText();
    }

}
