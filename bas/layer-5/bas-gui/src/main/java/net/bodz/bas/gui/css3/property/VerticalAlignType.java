package net.bodz.bas.gui.css3.property;

public enum VerticalAlignType {

    /*
     * The following values only have meaning with respect to a parent inline element, or to the
     * strut of a parent block container element.
     * 
     * In the following definitions, for inline non-replaced elements, the box used for alignment is
     * the box whose height is the 'line-height' (containing the box's glyphs and the half-leading
     * on each side, see above). For all other elements, the box used for alignment is the margin
     * box.
     */
    /**
     * Align the baseline of the box with the baseline of the parent box. If the box does not have a
     * baseline, align the bottom margin edge with the parent's baseline.
     */
    baseline,

    /**
     * Align the vertical midpoint of the box with the baseline of the parent box plus half the
     * x-height of the parent.
     */
    middle,

    /**
     * Lower the baseline of the box to the proper position for subscripts of the parent's box.
     * (This value has no effect on the font size of the element's text.)
     */
    sub,

    /**
     * Raise the baseline of the box to the proper position for superscripts of the parent's box.
     * (This value has no effect on the font size of the element's text.)
     */
    super_,

    /** Align the top of the box with the top of the parent's content area (see 10.6.1). */
    text_top,

    /** Align the bottom of the box with the bottom of the parent's content area (see 10.6.1). */
    text_bottom,

    /**
     * Raise (positive value) or lower (negative value) the box by this distance (a percentage of
     * the 'line-height' value). The value '0%' means the same as 'baseline'.
     */
    percentage,

    /**
     * Raise (positive value) or lower (negative value) the box by this distance. The value '0cm'
     * means the same as 'baseline'.
     */
    length,

    /*
     * The following values align the element relative to the line box. Since the element may have
     * children aligned relative to it (which in turn may have descendants aligned relative to
     * them), these values use the bounds of the aligned subtree. The aligned subtree of an inline
     * element contains that element and the aligned subtrees of all children inline elements whose
     * computed 'vertical-align' value is not 'top' or 'bottom'. The top of the aligned subtree is
     * the highest of the tops of the boxes in the subtree, and the bottom is analogous.
     */

    /** Align the top of the aligned subtree with the top of the line box. */
    top,

    /** Align the bottom of the aligned subtree with the bottom of the line box. */
    bottom,

}
