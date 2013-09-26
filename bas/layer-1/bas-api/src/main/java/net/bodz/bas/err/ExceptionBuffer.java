package net.bodz.bas.err;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ExceptionBuffer
        implements RecoverableExceptionListener, Serializable {

    private static final long serialVersionUID = 1L;

    private List<Exception> list;

    public ExceptionBuffer() {
        list = new ArrayList<Exception>(1);
    }

    void addException(Exception e) {
        // if (list == null)
        // synchronized (list) {
        // if (list == null)
        // list = new ArrayList<Exception>();
        // }
        list.add(e);
    }

    @Override
    public void exceptionThrown(Exception e) {
        addException(e);
    }

    @Override
    public void recoverException(RecoverableExceptionEvent e) {
        addException(e.getException());
        e.setRecovered(true);
    }

    public boolean hasCaughtException() {
        return !list.isEmpty();
    }

    public Collection<Exception> getExceptions() {
        return Collections.unmodifiableList(list);
    }

    public void clear() {
        list.clear();
    }

    /**
     * @return <code>null</code> if no exception.
     */
    public String summary() {
        return summary(list);
    }

    /**
     * @return <code>null</code> if no exception.
     */
    public static String summary(Collection<Exception> exceptions) {
        if (exceptions.isEmpty())
            return null;
        StringBuilder buf = new StringBuilder(exceptions.size() * 30);
        for (Exception e : exceptions) {
            String line = e.getClass().getName() + ": " + e.getMessage();
            buf.append(line);
            buf.append('\n');
        }
        return buf.toString();
    }

}
