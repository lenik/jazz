package net.bodz.bas.geom_f.api;

public interface ISnapshotable2d {

    /**
     * Take a static snapshot.
     * 
     * @return A static snapshot. Which is not backed by this object.
     */
    IShape2d snapshot();

    /**
     * Take a const snapshot.
     * 
     * @return A const snapshot. Which maybe backed by this object. The caller should not modify the
     *         snapshot.
     */
    IShape2d snapshotConst();

}
