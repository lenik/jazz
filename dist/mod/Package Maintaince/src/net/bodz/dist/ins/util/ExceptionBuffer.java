package net.bodz.dist.ins.util;

import java.beans.ExceptionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExceptionBuffer implements ExceptionListener {

    List<Exception> list;

    public ExceptionBuffer() {
        list = new ArrayList<Exception>();
    }

    @Override
    public void exceptionThrown(Exception e) {
        list.add(e);
    }

    public Collection<Exception> getExceptions() {
        return list;
    }

    public static String summary(Collection<Exception> exceptions) {
        StringBuffer buf = new StringBuffer(exceptions.size() * 30);
        for (Exception e : exceptions) {
            String line = e.getClass().getName() + ": " + e.getMessage(); //$NON-NLS-1$
            buf.append(line);
            buf.append('\n');
        }
        return buf.toString();
    }

}
