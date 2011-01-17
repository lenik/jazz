package net.bodz.bas.log;

import net.bodz.bas.collection.preorder.AbstractPreorder;

public class LogLevelPreorder
        extends AbstractPreorder<LogLevel> {

    @Override
    public LogLevel getPreceding(LogLevel o) {
        return o.getPreceding();
    }

    @Override
    public int precompare(LogLevel o1, LogLevel o2) {
        if (o1.getGroup() != o2.getGroup())
            return UNKNOWN;
        return o1.getPriority() - o2.getPriority();
    }

    private static final LogLevelPreorder instance = new LogLevelPreorder();

    public static LogLevelPreorder getInstance() {
        return instance;
    }

}
