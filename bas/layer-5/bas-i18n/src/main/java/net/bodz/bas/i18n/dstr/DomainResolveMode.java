package net.bodz.bas.i18n.dstr;

public enum DomainResolveMode {

    /**
     * Return <code>null</code> if path is not complete, i.e., for any path-prefix, there isn't any
     * matched domain.
     */
    nullForNone,

    /**
     * Return the longest matching domain.
     */
    findNearest,

    /**
     * Create the full path with the given init-value if it's not existed.
     */
    createPath,

}
