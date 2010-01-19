package net.bodz.bas.commons.scripting.util;

import java.util.ArrayList;
import java.util.List;

public class TryUtil {

    static class CatchFirst
            implements RecoverableExceptionListener {

        Exception first;

        @Override
        public void exceptionThrown(Exception ex) {
            if (first == null)
                first = ex;
        }

        @Override
        public void recoverException(RecoverableExceptionEvent e) {
            if (first == null)
                first = e.getException();
        }

    }

    static class CatchAll
            implements RecoverableExceptionListener {

        private List<Exception> list;

        void add(Exception e) {
            if (list == null)
                list = new ArrayList<Exception>();
            list.add(e);
        }

        @Override
        public void exceptionThrown(Exception ex) {
            add(ex);
        }

        @Override
        public void recoverException(RecoverableExceptionEvent e) {
            add(e.getException());
            e.setRecovered(true);
        }

    }

    public static void rethrow(ExceptionSourceRunnable runnable) throws Exception {
        CatchFirst catchFirst = new CatchFirst();
        runnable.addExceptionListener(catchFirst);
        try {
            runnable.execute();
        } finally {
            runnable.removeExceptionListener(catchFirst);
        }
        if (catchFirst.first != null)
            throw catchFirst.first;
    }

    public static List<Exception> catchAll(ExceptionSourceRunnable runnable) {
        CatchAll buf = new CatchAll();
        runnable.addExceptionListener(buf);
        try {
            runnable.execute();
        } finally {
            runnable.removeExceptionListener(buf);
        }
        return buf.list;
    }

}
