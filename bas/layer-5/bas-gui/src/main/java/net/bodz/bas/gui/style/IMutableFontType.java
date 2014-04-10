package net.bodz.bas.gui.style;

import net.bodz.bas.gui.css3.ICss3Length;
import net.bodz.bas.gui.css3.property.FontStyleMode;
import net.bodz.bas.gui.css3.property.FontUseMode;
import net.bodz.bas.gui.css3.property.FontVariantMode;
import net.bodz.bas.gui.css3.property.FontWeightMode;
import net.bodz.bas.gui.css3.property.TextDecorationFlags;

public interface IMutableFontType
        extends IFontType {

    void setFontUse(FontUseMode fontUse);

    void setFontFamily(String fontFamily);

    void setFontSize(ICss3Length fontSize);

    void setFontStyle(FontStyleMode fontStyle);

    void setFontWeight(FontWeightMode fontWeight);

    void setFontVariant(FontVariantMode fontVariant);

    void setTextDecoration(TextDecorationFlags textDecoration);

}
