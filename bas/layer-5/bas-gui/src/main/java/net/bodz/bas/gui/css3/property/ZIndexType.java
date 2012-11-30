package net.bodz.bas.gui.css3.property;

public enum ZIndexType {

    /**
     * This integer is the stack level of the generated box in the current stacking context. The box
     * also establishes a new stacking context.
     */
    integer,

    /**
     * The stack level of the generated box in the current stacking context is 0. The box does not
     * establish a new stacking context unless it is the root element.
     */
    auto,

}
