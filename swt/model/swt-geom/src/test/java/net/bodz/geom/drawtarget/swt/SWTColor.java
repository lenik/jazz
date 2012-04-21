package net.bodz.geom.drawtarget.swt;

import net.bodz.geom.drawtarget.Color;

import org.eclipse.swt.graphics.RGB;

public class SWTColor
        extends Color {

    static final long serialVersionUID = -2892726793071126904L;

    public org.eclipse.swt.graphics.Color color;

    public SWTColor(org.eclipse.swt.graphics.Color color) {
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

        org.eclipse.swt.graphics.Color c;
        if (object instanceof org.eclipse.swt.graphics.Color)
            c = (org.eclipse.swt.graphics.Color) object;
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
