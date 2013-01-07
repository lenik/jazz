package net.bodz.bas.gui.css3.property;

/**
 * It is acceptable (but not required) in CSS 2.1 if the small-caps font is a created by taking a
 * normal font and replacing the lower case letters by scaled uppercase characters. As a last
 * resort, uppercase letters will be used as replacement for a small-caps font.
 */
public enum FontVariantMode {

    /** selects a font that is not a small-caps font */
    normal,

    /** selects a small-caps font */
    small_caps,

}
