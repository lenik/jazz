package net.bodz.swt.gui.dev;

import java.io.Serializable;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

import net.bodz.bas.gui.style.IColor;

public class SWTColor
        implements IColor, Serializable {

    private static final long serialVersionUID = 1L;

    public Color color;

    public SWTColor(Color color) {
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
        else if (object instanceof SWTColor)
            c = ((SWTColor) object).color;
        else
            return false;

        return color.equals(c);
    }

    public int getBlue() {
        return color.getBlue();
    }

    public int getGreen() {
        return color.getGreen();
    }

    public int getRed() {
        return color.getRed();
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
