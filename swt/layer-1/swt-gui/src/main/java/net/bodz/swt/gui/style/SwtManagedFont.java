package net.bodz.swt.gui.style;

import org.eclipse.swt.graphics.Font;

public class SwtManagedFont
        extends AbstractSwtFont {

    private static final long serialVersionUID = 1L;

    private Font font;
    private boolean managed;

    public SwtManagedFont(Font font) {
        this(font, false);
    }

    public SwtManagedFont(Font font, boolean managed) {
        super(font.getDevice());
        this.font = font;
        this.managed = managed;
    }

    @Override
    public Font getSwtFont() {
        return font;
    }

    @Override
    public void setSwtFont(Font font) {
        if (font == null)
            throw new NullPointerException("font");
        this.font = font;
    }

    @Override
    protected void finalize()
            throws Throwable {
        if (managed)
            // if (font!=null)
            font.dispose();
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
