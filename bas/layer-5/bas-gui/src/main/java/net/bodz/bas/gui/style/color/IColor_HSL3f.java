package net.bodz.bas.gui.style.color;

import net.bodz.bas.gui.style.IColor;

public interface IColor_HSL3f
        extends IColor {

    /**
     * [0, 1]
     */
    float getHuef();

    void setHuef(float huef);

    /**
     * [0, 1]
     */
    float getSaturationf();

    void setSaturationf(float saturationf);

    /**
     * [0, 1]
     */
    float getLightnessf();

    void setLightnessf(float lightnessf);

}
