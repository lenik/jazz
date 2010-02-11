package net.bodz.bas.fs.path;

public interface IPathAnchorPoint
        extends IPathAnchor {

    /**
     * A fixed-level anchor only anchors on a fixed level index in the context path.
     */
    boolean isFixedLevel();

    /**
     * Only valid if {@link #isFixedLevel()} returns <code>true</code>.
     */
    int getFixedLevel();

    /**
     * A chop-anchor always trim the right parts of context path, and the result anchor path may be
     * safely cached and later be appended.
     */
    boolean isChopAnchor();

}
