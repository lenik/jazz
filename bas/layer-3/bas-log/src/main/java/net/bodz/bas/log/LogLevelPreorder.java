package net.bodz.bas.log;

import net.bodz.bas.t.preorder.AbstractPreorder;

public class LogLevelPreorder
        extends AbstractPreorder<LogLevel> {

    @Override
    public int compare(LogLevel o1, LogLevel o2) {
        int cmp = o1.getGroup() - o2.getGroup();
        if (cmp != 0)
            return cmp;
        return o1.getPriority() - o2.getPriority();
    }

    @Override
    public LogLevel getPreceding(LogLevel o) {
        return new LogLevel("", o.getGroup(), o.getPriority() - 1, o.getSyslogEquiv());
    }

    @Override
    public int precompare(LogLevel o1, LogLevel o2) {
        if (o1.getGroup() != o2.getGroup())
            return UNKNOWN;

        int diff = o1.getPriority() - o2.getPriority();
        if (diff < 0)
            return LESS_THAN;
        else if (diff > 0)
            return GREATER_THAN;

        return compareIdentity(o1, o2);
    }

    private static final LogLevelPreorder instance = new LogLevelPreorder();

    public static LogLevelPreorder getInstance() {
        return instance;
    }

}
