package net.bodz.bas.ui.style;

import net.bodz.bas.ui.css3.ICss3Length;
import net.bodz.bas.ui.css3.ICss3StyleDeclaration;
import net.bodz.bas.ui.css3.property.FontStyleMode;
import net.bodz.bas.ui.css3.property.FontUseMode;
import net.bodz.bas.ui.css3.property.FontVariantMode;
import net.bodz.bas.ui.css3.property.FontWeightMode;
import net.bodz.bas.ui.css3.property.TextDecorationFlags;

public class FontType
        implements IFontType {

    private static final long serialVersionUID = 1L;

    FontUseMode fontUse;
    String fontFamily;
    ICss3Length fontSize;
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
    public ICss3Length getFontSize() {
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
