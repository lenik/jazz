package net.bodz.bas.gui.style;

import net.bodz.bas.gui.css3.ICss3StyleDeclaration;
import net.bodz.bas.gui.css3.property.FontSizeType;
import net.bodz.bas.gui.css3.property.FontStyleMode;
import net.bodz.bas.gui.css3.property.FontUseMode;
import net.bodz.bas.gui.css3.property.FontVariantMode;
import net.bodz.bas.gui.css3.property.FontWeightMode;
import net.bodz.bas.gui.css3.property.TextDecorationFlags;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public class FontType
        implements IFontType {

    private static final long serialVersionUID = 1L;

    FontUseMode fontUse;

    String fontFamily;

    FontSizeType fontSizeType;
    LengthMeasure fontSize;

    FontStyleMode fontStyle;
    FontWeightMode fontWeight;

    FontVariantMode fontVariant;
    TextDecorationFlags textDecoration;

    public FontType() {
    }

    public FontType(ICss3StyleDeclaration styleDecl) {
        if (styleDecl == null)
            throw new NullPointerException("styleDecl");
        fontFamily = styleDecl.getFontFamily();
        fontSizeType = styleDecl.getFontSizeType();
        fontSize = styleDecl.getFontSize();
        fontStyle = styleDecl.getFontStyle();
        fontWeight = styleDecl.getFontWeight();
        fontVariant = styleDecl.getFontVariant();
        textDecoration = styleDecl.getTextDecoration();
    }

    @Override
    public FontUseMode getFontUse() {
        return fontUse;
    }

    @Override
    public String getFontFamily() {
        return fontFamily;
    }

    @Override
    public FontSizeType getFontSizeType() {
        return fontSizeType;
    }

    @Override
    public LengthMeasure getFontSize() {
        return fontSize;
    }

    @Override
    public FontStyleMode getFontStyle() {
        return fontStyle;
    }

    @Override
    public FontWeightMode getFontWeight() {
        return fontWeight;
    }

    @Override
    public FontVariantMode getFontVariant() {
        return fontVariant;
    }

    @Override
    public TextDecorationFlags getTextDecoration() {
        return textDecoration;
    }

}
