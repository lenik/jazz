package net.bodz.geom.drawtarget.swt;

import net.bodz.geom.drawtarget.Font;

import org.eclipse.swt.graphics.FontData;

public class SWTFont extends Font {

    final org.eclipse.swt.graphics.Font font;

    public SWTFont(org.eclipse.swt.graphics.Font font) {
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

        org.eclipse.swt.graphics.Font c;
        if (object instanceof org.eclipse.swt.graphics.Font)
            c = (org.eclipse.swt.graphics.Font) object;
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

    private static final long serialVersionUID = 2482793723228912787L;

}
