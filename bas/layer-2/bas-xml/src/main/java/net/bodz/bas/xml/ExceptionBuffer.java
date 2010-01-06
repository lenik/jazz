package net.bodz.bas.xml;

import java.beans.ExceptionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExceptionBuffer implements ExceptionListener {

    private List<Exception> list;

    @Override
    public void exceptionThrown(Exception e) {
        if (list == null)
            list = new ArrayList<Exception>();
        list.add(e);
    }

    public Collection<Exception> getExceptions() {
        return list;
    }

    public void clear() {
        list = null;
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
        if (exceptions == null || exceptions.isEmpty())
            return null;
        StringBuffer buf = new StringBuffer(exceptions.size() * 30);
        for (Exception e : exceptions) {
            String line = e.getClass().getName() + ": " + e.getMessage(); 
            buf.append(line);
            buf.append('\n');
        }
        return buf.toString();
    }

}
