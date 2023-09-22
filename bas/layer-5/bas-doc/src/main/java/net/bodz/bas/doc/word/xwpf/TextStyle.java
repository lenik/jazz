package net.bodz.bas.doc.word.xwpf;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STVerticalAlignRun;

public class TextStyle {

    public Boolean bold;
    public Boolean captialized;
    public Integer charSpacing; // twips
    public String color;
    public Boolean doubleStrikethrough;
    public String fontFamily;
    public Number fontSize;
    public boolean fontSizeInt;
    public Boolean italic;
    public Boolean shadow;
    public Boolean smallCaps;
    public String style;
    public VerticalAlign subscript;
    public Boolean strikeThrough;
    public UnderlinePatterns underline;
    public String underlineColor;
    public STVerticalAlignRun.Enum verticalAlignment;

    public TextStyle() {
    }

    public TextStyle(TextStyle o) {
        this.bold = o.bold;
        this.captialized = o.captialized;
        this.charSpacing = o.charSpacing;
        this.color = o.color;
        this.doubleStrikethrough = o.doubleStrikethrough;
        this.fontFamily = o.fontFamily;
        this.fontSize = o.fontSize;
        this.fontSizeInt = o.fontSizeInt;
        this.italic = o.italic;
        this.shadow = o.shadow;
        this.smallCaps = o.smallCaps;
        this.style = o.style;
        this.subscript = o.subscript;
        this.strikeThrough = o.strikeThrough;
        this.underline = o.underline;
        this.underlineColor = o.underlineColor;
    }

    @Override
    public TextStyle clone() {
        return new TextStyle(this);
    }

    public void readObject(XWPFRun run) {
        bold = run.isBold();
        captialized = run.isCapitalized();
        charSpacing = run.getCharacterSpacing();
        color = run.getColor();
        doubleStrikethrough = run.isDoubleStrikeThrough();
        fontFamily = run.getFontFamily();
        fontSize = run.getFontSizeAsDouble();
        fontSizeInt = false;

        italic = run.isItalic();
        shadow = run.isShadowed();
        smallCaps = run.isSmallCaps();
        style = run.getStyle();
        // subscript=run.getSubscript();
        strikeThrough = run.isStrikeThrough();
        underline = run.getUnderline();
        underlineColor = run.getUnderlineColor();
        verticalAlignment = run.getVerticalAlignment();
    }

    public void apply(XWPFRun run) {
        if (bold != null)
            run.setBold(bold);
        if (captialized != null)
            run.setCapitalized(captialized);
        if (charSpacing != null)
            run.setCharacterSpacing(charSpacing);
        if (color != null)
            run.setColor(color);
        if (doubleStrikethrough != null)
            run.setDoubleStrikethrough(doubleStrikethrough);
        if (fontFamily != null)
            run.setFontFamily(fontFamily);
        if (fontSize != null)
            if (fontSizeInt)
                run.setFontSize(fontSize.intValue());
            else
                run.setFontSize(fontSize.doubleValue());
        if (italic != null)
            run.setItalic(italic);
        if (shadow != null)
            run.setShadow(shadow);
        if (smallCaps != null)
            run.setSmallCaps(smallCaps);
        if (style != null)
            run.setStyle(style);
        if (subscript != null)
            run.setSubscript(subscript);
        if (strikeThrough != null)
            run.setStrikeThrough(strikeThrough);
        if (underline != null)
            run.setUnderline(underline);
        if (underlineColor != null)
            run.setUnderlineColor(underlineColor);
        if (verticalAlignment != null)
            run.setVerticalAlignment(verticalAlignment.toString());
    }

}
