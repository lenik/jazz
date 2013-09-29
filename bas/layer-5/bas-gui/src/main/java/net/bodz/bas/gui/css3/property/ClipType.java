package net.bodz.bas.gui.css3.property;

public enum ClipType {

    /** The element does not clip. */
    auto,

    /**
     * In CSS 2.1, the only valid <shape> value is: rect(<top>, <right>, <bottom>, <left>) where
     * <top> and <bottom> specify offsets from the top border edge of the box, and <right>, and
     * <left> specify offsets from the left border edge of the box. Authors should separate offset
     * values with commas. User agents must support separation with commas, but may also support
     * separation without commas (but not a combination), because a previous revision of this
     * specification was ambiguous in this respect.
     */
    shape,

}
