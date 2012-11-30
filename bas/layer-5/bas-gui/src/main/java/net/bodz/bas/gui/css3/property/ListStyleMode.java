package net.bodz.bas.gui.css3.property;

public enum ListStyleMode {

    none,

    disc, circle, square,

    /** Decimal numbers, beginning with 1. */
    decimal,

    /** Decimal numbers padded by initial zeros (e.g., 01, 02, 03, ..., 98, 99). */
    decimal_leading_zero,

    /** Lowercase roman numerals (i, ii, iii, iv, v, etc.). */
    lower_roman,

    /** Uppercase roman numerals (I, II, III, IV, V, etc.). */
    upper_roman,

    /** Traditional Georgian numbering (an, ban, gan, ..., he, tan, in, in-an, ...). */
    georgian,

    /** Traditional uppercase Armenian numbering. */
    armenian,

    /** Lowercase ascii letters (a, b, c, ... z). */
    lower_latin, lower_alpha,

    /** Uppercase ascii letters (A, B, C, ... Z). */
    upper_latin, upper_alpha,

    /** Lowercase classical Greek alpha, beta, gamma, ... (α, β, γ, ...) */
    lower_greek,

}
