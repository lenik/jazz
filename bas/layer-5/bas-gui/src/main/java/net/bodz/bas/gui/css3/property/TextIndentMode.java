package net.bodz.bas.gui.css3.property;

/**
 * The value of 'text-indent' may be negative, but there may be implementation-specific limits. If
 * the value of 'text-indent' is either negative or exceeds the width of the block, that first box,
 * described above, can overflow the block. The value of 'overflow' will affect whether such text
 * that overflows the block is visible.
 */
public enum TextIndentMode {

    /** The indentation is a fixed length. */
    length,

    /** The indentation is a percentage of the containing block width. */
    percentage,

}
