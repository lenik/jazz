package net.bodz.bas.ui.css3;

import net.bodz.bas.i18n.unit.std.ILength;

public interface ICss3Length
        extends ILength, ICss3Keywords {

    int getType();

    void setType(int type);

    int getNumber();

    /**
     * Set the number value, also change type to {@link ICss3Keywords#NUMBER}.
     */
    void setNumber(int number);

    float getPercentage();

    /**
     * Set the number value, also change type to {@link ICss3Keywords#PERCENTAGE}.
     */
    void setPercentage(float percentage);

}
