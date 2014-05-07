package net.bodz.bas.ui.style.color;

import net.bodz.bas.ui.style.IColor;

public interface IColor_CMYK64
        extends IColor {

    /**
     * 0..65535
     */
    int getCyan16();

    void setCyan16(int cyan16);

    /**
     * 0..65535
     */
    int getMagenta16();

    void setMagenta16(int magenta16);

    /**
     * 0..65535
     */
    int getYellow16();

    void setYelow16(int yellow16);

    /**
     * 0..65535
     */
    int getBlack16();

    void setBlack16(int black16);

}
