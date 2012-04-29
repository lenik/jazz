package net.bodz.bas.disp.util;

import net.bodz.bas.disp.IPathArrival;

public interface ArrivalBacktraceCallback<E extends Exception> {

    /**
     * @return <code>false</code> to terminate the traversion.
     */
    boolean arriveBack(IPathArrival arrival)
            throws E;

}
