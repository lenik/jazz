package net.bodz.bas.err;

import java.io.Serializable;
import java.util.ArrayList;

class LogItem
        implements Serializable {

    private static final long serialVersionUID = 1L;

    long time;
    Throwable exception;

    public LogItem(long time, Throwable exception) {
        this.time = time;
        this.exception = exception;
    }

    public long getTime() {
        return time;
    }

    public Throwable getException() {
        return exception;
    }

}

public class ExceptionLog
        extends ArrayList<LogItem> {

    private static final long serialVersionUID = 1L;

    public void log(Throwable exception) {
        LogItem item = new LogItem(System.currentTimeMillis(), exception);
        add(item);
    }

}
