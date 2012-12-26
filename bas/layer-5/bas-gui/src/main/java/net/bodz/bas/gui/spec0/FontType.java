package net.bodz.bas.gui.spec0;

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
    FontStyleMode fontStyle;
    FontSizeType fontSizeType;
    LengthMeasure fontSize;
    FontVariantMode fontVariant;
    FontWeightMode fontWeight;
    TextDecorationFlags textDecoration;

    @Override
    public FontUseMode getFontUse() {
        return fontUse;
    }

    @Override
    public void setFontUse(FontUseMode fontUse) {
        this.fontUse = fontUse;
    }

    @Override
    public FontStyleMode getFontStyle() {
        return fontStyle;
    }

    @Override
    public void setFontStyle(FontStyleMode fontStyle) {
        this.fontStyle = fontStyle;
    }

    @Override
    public FontSizeType getFontSizeType() {
        return fontSizeType;
    }

    @Override
    public void setFontSizeType(FontSizeType fontSizeType) {
        this.fontSizeType = fontSizeType;
    }

    @Override
    public LengthMeasure getFontSize() {
        return fontSize;
    }

    @Override
    public void setFontSize(LengthMeasure fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public FontVariantMode getFontVariant() {
        return fontVariant;
    }

    @Override
    public void setFontVariant(FontVariantMode fontVariant) {
        this.fontVariant = fontVariant;
    }

    @Override
    public FontWeightMode getFontWeight() {
        return fontWeight;
    }

    @Override
    public void setFontWeight(FontWeightMode fontWeight) {
        this.fontWeight = fontWeight;
    }

    @Override
    public TextDecorationFlags getTextDecoration() {
        return textDecoration;
    }

    @Override
    public void setTextDecoration(TextDecorationFlags textDecoration) {
        this.textDecoration = textDecoration;
    }

}
