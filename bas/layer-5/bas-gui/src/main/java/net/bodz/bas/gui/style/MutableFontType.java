package net.bodz.bas.gui.style;

import net.bodz.bas.gui.css3.property.FontSizeType;
import net.bodz.bas.gui.css3.property.FontStyleMode;
import net.bodz.bas.gui.css3.property.FontUseMode;
import net.bodz.bas.gui.css3.property.FontVariantMode;
import net.bodz.bas.gui.css3.property.FontWeightMode;
import net.bodz.bas.gui.css3.property.TextDecorationFlags;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public class MutableFontType
        extends FontType
        implements IMutableFontType {

    private static final long serialVersionUID = 1L;

    @Override
    public void setFontUse(FontUseMode fontUse) {
        this.fontUse = fontUse;
    }

    @Override
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    @Override
    public void setFontSizeType(FontSizeType fontSizeType) {
        this.fontSizeType = fontSizeType;
    }

    @Override
    public void setFontSize(LengthMeasure fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public void setFontStyle(FontStyleMode fontStyle) {
        this.fontStyle = fontStyle;
    }

    @Override
    public void setFontWeight(FontWeightMode fontWeight) {
        this.fontWeight = fontWeight;
    }

    @Override
    public void setFontVariant(FontVariantMode fontVariant) {
        this.fontVariant = fontVariant;
    }

    @Override
    public void setTextDecoration(TextDecorationFlags textDecoration) {
        this.textDecoration = textDecoration;
    }

}
