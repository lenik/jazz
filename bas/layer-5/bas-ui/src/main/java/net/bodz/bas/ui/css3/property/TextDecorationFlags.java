package net.bodz.bas.ui.css3.property;

import java.io.Serializable;

public class TextDecorationFlags
        implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Each line of text is underlined. */
    boolean underline;

    /** Each line of text has a line above it. */
    boolean overline;

    /** Each line of text has a line through the middle. */
    boolean lineThrough;

    /**
     * Text blinks (alternates between visible and invisible). Conforming user agents may simply not
     * blink the text. Note that not blinking the text is one technique to satisfy checkpoint 3.3 of
     * WAI-UAAG.
     */
    boolean blink;

    @Override
    public TextDecorationFlags clone() {
        TextDecorationFlags o = new TextDecorationFlags();
        o.underline = underline;
        o.overline = overline;
        o.lineThrough = lineThrough;
        o.blink = blink;
        return o;
    }

    public boolean isUnderline() {
        return underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public boolean isOverline() {
        return overline;
    }

    public void setOverline(boolean overline) {
        this.overline = overline;
    }

    public boolean isLineThrough() {
        return lineThrough;
    }

    public void setLineThrough(boolean lineThrough) {
        this.lineThrough = lineThrough;
    }

    public boolean isBlink() {
        return blink;
    }

    public void setBlink(boolean blink) {
        this.blink = blink;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        if (underline)
            buf.append("underline ");
        if (overline)
            buf.append("overline ");
        if (lineThrough)
            buf.append("line-through ");
        if (blink)
            buf.append("blink ");
        if (buf.length() != 0)
            buf.setLength(buf.length() - 1);
        return buf.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != TextDecorationFlags.class)
            return false;
        TextDecorationFlags o = (TextDecorationFlags) obj;
        if (underline != o.underline)
            return false;
        if (overline != o.overline)
            return false;
        if (lineThrough != o.lineThrough)
            return false;
        if (blink != o.blink)
            return false;
        return true;
    }

}
