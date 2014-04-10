package net.bodz.bas.gui.css3;

public interface ICss3Int
        extends ICss3Keywords {

    int getType();

    void setType(int type);

    int getValue();

    /**
     * Set the integer value, also change type to {@link ICss3Keywords#NUMBER}.
     */
    void setValue(int value);

}
