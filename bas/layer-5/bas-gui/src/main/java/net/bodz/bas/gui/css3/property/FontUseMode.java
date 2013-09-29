package net.bodz.bas.gui.css3.property;

/**
 * Abstract font type.
 * 
 * In Win32, these font may be mapped to actual font types.
 */
public enum FontUseMode {

    /** The font used for captioned controls (e.g., buttons, drop-downs, etc.). */
    caption,

    /** The font used to label icons. */
    icon,

    /** The font used in menus (e.g., dropdown menus and menu lists). */
    menu,

    /** The font used in dialog boxes. */
    message_box,

    /** The font used for labeling small controls. */
    small_caption,

    /** The font used in window status bars. */
    status_bar,

}
