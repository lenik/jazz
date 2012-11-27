package net.bodz.bas.gui.spec0;

public interface IColor {

    /**
     * 0..255
     */
    int getAlphaByte();

    IColor_RGB toRGB();

    IColor_FloatRGB toFloatRGB();

    IColor_CMYK toCMYK();

}
