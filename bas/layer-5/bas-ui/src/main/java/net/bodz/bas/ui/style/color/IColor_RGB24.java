package net.bodz.bas.ui.style.color;

import net.bodz.bas.ui.style.IColor;

public interface IColor_RGB24
        extends IColor {

    /**
     * 0..255
     */
    int getRed8();

    void setRed8(int red8);

    /**
     * 0..255
     */
    int getGreen8();

    void setGreen8(int green8);

    /**
     * 0..255
     */
    int getBlue8();

    void setBlue8(int blue8);

}
