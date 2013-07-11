package net.bodz.bas.gui.style;

import java.io.Serializable;

import net.bodz.bas.gui.css3.property.FontSizeType;
import net.bodz.bas.gui.css3.property.FontStyleMode;
import net.bodz.bas.gui.css3.property.FontUseMode;
import net.bodz.bas.gui.css3.property.FontVariantMode;
import net.bodz.bas.gui.css3.property.FontWeightMode;
import net.bodz.bas.gui.css3.property.TextDecorationFlags;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public interface IFontType
        extends Serializable {

    FontUseMode getFontUse();

    void setFontUse(FontUseMode fontUse);

    String getFontFamily();

    void setFontFamily(String fontFamily);

    FontSizeType getFontSizeType();

    void setFontSizeType(FontSizeType fontSizeType);

    LengthMeasure getFontSize();

    void setFontSize(LengthMeasure fontSize);

    FontStyleMode getFontStyle();

    void setFontStyle(FontStyleMode fontStyle);

    FontWeightMode getFontWeight();

    void setFontWeight(FontWeightMode fontWeight);

    FontVariantMode getFontVariant();

    void setFontVariant(FontVariantMode fontVariant);

    TextDecorationFlags getTextDecoration();

    void setTextDecoration(TextDecorationFlags textDecoration);

}
