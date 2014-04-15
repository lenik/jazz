package net.bodz.bas.gui.css3.property;

public enum DirectionMode {

    /** Left-to-right direction. */
    ltr,

    /** Right-to-left direction. */
    rtl,

    /**
     * HTML5: Indicates that the contents of the element are explicitly directionally isolated text,
     * but that the direction is to be determined programmatically using the contents of the element
     * (as described below).
     * <p>
     * Note: The heuristic used by this state is very crude (it just looks at the first character
     * with a strong directionality, in a manner analogous to the Paragraph Level determination in
     * the bidirectional algorithm). Authors are urged to only use this value as a last resort when
     * the direction of the text is truly unknown and no better server-side heuristic can be
     * applied. [BIDI]
     * <p>
     * Note: For <code>textarea</code> and <code>pre</code> elements, the heuristic is applied on a
     * per-paragraph level.
     */
    auto,

}
