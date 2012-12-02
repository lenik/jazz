package net.bodz.swt.gui.spec1;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

import net.bodz.bas.gui.spec0.color.AbstractRGBA32Color;

public class SwtColor
        extends AbstractRGBA32Color {

    private static final long serialVersionUID = 1L;

    public Color color;

    public SwtColor(Color color) {
        assert color != null;
        this.color = color;
    }

    public void dispose() {
        color.dispose();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;

        Color c;
        if (object instanceof Color)
            c = (Color) object;
        else if (object instanceof SwtColor)
            c = ((SwtColor) object).color;
        else
            return false;

        return color.equals(c);
    }

    @Override
    public int getAlpha8() {
        return 0;
    }

    @Override
    public void setAlpha8(int alpha8) {
    }

    @Override
    public int getRed8() {
        return color.getRed();
    }

    @Override
    public void setRed8(int red8) {
    }

    @Override
    public int getGreen8() {
        return color.getGreen();
    }

    @Override
    public void setGreen8(int green8) {
    }

    @Override
    public int getBlue8() {
        return color.getBlue();
    }

    @Override
    public void setBlue8(int blue8) {
    }

    public RGB getRGB() {
        return color.getRGB();
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }

    public boolean isDisposed() {
        return color.isDisposed();
    }

    @Override
    public String toString() {
        return color.toString();
    }

}
