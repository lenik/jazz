package net.bodz.bas.gui.spec0;

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

    FontStyleMode getFontStyle();

    void setFontStyle(FontStyleMode fontStyle);

    FontSizeType getFontSizeType();

    void setFontSizeType(FontSizeType fontSizeType);

    LengthMeasure getFontSize();

    void setFontSize(LengthMeasure fontSize);

    FontVariantMode getFontVariant();

    void setFontVariant(FontVariantMode fontVariant);

    FontWeightMode getFontWeight();

    void setFontWeight(FontWeightMode fontWeight);

    TextDecorationFlags getTextDecoration();

    void setTextDecoration(TextDecorationFlags textDecoration);

}
