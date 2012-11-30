package net.bodz.bas.gui.css3.property;

public enum PositionMode {

    /**
     * The box is a normal box, laid out according to the normal flow. The 'top', 'right', 'bottom',
     * and 'left' properties do not apply.
     */
    static_,

    /**
     * The box's position is calculated according to the normal flow (this is called the position in
     * normal flow). Then the box is offset relative to its normal position. When a box B is
     * relatively positioned, the position of the following box is calculated as though B were not
     * offset. The effect of 'position:relative' on table-row-group, table-header-group,
     * table-footer-group, table-row, table-column-group, table-column, table-cell, and
     * table-caption elements is undefined.
     */
    relative,

    /**
     * The box's position (and possibly size) is specified with the 'top', 'right', 'bottom', and
     * 'left' properties. These properties specify offsets with respect to the box's containing
     * block. Absolutely positioned boxes are taken out of the normal flow. This means they have no
     * impact on the layout of later siblings. Also, though absolutely positioned boxes have
     * margins, they do not collapse with any other margins.
     */
    absolute,

    /**
     * The box's position is calculated according to the 'absolute' model, but in addition, the box
     * is fixed with respect to some reference. As with the 'absolute' model, the box's margins do
     * not collapse with any other margins. In the case of handheld, projection, screen, tty, and tv
     * media types, the box is fixed with respect to the viewport and does not move when scrolled.
     * In the case of the print media type, the box is rendered on every page, and is fixed with
     * respect to the page box, even if the page is seen through a viewport (in the case of a
     * print-preview, for example). For other media types, the presentation is undefined. Authors
     * may wish to specify 'fixed' in a media-dependent way. For instance, an author may want a box
     * to remain at the top of the viewport on the screen, but not at the top of each printed page.
     * The two specifications may be separated by using an @media rule, as in:
     * 
     * <pre>
     * @media screen { 
     *   h1#first { position: fixed } 
     * }
     * @media print { 
     *   h1#first { position: static }
     * }
     * </pre>
     * 
     * UAs must not paginate the content of fixed boxes.
     */
    fixed,

}
