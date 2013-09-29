package net.bodz.bas.gui.css3.property;

/**
 * A conforming user agent may interpret the values 'left' and 'right' as 'always'.
 * 
 * A potential page break location is typically under the influence of the parent element's
 * 'page-break-inside' property, the 'page-break-after' property of the preceding element, and the
 * 'page-break-before' property of the following element. When these properties have values other
 * than 'auto', the values 'always', 'left', and 'right' take precedence over 'avoid'.
 * 
 * User Agents must apply these properties to block-level elements in the normal flow of the root
 * element. User agents may also apply these properties to other elements, e.g., 'table-row'
 * elements.
 * 
 * When a page break splits a box, the box's margins, borders, and padding have no visual effect
 * where the split occurs.
 */
public enum PageBreakMode {

    /** Neither force nor forbid a page break before (after, inside) the generated box. */
    auto,

    /** Always force a page break before (after) the generated box. */
    always,

    /** Avoid a page break before (after, inside) the generated box. */
    avoid,

    /**
     * Force one or two page breaks before (after) the generated box so that the next page is
     * formatted as a left page.
     */
    left,

    /**
     * Force one or two page breaks before (after) the generated box so that the next page is
     * formatted as a right page.
     */
    right,

}
