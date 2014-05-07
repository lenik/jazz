package net.bodz.bas.ui.style;

import java.io.Serializable;

import net.bodz.bas.ui.css3.ICss3Length;
import net.bodz.bas.ui.css3.property.FontStyleMode;
import net.bodz.bas.ui.css3.property.FontUseMode;
import net.bodz.bas.ui.css3.property.FontVariantMode;
import net.bodz.bas.ui.css3.property.FontWeightMode;
import net.bodz.bas.ui.css3.property.TextDecorationFlags;

public interface IFontType
        extends Serializable {

    FontUseMode getFontUse();

    String getFontFamily();

    ICss3Length getFontSize();

    FontStyleMode getFontStyle();

    FontVariantMode getFontVariant();

    FontWeightMode getFontWeight();

    TextDecorationFlags getTextDecoration();

}
