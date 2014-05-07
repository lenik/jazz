package net.bodz.bas.ui.style.color;

public class RGB32Color
        extends AbstractRGBA32Color {

    private static final long serialVersionUID = 1L;

    private byte red8;
    private byte green8;
    private byte blue8;
    private byte alpha8;

    public RGB32Color() {
    }

    public RGB32Color(byte red8, byte green8, byte blue8, byte alpha8) {
        this.red8 = red8;
        this.green8 = green8;
        this.blue8 = blue8;
        this.alpha8 = alpha8;
    }

    public RGB32Color(int red8, int green8, int blue8, int alpha8) {
        this.red8 = (byte) red8;
        this.green8 = (byte) green8;
        this.blue8 = (byte) blue8;
        this.alpha8 = (byte) alpha8;
    }

    @Override
    public int getRed8() {
        return red8;
    }

    @Override
    public void setRed8(int red8) {
        this.red8 = (byte) red8;
    }

    @Override
    public int getGreen8() {
        return green8;
    }

    @Override
    public void setGreen8(int green8) {
        this.green8 = (byte) green8;
    }

    @Override
    public int getBlue8() {
        return blue8;
    }

    @Override
    public void setBlue8(int blue8) {
        this.blue8 = (byte) blue8;
    }

    @Override
    public int getAlpha8() {
        return alpha8;
    }

    @Override
    public void setAlpha8(int alpha8) {
        this.alpha8 = (byte) alpha8;
    }

}
