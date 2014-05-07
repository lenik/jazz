package net.bodz.bas.ui.style.property;

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
