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

    String getFontFamily();

    FontSizeType getFontSizeType();

    LengthMeasure getFontSize();

    FontStyleMode getFontStyle();

    FontWeightMode getFontWeight();

    FontVariantMode getFontVariant();

    TextDecorationFlags getTextDecoration();

}
