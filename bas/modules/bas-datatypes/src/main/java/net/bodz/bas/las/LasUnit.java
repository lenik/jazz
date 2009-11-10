package net.bodz.bas.las;

import net.bodz.bas.lang.err.IllegalUsageError;

public interface LasUnit {

    /**
     * @return <code>false</code> to skip the block to be entered, and the correspoing
     *         <code>leave</code> is unnecessary.
     */
    boolean enter(Object... args);

    /**
     * @throws IllegalUsageError
     *             if stack-trace is different from enter.
     */
    void leave() throws IllegalUsageError;

    /**
     * A variant of {@link #leave()}
     */
    <T> T leave(T returnValue) throws IllegalUsageError;

    /**
     * A variant of {@link #leave()}
     */
    <T extends Throwable> T leave(T t) throws IllegalUsageError, T;

}
