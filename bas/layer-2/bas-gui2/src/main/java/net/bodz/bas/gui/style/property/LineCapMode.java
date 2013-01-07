package net.bodz.bas.gui.style.property;

public enum LineCapMode {

    /**
     * Butt, aka. "Flat".
     * 
     * <pre>
     * ______________ 
     *               |
     * ______________|
     * </pre>
     */
    butt,

    /**
     * <pre>
     * ______________ __
     *               |  |
     * ______________|__|
     * </pre>
     */
    square,

    /**
     * <pre>
     * ______________ .              
     *               | '
     * ______________|,'
     * 
     * </pre>
     */
    round,

}
