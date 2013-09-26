package net.bodz.bas.flow.unit;

import java.io.IOException;

/**
 * tag interface
 */
public interface ISourceUnit
        extends IUnit {

    /**
     * @param timeout
     *            the preferred max time to wait.
     * @return <code>false</code> if no more data, and the pump loop must quit.
     */
    boolean pump(int timeout)
            throws IOException, InterruptedException;

    /**
     * pump immediately.
     * 
     * may implemented as {@link #pump(int)} with timeout=0. but this isn't necessary,
     * {@link #pump()} may wait for any timeout. (but it should be too long)
     * 
     * @return <code>false</code> if no immediately available data. this is different with
     *         {@link #pump(int)}.
     */
    boolean pump()
            throws IOException;

    void pumpLoop(int timeout)
            throws IOException, InterruptedException;

    void pumpLoop()
            throws IOException;

}
