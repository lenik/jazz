package net.bodz.bas.gui.css3.property;

/**
 * Word spacing algorithms are user agent-dependent. Word spacing is also influenced by
 * justification (see the 'text-align' property). Word spacing affects each space (U+0020) and
 * non-breaking space (U+00A0), left in the text after the white space processing rules have been
 * applied. The effect of the property on other word-separator characters is undefined. However
 * general punctuation, characters with zero advance width (such as the zero with space U+200B) and
 * fixed-width spaces (such as U+3000 and U+2000 through U+200A) are not affected.,
 */
public enum WordSpacingType {

    /** The normal inter-word space, as defined by the current font and/or the UA. */
    normal,

    /**
     * This value indicates inter-word space in addition to the default space between words. Values
     * may be negative, but there may be implementation-specific limits.
     */
    length,

}
