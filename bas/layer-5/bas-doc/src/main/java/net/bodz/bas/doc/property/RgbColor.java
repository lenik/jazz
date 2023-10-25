package net.bodz.bas.doc.property;

public class RgbColor
        implements
            Color {

    String rgbCode;

    public RgbColor(String rgbCode) {
        if (rgbCode == null)
            throw new NullPointerException("rgbCode");
        this.rgbCode = rgbCode;
    }

    @Override
    public String getRgbCode() {
        return rgbCode;
    }

    @Override
    public String toString() {
        return "#" + rgbCode;
    }

}
