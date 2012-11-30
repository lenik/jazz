package net.bodz.bas.gui.css3.property;

public enum LineHeightType {

    /**
     * Tells user agents to set the used value to a "reasonable" value based on the font of the
     * element. The value has the same meaning as <number>. We recommend a used value for 'normal'
     * between 1.0 to 1.2. The computed value is 'normal'.
     */
    normal,

    /**
     * The specified length is used in the calculation of the line box height. Negative values are
     * illegal.
     */
    length,

    /**
     * The used value of the property is this number multiplied by the element's font size. Negative
     * values are illegal. The computed value is the same as the specified value.
     */
    number,

    /**
     * The computed value of the property is this percentage multiplied by the element's computed
     * font size. Negative values are illegal.
     */
    percentage,

}
