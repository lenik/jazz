package net.bodz.swt.ui.style;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import net.bodz.bas.t.object.IDisposable;
import net.bodz.bas.ui.style.color.AbstractRGBA32Color;
import net.bodz.bas.ui.style.color.IColor_RGB24;
import net.bodz.bas.ui.style.color.IColor_RGBA32;

public class SwtColor
        extends AbstractRGBA32Color
        implements IDisposable {

    private static final long serialVersionUID = 1L;

    private final Color color;
    private final boolean managed;

    public SwtColor(Device device, IColor_RGB24 color) {
        if (device == null)
            device = Display.getCurrent();
        this.color = new Color(device, color.getRed8(), color.getGreen8(), color.getBlue8());
        this.managed = true;
    }

    public SwtColor(Color color) {
        assert color != null;
        this.color = color;
        this.managed = false;
    }

    // TODO should finalize be necessary..?
    // @Override
    // protected void finalize()
    // throws Throwable {
    // dispose();
    // }

    public boolean isDisposed() {
        return color.isDisposed();
    }

    @Override
    public void dispose() {
        if (managed)
            color.dispose();
    }

    public Color getSwtColor() {
        return color;
    }

    public RGB getRGB() {
        return color.getRGB();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SwtColor))
            return false;

        SwtColor o = (SwtColor) obj;
        if (!managed != o.managed)
            return false;
        if (!color.equals(o.color))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0x8a57df58;
        if (managed)
            hash += 0x19b8224e;
        hash += color.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return color.toString();
    }

    /** ⇱ Implementation Of {@link IColor_RGBA32}. */
    /* _____________________________ */static section.iface __RGBA32__;

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

}
