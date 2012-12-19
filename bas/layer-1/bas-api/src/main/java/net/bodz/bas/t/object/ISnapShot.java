package net.bodz.bas.t.object;

public interface ISnapShot
        extends ISnapshot {

    /**
     * Take a const snapshot. A "deep" shared-snapshot.
     * 
     * <code>snap.snap</code> is always the same as <code>snap</code>.
     * 
     * <p>
     * A snapshot doesn't reflect change on the source object.
     * 
     * It's assumed the caller won't modify a const snapshot. So the default snap() implementation
     * may just return snapshot().
     * 
     * @return A const snapshot. Which maybe backed by this object.
     */
    Object snap();

    /**
     * A "light" private-snapshot.
     * 
     * <code>this.shot</code> is always different from <code>this</code>.
     * 
     * However, <code>shot.internal</code> and <code>this.internal</code> maybe the same.
     */
    Object shot();

}
