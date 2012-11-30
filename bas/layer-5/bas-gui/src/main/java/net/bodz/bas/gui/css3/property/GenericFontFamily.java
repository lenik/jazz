package net.bodz.bas.gui.css3.property;

public enum GenericFontFamily {

    /**
     * Glyphs of serif fonts, as the term is used in CSS, tend to have finishing strokes, flared or
     * tapering ends, or have actual serifed endings (including slab serifs). Serif fonts are
     * typically proportionately-spaced. They often display a greater variation between thick and
     * thin strokes than fonts from the 'sans-serif' generic font family. CSS uses the term 'serif'
     * to apply to a font for any script, although other names may be more familiar for particular
     * scripts, such as Mincho (Japanese), Sung or Song (Chinese), Totum or Kodig (Korean). Any font
     * that is so described may be used to represent the generic 'serif' family.
     */
    serif,

    /**
     * Glyphs in sans-serif fonts, as the term is used in CSS, tend to have stroke endings that are
     * plain -- with little or no flaring, cross stroke, or other ornamentation. Sans-serif fonts
     * are typically proportionately-spaced. They often have little variation between thick and thin
     * strokes, compared to fonts from the 'serif' family. CSS uses the term 'sans-serif' to apply
     * to a font for any script, although other names may be more familiar for particular scripts,
     * such as Gothic (Japanese), Kai (Chinese), or Pathang (Korean). Any font that is so described
     * may be used to represent the generic 'sans-serif' family.
     */
    sans_serif,

    /**
     * Glyphs in cursive fonts, as the term is used in CSS, generally have either joining strokes or
     * other cursive characteristics beyond those of italic typefaces. The glyphs are partially or
     * completely connected, and the result looks more like handwritten pen or brush writing than
     * printed letterwork. Fonts for some scripts, such as Arabic, are almost always cursive. CSS
     * uses the term 'cursive' to apply to a font for any script, although other names such as
     * Chancery, Brush, Swing and Script are also used in font names.
     */
    cursive,

    /**
     * Fantasy fonts, as used in CSS, are primarily decorative while still containing
     * representations of characters (as opposed to Pi or Picture fonts, which do not represent
     * characters).
     */
    fantasy,

    /**
     * The sole criterion of a monospace font is that all glyphs have the same fixed width. (This
     * can make some scripts, such as Arabic, look most peculiar.) The effect is similar to a manual
     * typewriter, and is often used to set samples of computer code.
     */
    monospace,

}
