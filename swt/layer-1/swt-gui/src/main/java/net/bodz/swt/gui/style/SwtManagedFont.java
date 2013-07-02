package net.bodz.swt.gui.style;

import java.io.Serializable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.gui.css3.property.FontSizeType;
import net.bodz.bas.gui.css3.property.FontStyleMode;
import net.bodz.bas.gui.css3.property.FontUseMode;
import net.bodz.bas.gui.css3.property.FontVariantMode;
import net.bodz.bas.gui.css3.property.FontWeightMode;
import net.bodz.bas.gui.css3.property.TextDecorationFlags;
import net.bodz.bas.gui.style.IFontType;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public class SwtManagedFont
        implements IFontType, Serializable {

    private static final long serialVersionUID = 1L;

    private final Font font;
    private final boolean managed;

    public SwtManagedFont(Font font) {
        if (font == null)
            throw new NullPointerException("font");
        this.font = font;
        this.managed = false;
    }

    public boolean isDisposed() {
        return font.isDisposed();
    }

    public void dispose() {
        font.dispose();
    }

    public Font getSwtFont() {
        return font;
    }

    public FontData[] getFontData() {
        return font.getFontData();
    }

    public FontData getFontData0() {
        FontData[] fontDataArray = font.getFontData();
        return fontDataArray[0];
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SwtManagedFont))
            return false;

        SwtManagedFont o = (SwtManagedFont) obj;
        if (managed != o.managed)
            return false;
        if (!font.equals(o.font))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0x7e3e8a3c;
        if (managed)
            hash += 0xe777434c;
        hash += font.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return font.toString();
    }

    // -o IFontType

    @Override
    public FontUseMode getFontUse() {
        return null;
    }

    @Override
    public void setFontUse(FontUseMode fontUse) {
        throw new ReadOnlyException();
    }

    @Override
    public FontSizeType getFontSizeType() {
        return FontSizeType.medium;
    }

    @Override
    public void setFontSizeType(FontSizeType fontSizeType) {
        throw new ReadOnlyException();
    }

    @Override
    public LengthMeasure getFontSize() {
        FontData fontData = getFontData0();
        return new LengthMeasure(fontData.getHeight(), LengthMeasure.POINT);
    }

    @Override
    public void setFontSize(LengthMeasure fontSize) {
        throw new ReadOnlyException();
    }

    @Override
    public FontStyleMode getFontStyle() {
        FontData fontData = getFontData0();
        int fontStyle = fontData.getStyle();
        if ((fontStyle & SWT.ITALIC) != 0)
            return FontStyleMode.italic;
        else
            return FontStyleMode.normal;
    }

    @Override
    public void setFontStyle(FontStyleMode fontStyle) {
        throw new ReadOnlyException();
    }

    @Override
    public FontVariantMode getFontVariant() {
        // FontData fontData = getFontData0();
        return FontVariantMode.normal;
    }

    @Override
    public void setFontVariant(FontVariantMode fontVariant) {
        throw new ReadOnlyException();
    }

    @Override
    public FontWeightMode getFontWeight() {
        FontData fontData = getFontData0();
        int fontStyle = fontData.getStyle();
        if ((fontStyle & SWT.BOLD) != 0)
            return FontWeightMode.bold;
        else
            return FontWeightMode.normal;
    }

    @Override
    public void setFontWeight(FontWeightMode fontWeight) {
        throw new ReadOnlyException();
    }

    @Override
    public TextDecorationFlags getTextDecoration() {
        return new TextDecorationFlags();
    }

    @Override
    public void setTextDecoration(TextDecorationFlags textDecoration) {
        throw new ReadOnlyException();
    }

}
