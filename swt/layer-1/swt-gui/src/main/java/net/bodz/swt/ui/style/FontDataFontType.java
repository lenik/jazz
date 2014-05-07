package net.bodz.swt.ui.style;

import java.io.Serializable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;

import net.bodz.bas.i18n.unit.std.Length;
import net.bodz.bas.ui.css3.Css3Length;
import net.bodz.bas.ui.css3.ICss3Length;
import net.bodz.bas.ui.css3.property.FontStyleMode;
import net.bodz.bas.ui.css3.property.FontUseMode;
import net.bodz.bas.ui.css3.property.FontVariantMode;
import net.bodz.bas.ui.css3.property.FontWeightMode;
import net.bodz.bas.ui.css3.property.TextDecorationFlags;
import net.bodz.bas.ui.style.IFontType;
import net.bodz.bas.ui.style.IMutableFontType;

public class FontDataFontType
        implements IMutableFontType, Serializable {

    private static final long serialVersionUID = 1L;

    private Device device;
    private FontData _fontData;

    protected FontDataFontType() {
    }

    public FontDataFontType(FontData fontData) {
        if (fontData == null)
            throw new NullPointerException("fontData");
        this._fontData = fontData;
    }

    public FontData getFontData() {
        return _fontData;
    }

    public void setFontData(FontData fontData) {
        if (fontData == null)
            throw new NullPointerException("fontData");
        this._fontData = fontData;
    }

    /** ⇱ Implementaton Of {@link IFontType}. */
    /* _____________________________ */static section.iface __FONTTYPE__;

    @Override
    public FontUseMode getFontUse() {
        // logger.debug("font-use isn't supported in SwtFontType.");
        return null;
    }

    @Override
    public void setFontUse(FontUseMode fontUse) {
        // logger.debug("font-use isn't supported in SwtFontType.");
    }

    @Override
    public String getFontFamily() {
        FontData fontData = getFontData();
        assert fontData != null : "font data is null";
        return fontData.getName();
    }

    @Override
    public void setFontFamily(String fontFamily) {
        if (fontFamily != null) {
            int comma = fontFamily.indexOf(',');
            if (comma != -1)
                fontFamily = fontFamily.substring(0, comma);

            fontFamily = fontFamily.trim();
            if (fontFamily.isEmpty())
                fontFamily = null;
        }

        FontData fontData = getFontData();
        fontData.setName(fontFamily);
        setFontData(fontData);
    }

    @Override
    public ICss3Length getFontSize() {
        FontData fontData = getFontData();
        assert fontData != null : "font data is null";
        int fontHeight = fontData.getHeight();
        return new Css3Length(fontHeight, Length.POINT);
    }

    @Override
    public void setFontSize(ICss3Length fontSize) {
        Point ppi = device.getDPI();

        FontData fontData = getFontData();
        assert fontData != null : "font data is null";

        int fontHeight = fontSize.pixels(ppi.y);
        fontData.setHeight(fontHeight);

        setFontData(fontData);
    }

    @Override
    public FontStyleMode getFontStyle() {
        FontData fontData = getFontData();
        assert fontData != null : "font data is null";

        int fontStyle = fontData.getStyle();
        if ((fontStyle & SWT.ITALIC) != 0)
            return FontStyleMode.italic;
        else
            return FontStyleMode.normal;
    }

    @Override
    public void setFontStyle(FontStyleMode fontStyle) {
        if (fontStyle == null)
            throw new NullPointerException("fontStyle");

        FontData fontData = getFontData();
        assert fontData != null : "font data is null";

        int fontStyleInt = fontData.getStyle();

        switch (fontStyle) {
        case normal:
            fontStyleInt &= ~SWT.ITALIC;
            break;

        case italic:
        case oblique:
            fontStyleInt |= SWT.ITALIC;
            break;

        default:
            assert false;
        }

        fontData.setStyle(fontStyleInt);
        setFontData(fontData);
    }

    @Override
    public FontWeightMode getFontWeight() {
        FontData fontData = getFontData();
        assert fontData != null : "font data is null";

        int fontStyleInt = fontData.getStyle();
        if ((fontStyleInt & SWT.BOLD) != 0)
            return FontWeightMode.bold;
        else
            return FontWeightMode.normal;
    }

    @Override
    public void setFontWeight(FontWeightMode fontWeight) {
        if (fontWeight == null)
            throw new NullPointerException("fontWeight");

        FontData fontData = getFontData();
        assert fontData != null : "font data is null";

        int fontStyleInt = fontData.getStyle();

        switch (fontWeight) {
        case normal:
        case lighter:
            fontStyleInt &= ~SWT.BOLD;
            break;

        case bold:
        case bolder:
        case _100:
        default: // ... _900
            fontStyleInt |= SWT.BOLD;
            break;
        }

        fontData.setStyle(fontStyleInt);
        setFontData(fontData);
    }

    // small-caps
    @Override
    public FontVariantMode getFontVariant() {
        // logger.debug("font-variant isn't supported in SwtFontType.");
        return FontVariantMode.normal;
    }

    @Override
    public void setFontVariant(FontVariantMode fontVariant) {
        // logger.debug("font-variant isn't supported in SwtFontType.");
    }

    // underline, overline, line-through, blink..
    @Override
    public TextDecorationFlags getTextDecoration() {
        FontData fontData = getFontData();
        assert fontData != null : "font data is null";

        // logger.debug("text-decoration isn't supported in SwtFontType.");

        return new TextDecorationFlags();
    }

    @Override
    public void setTextDecoration(TextDecorationFlags textDecoration) {
        if (textDecoration == null)
            throw new NullPointerException("textDecoration");

        // logger.debug("text-decoration isn't supported in SwtFontType.");
    }

    public static FontData convert(IFontType src) {
        FontDataFontType mapper = new FontDataFontType(new FontData());
        mapper.setFontFamily(src.getFontFamily());
        mapper.setFontSize(src.getFontSize());
        mapper.setFontStyle(src.getFontStyle());
        mapper.setFontWeight(src.getFontWeight());
        FontData fontData = mapper.getFontData();
        return fontData;
    }

}
