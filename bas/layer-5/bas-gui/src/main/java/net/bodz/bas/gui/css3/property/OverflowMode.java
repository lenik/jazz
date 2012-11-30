package net.bodz.bas.gui.css3.property;

/**
 * Even if 'overflow' is set to 'visible', content may be clipped to a UA's document window by the
 * native operating environment.
 * 
 * UAs must apply the 'overflow' property set on the root element to the viewport. When the root
 * element is an HTML "HTML" element or an XHTML "html" element, and that element has an HTML "BODY"
 * element or an XHTML "body" element as a child, user agents must instead apply the 'overflow'
 * property from the first such child element to the viewport, if the value on the root element is
 * 'visible'. The 'visible' value when used for the viewport must be interpreted as 'auto'. The
 * element from which the value is propagated must have a used value for 'overflow' of 'visible'.
 * 
 * In the case of a scrollbar being placed on an edge of the element's box, it should be inserted
 * between the inner border edge and the outer padding edge. Any space taken up by the scrollbars
 * should be taken out of (subtracted from the dimensions of) the containing block formed by the
 * element with the scrollbars.
 */
public enum OverflowMode {

    /**
     * This value indicates that content is not clipped, i.e., it may be rendered outside the block
     * box.
     */
    visible,

    /**
     * This value indicates that the content is clipped and that no scrolling user interface should
     * be provided to view the content outside the clipping region.
     */
    hidden,

    /**
     * This value indicates that the content is clipped and that if the user agent uses a scrolling
     * mechanism that is visible on the screen (such as a scroll bar or a panner), that mechanism
     * should be displayed for a box whether or not any of its content is clipped. This avoids any
     * problem with scrollbars appearing and disappearing in a dynamic environment. When this value
     * is specified and the target medium is 'print', overflowing content may be printed.
     */
    scroll,

    /**
     * The behavior of the 'auto' value is user agent-dependent, but should cause a scrolling
     * mechanism to be provided for overflowing boxes.
     */
    auto,

}
