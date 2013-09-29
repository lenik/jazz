package net.bodz.bas.gui.css3.property;

public enum OffsetType {

    /**
     * For non-replaced elements, the effect of this value depends on which of related properties
     * have the value 'auto' as well. See the sections on the width and height of absolutely
     * positioned, non-replaced elements for details. For replaced elements, the effect of this
     * value depends only on the intrinsic dimensions of the replaced content. See the sections on
     * the width and height of absolutely positioned, replaced elements for details.
     */
    auto,

    /** The offset is a fixed distance from the reference edge. Negative values are allowed. */
    length,

    /**
     * The offset is a percentage of the containing block's width (for 'left' or 'right') or height
     * (for 'top' and 'bottom'). Negative values are allowed.
     */
    percentage,

    /**
     * (Only on 'max-width', 'max-height') No limit on the width or height of the box.
     */
    none,

}
