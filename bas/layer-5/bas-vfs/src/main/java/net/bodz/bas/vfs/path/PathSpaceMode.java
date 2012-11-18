package net.bodz.bas.vfs.path;

public enum PathSpaceMode {

    /**
     * Trim the leading and trailing space, and compress continuous space to a single \x20 space.
     */
    normalized,

    /**
     * Preserve space characters as it is.
     */
    preserved,

    /**
     * Convert space to +, vice versa.
     */
    asPlus,

}
