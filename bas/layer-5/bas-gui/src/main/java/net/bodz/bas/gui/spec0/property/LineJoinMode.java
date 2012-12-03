package net.bodz.bas.gui.spec0.property;

public enum LineJoinMode {

    /**
     * <pre>
     * _________________
     *                 /
     *                /
     * _______       /
     *       /      /
     *      /      /
     *     /      /
     *    /      /
     * </pre>
     */
    miter,

    /**
     * <pre>
     * ___________
     *            `.
     *              \
     * _______       |
     *       /       '
     *      /       /
     *     /       /
     *    /       /
     * </pre>
     */
    round,

    /**
     * <pre>
     * ___________
     *            \
     *             \
     * _______      \
     *       /      /
     *      /      /
     *     /      /
     *    /      /
     * </pre>
     */
    bevel,

}
