package net.bodz.bas.i18n.dom;

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
     * Return the longest matching domain.
     */
    findNearestAndPullToFront(true),

    /**
     * Create the full path with the given init-value if it's not existed.
     */
    createPath,

    ;

    boolean pullToFront;

    private DomainResolveMode() {
    }

    private DomainResolveMode(boolean pullToFront) {
        this.pullToFront = pullToFront;
    }

    public boolean isPullToFront() {
        return pullToFront;
    }

    public void setPullToFront(boolean pullToFront) {
        this.pullToFront = pullToFront;
    }

}
