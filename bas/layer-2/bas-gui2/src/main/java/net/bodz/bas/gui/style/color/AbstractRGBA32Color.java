package net.bodz.bas.gui.style.color;

import net.bodz.bas.gui.style.IColor;

public abstract class AbstractRGBA32Color
        implements IColor, //
        IAlphaChannel8, IAlphaChannel16, IAlphaChannelf, //
        IColor_RGBA32, IColor_RGBA64, IColor_RGBA4f {

    private static final long serialVersionUID = 1L;

    @Override
    public IAlphaChannel8 toAlphaChannel8() {
        return this;
    }

    @Override
    public IAlphaChannel16 toAlphaChannel16() {
        return this;
    }

    @Override
    public IAlphaChannelf toAlphaChannel16f() {
        return this;
    }

    @Override
    public IColor_RGB24 toRGB24() {
        return this;
    }

    @Override
    public IColor_RGBA32 toRGBA32() {
        return this;
    }

    @Override
    public IColor_RGB48 toRGB48() {
        return this;
    }

    @Override
    public IColor_RGBA64 toRGBA64() {
        return this;
    }

    @Override
    public IColor_RGB3f toRGB3f() {
        return this;
    }

    @Override
    public IColor_RGBA4f toRGBA4f() {
        return this;
    }

    @Override
    public IColor_HSL24 toHSL24() {
        return null;
    }

    @Override
    public IColor_HSLA32 toHSLA32() {
        return null;
    }

    @Override
    public IColor_HSL3f toHSL3f() {
        return null;
    }

    @Override
    public IColor_HSLA4f toHSLA4f() {
        return null;
    }

    @Override
    public IColor_CMYK32 toCMYK32() {
        return null;
    }

    @Override
    public IColor_CMYK64 toCMYK64() {
        return null;
    }

    // RGBA/Expansion

    @Override
    public float getAlphaf() {
        return getAlpha8() / 255.0f;
    }

    @Override
    public void setAlphaf(float alphaf) {
        int alpha8 = (int) (alphaf * 255.f);
        setAlpha8(alpha8);
    }

    @Override
    public int getAlpha16() {
        int alpha8 = getAlpha8() & 0xff;
        return alpha8 << 8;
    }

    @Override
    public void setAlpha16(int alpha16) {
        int alpha8 = alpha16 >> 8;
        setAlpha8(alpha8);
    }

    @Override
    public float getRedf() {
        return getRed8() / 255.0f;
    }

    @Override
    public void setRedf(int redf) {
        int red8 = (int) (redf * 255.0f);
        setRed8(red8);
    }

    @Override
    public float getGreenf() {
        return getGreen8() / 255.0f;
    }

    @Override
    public void setGreenf(int greenf) {
        int green8 = (int) (greenf * 255.0f);
        setGreen8(green8);
    }

    @Override
    public float getBluef() {
        return getBlue8() / 255.0f;
    }

    @Override
    public void setBluef(int bluef) {
        int blue8 = (int) (bluef * 255.0f);
        setBlue8(blue8);
    }

    @Override
    public int getRed16() {
        int red8 = getRed8() & 0xff;
        return red8 << 8;
    }

    @Override
    public void setRed16(int red16) {
        int red8 = red16 >> 8;
        setRed8(red8);
    }

    @Override
    public int getGreen16() {
        int green8 = getGreen8() & 0xff;
        return green8 << 8;
    }

    @Override
    public void setGreen16(int green16) {
        int green8 = green16 >> 8;
        setGreen8(green8);
    }

    @Override
    public int getBlue16() {
        int blue8 = getBlue8() & 0xff;
        return blue8 << 8;
    }

    @Override
    public void setBlue16(int blue16) {
        int blue8 = blue16 >> 8;
        setBlue8(blue8);
    }

}
