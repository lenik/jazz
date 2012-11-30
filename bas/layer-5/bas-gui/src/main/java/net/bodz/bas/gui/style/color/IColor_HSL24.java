package net.bodz.bas.gui.style.color;

import net.bodz.bas.gui.style.IColor;

public interface IColor_HSL24
        extends IColor {

    /**
     * 0..359
     */
    int getHue();

    /**
     * 0..99
     */
    int getSaturation();

    /**
     * 0..99
     */
    int getLight();

}
