package net.bodz.bas.t.object;

public interface ISnapshot {

    /**
     * A "deep" private-snapshot.
     * 
     * A snapshot doesn't reflect change on the source object.
     * 
     * @return A static snapshot. Which is not backed by this object.
     */
    Object snapshot();

}
