package net.bodz.bas.log.objects;

import java.util.Collection;

public interface IExceptionBuffer {

    /**
     * @throws NullPointerException
     *             If <code>exception</code> is <code>null</code>.
     */
    IExceptionBuffer addException(Throwable exception);

    Exception getException();

    Collection<Exception> getExceptions();

}
