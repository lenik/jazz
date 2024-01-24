package net.bodz.lily.concrete.util;

import net.bodz.bas.c.string.Strings;

public class TextObject {

    String text;
    int previewLength = 50;

    public TextObject(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getPreview() {
        return Strings.ellipsis(getText(), previewLength);
    }

    @Override
    public String toString() {
        return text;
    }

}
