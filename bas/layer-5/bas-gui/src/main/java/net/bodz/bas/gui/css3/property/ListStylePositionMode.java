package net.bodz.bas.gui.css3.property;

public enum ListStylePositionMode {

    /**
     * The marker box is outside the principal block box. The position of the list-item marker
     * adjacent to floats is undefined in CSS 2.1. CSS 2.1 does not specify the precise location of
     * the marker box or its position in the painting order, but does require that for list items
     * whose 'direction' property is 'ltr' the marker box be on the left side of the content and for
     * elements whose 'direction' property is 'rtl' the marker box be on the right side of the
     * content. The marker box is fixed with respect to the principal block box's border and does
     * not scroll with the principal block box's content. In CSS 2.1, a UA may hide the marker if
     * the element's 'overflow' is other than 'visible'. (This is expected to change in the future.)
     * The size or contents of the marker box may affect the height of the principal block box
     * and/or the height of its first line box, and in some cases may cause the creation of a new
     * line box. Note: This interaction may be more precisely defined in a future level of CSS.
     */
    outside,

    /**
     * The marker box is placed as the first inline box in the principal block box, before the
     * element's content and before any :before pseudo-elements. CSS 2.1 does not specify the
     * precise location of the marker box.
     */
    inside,

}
