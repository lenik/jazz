package net.bodz.bas.ui.style;

import net.bodz.bas.ui.css3.ICss3Length;
import net.bodz.bas.ui.css3.property.FontStyleMode;
import net.bodz.bas.ui.css3.property.FontUseMode;
import net.bodz.bas.ui.css3.property.FontVariantMode;
import net.bodz.bas.ui.css3.property.FontWeightMode;
import net.bodz.bas.ui.css3.property.TextDecorationFlags;

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
    public void setFontSize(ICss3Length fontSize) {
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
