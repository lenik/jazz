package net.bodz.bas.err;

import java.util.ArrayList;

import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.LogRecord;

public class ExceptionLog
        extends ArrayList<LogRecord> {

    private static final long serialVersionUID = 1L;

    public void log(Throwable exception) {
        LogRecord item = new LogRecord(LogLevel.ERROR, 0, exception);
        add(item);
    }

}
