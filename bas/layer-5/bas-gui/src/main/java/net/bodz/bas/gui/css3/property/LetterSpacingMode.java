package net.bodz.bas.gui.css3.property;

public enum LetterSpacingMode {

    /**
     * The spacing is the normal spacing for the current font. This value allows the user agent to
     * alter the space between characters in order to justify text.
     */
    normal,

    /**
     * This value indicates inter-character space in addition to the default space between
     * characters. Values may be negative, but there may be implementation-specific limits. User
     * agents may not further increase or decrease the inter-character space in order to justify
     * text.
     */
    length,

}
