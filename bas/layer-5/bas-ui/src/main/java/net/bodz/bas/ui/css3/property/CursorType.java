package net.bodz.bas.ui.css3.property;

public enum CursorType {

    /** The UA determines the cursor to display based on the current context. */
    auto,

    /** A simple crosshair (e.g., short line segments resembling a "+" sign). */
    crosshair,

    /** The platform-dependent default cursor. Often rendered as an arrow. */
    default_,

    /** The cursor is a pointer that indicates a link. */
    pointer,

    /** Indicates something is to be moved. */
    move,

    /**
     * Indicate the movement starts from the east corner of the box.
     */
    e_resize,
    /**
     * Indicate the movement starts from the north-east corner of the box.
     */
    ne_resize,

    /**
     * Indicate the movement starts from the north-west corner of the box.
     */
    nw_resize,

    /**
     * Indicate the movement starts from the north corner of the box.
     */
    n_resize,

    /**
     * Indicate the movement starts from the south-east corner of the box.
     */
    se_resize,

    /**
     * Indicate the movement starts from the south-west corner of the box.
     */
    sw_resize,

    /**
     * Indicate the movement starts from the south corner of the box.
     */
    s_resize,

    /**
     * Indicate the movement starts from the west corner of the box.
     */
    w_resize,

    /** Indicates text that may be selected. Often rendered as an I-beam. */
    text,

    /**
     * Indicates that the program is busy and the user should wait. Often rendered as a watch or
     * hourglass.
     */
    wait,

    /**
     * A progress indicator. The program is performing some processing, but is different from 'wait'
     * in that the user may still interact with the program. Often rendered as a spinning beach
     * ball, or an arrow with a watch or hourglass.
     */
    progress,

    /**
     * Help is available for the object under the cursor. Often rendered as a question mark or a
     * balloon.
     */
    help,

    /**
     * The user agent retrieves the cursor from the resource designated by the URI. If the user
     * agent cannot handle the first cursor of a list of cursors, it should attempt to handle the
     * second, etc. If the user agent cannot handle any user-defined cursor, it must use the generic
     * cursor at the end of the list. Intrinsic sizes for cursors are calculated as for background
     * images, except that a UA-defined rectangle is used in place of the rectangle that establishes
     * the coordinate system for the 'background-image' property. This UA-defined rectangle should
     * be based on the size of a typical cursor on the UA's operating system. If the resulting
     * cursor size does not fit within this rectangle, the UA may proportionally scale the resulting
     * cursor down until it fits within the rectangle.
     */
    uri,

}
