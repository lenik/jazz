package net.bodz.bas.gui.spec0.color;

import net.bodz.bas.gui.spec0.IColor;

public interface IColor_HSL24
        extends IColor {

    /**
     * 0..359
     */
    int getHue8();

    void setHue8(int hue8);

    /**
     * 0..99
     */
    int getSaturation8();

    void setSaturation8(int hue8);

    /**
     * 0..99
     */
    int getLightness8();

    void setLightness8(int lightness8);

}
