package net.bodz.swt.gui.dev;

import java.io.Serializable;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

import net.bodz.bas.gui.style.IFont;

public class SWTFont
        implements IFont, Serializable {

    private static final long serialVersionUID = 1L;

    final Font font;

    public SWTFont(Font font) {
        assert font != null;
        this.font = font;
    }

    public void dispose() {
        font.dispose();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;

        Font c;
        if (object instanceof Font)
            c = (Font) object;
        else if (object instanceof SWTFont)
            c = ((SWTFont) object).font;
        else
            return false;

        return font.equals(c);
    }

    public FontData[] getFontData() {
        return font.getFontData();
    }

    @Override
    public int hashCode() {
        return font.hashCode();
    }

    public boolean isDisposed() {
        return font.isDisposed();
    }

    @Override
    public String toString() {
        return font.toString();
    }

}
