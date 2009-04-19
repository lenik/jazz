package net.bodz.bas.ant;

public class TextElement {

    private StringBuffer buf;

    public TextElement() {
        buf = new StringBuffer();
    }

    public void addText(String s) {
        buf.append(s);
    }

    public String getText() {
        return buf.toString();
    }

    @Override
    public String toString() {
        return getText();
    }

}
