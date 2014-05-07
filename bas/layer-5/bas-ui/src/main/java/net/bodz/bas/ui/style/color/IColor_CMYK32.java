package net.bodz.bas.ui.style.color;

import net.bodz.bas.ui.style.IColor;

public interface IColor_CMYK32
        extends IColor {

    /**
     * 0..255
     */
    int getCyan8();

    void setCyan8(int cyan8);

    /**
     * 0..255
     */
    int getMagenta8();

    void setMagenta8(int magenta8);

    /**
     * 0..255
     */
    int getYellow8();

    void setYelow8(int yellow8);

    /**
     * 0..255
     */
    int getBlack8();

    void setBlack8(int black8);

}
