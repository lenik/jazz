package net.bodz.bas.ui.style.color;

public class RGB24Color
        extends AbstractRGBA32Color {

    private static final long serialVersionUID = 1L;

    private byte red8;
    private byte green8;
    private byte blue8;

    public RGB24Color() {
    }

    public RGB24Color(byte red8, byte green8, byte blue8) {
        this.red8 = red8;
        this.green8 = green8;
        this.blue8 = blue8;
    }

    public RGB24Color(int red8, int green8, int blue8) {
        this.red8 = (byte) red8;
        this.green8 = (byte) green8;
        this.blue8 = (byte) blue8;
    }

    public static RGB24Color fromBE(int _0xrrggbb) {
        byte blue = (byte) (_0xrrggbb & 0xff);
        int _0xrrgg = _0xrrggbb >> 8;
        byte green = (byte) (_0xrrgg & 0xff);
        byte red = (byte) (_0xrrgg >> 8);
        return new RGB24Color(red, green, blue);
    }

    public static RGB24Color fromLE(int _0xbbggrr) {
        byte red = (byte) (_0xbbggrr & 0xff);
        int _0xbbgg = _0xbbggrr >> 8;
        byte green = (byte) (_0xbbgg & 0xff);
        byte blue = (byte) (_0xbbgg >> 8);
        return new RGB24Color(red, green, blue);
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
        return 0;
    }

    @Override
    public void setAlpha8(int alpha8) {
    }

}
