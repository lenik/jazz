package net.bodz.swt.gui.style;

import java.io.Serializable;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

import net.bodz.bas.gui.style.IFontType;

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

}
