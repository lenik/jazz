package net.bodz.bas.geom_f.api;

public interface ISnapshotable2d {

    /**
     * Take a const snapshot.
     * 
     * A snapshot doesn't reflect change on the source object.
     * 
     * It's assumed the caller won't modify a const snapshot. So the default snapshot-const
     * implementation may return just the same as {@link #snapshot()}.
     * 
     * @return A const snapshot. Which maybe backed by this object.
     */
    IShape2d snap();

    /**
     * Like clone.
     */
    IShape2d shot();

    /**
     * Take a static snapshot.
     * 
     * A snapshot doesn't reflect change on the source object.
     * 
     * @return A static snapshot. Which is not backed by this object.
     */
    IShape2d snapshot();

}
