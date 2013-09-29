package net.bodz.bas.gui.style;

import net.bodz.bas.gui.css3.property.FontSizeType;
import net.bodz.bas.gui.css3.property.FontStyleMode;
import net.bodz.bas.gui.css3.property.FontUseMode;
import net.bodz.bas.gui.css3.property.FontVariantMode;
import net.bodz.bas.gui.css3.property.FontWeightMode;
import net.bodz.bas.gui.css3.property.TextDecorationFlags;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public interface IMutableFontType
        extends IFontType {

    void setFontUse(FontUseMode fontUse);

    void setFontFamily(String fontFamily);

    void setFontSizeType(FontSizeType fontSizeType);

    void setFontSize(LengthMeasure fontSize);

    void setFontStyle(FontStyleMode fontStyle);

    void setFontWeight(FontWeightMode fontWeight);

    void setFontVariant(FontVariantMode fontVariant);

    void setTextDecoration(TextDecorationFlags textDecoration);

}
